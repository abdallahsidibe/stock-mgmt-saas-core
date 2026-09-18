# Guide — Stock Management SaaS Multi-Tenant

## Table des matières

1. [Prérequis](#1-prérequis)
2. [Lancer le projet](#2-lancer-le-projet)
3. [Différence entre Tenant et User](#3-différence-entre-tenant-et-user)
4. [Flow d'authentification complet](#4-flow-dauthentification-complet)
5. [Cycle de vie d'un Tenant](#5-cycle-de-vie-dun-tenant)
6. [Rôles utilisateurs](#6-rôles-utilisateurs)
7. [API — Référence rapide](#7-api--référence-rapide)
8. [Pagination](#8-pagination)

---

## 1. Prérequis

| Outil | Version minimale |
|---|---|
| Java | 17 |
| Maven | Inclus via `./mvnw` (pas besoin d'installation) |
| Docker Desktop | Toute version récente |
| PostgreSQL | Fourni via Docker |

**Ports utilisés :**
- `5432` — PostgreSQL
- `8080` — Application Spring Boot

---

## 2. Lancer le projet

### 2.1 Configurer l'environnement

```bash
cp .env.example .env
```

Éditez `.env` selon votre environnement. Les valeurs par défaut fonctionnent telles quelles :

| Variable | Valeur par défaut | Description |
|---|---|---|
| `SPRING_PROFILES_ACTIVE` | `dev` | Profil Spring actif |
| `DB_HOST` | `localhost` | Hôte PostgreSQL |
| `DB_PORT` | `5432` | Port PostgreSQL |
| `DB_NAME` | `saas-app-db` | Nom de la base |
| `DB_USERNAME` | `postgres` | Utilisateur DB |
| `DB_PASSWORD` | `postgres` | Mot de passe DB |
| `SERVER_PORT` | `8080` | Port de l'application |
| `JWT_PRIVATE_KEY_PATH` | `certs/private_key.pem` | Chemin clé privée RSA |
| `JWT_PUBLIC_KEY_PATH` | `certs/public_key.pem` | Chemin clé publique RSA |
| `JWT_ACCESS_TOKEN_EXPIRATION` | `86400000` | Expiration JWT en ms (24h) |

### 2.2 Démarrer PostgreSQL

```bash
docker compose up -d
```

Cela lance un container `saas-db` (image `postgres:17.5`) avec un volume persistant `postgres_data`.

### 2.3 Démarrer l'application

```bash
./mvnw spring-boot:run
```

Au démarrage, **Flyway exécute automatiquement** les migrations dans l'ordre :

| Migration | Schema | Ce qu'elle fait |
|---|---|---|
| `V1__Init_Common_Tables.sql` | `public` | Crée les tables `tenants` et `users` |
| `V2__Seed_Platform_Admin.sql` | `public` | Insère le compte `PLATFORM_ADMIN` initial |

### 2.4 Vérifier que tout fonctionne

| URL | Description |
|---|---|
| http://localhost:8080/swagger-ui.html | Documentation interactive Swagger UI |
| http://localhost:8080/v3/api-docs | Spec OpenAPI JSON |

**Compte disponible immédiatement après démarrage :**

```
username : platform_admin
password : Admin@123
```

---

## 3. Différence entre Tenant et User

### Vue d'ensemble

Dans une architecture SaaS multi-tenant, ces deux concepts sont distincts et complémentaires :

- Un **Tenant** représente une **entreprise cliente** qui s'abonne au service.
- Un **User** représente une **personne physique** qui se connecte à l'application.

### Tableau comparatif

| Aspect | Tenant | User |
|---|---|---|
| Représente | Une entreprise | Une personne |
| Table | `public.tenants` | `public.users` |
| Créé par | Inscription publique (`/auth/register`) | Approbation du tenant (admin initial) ou `COMPANY_ADMIN` |
| Lifecycle | `PENDING → ACTIVE → SUSPENDED / INACTIVE` | `enabled = true/false`, soft delete |
| Lié à | N/A | Un Tenant via `tenant_id` (FK) |
| Schema dédié | `tenant_{company_code}` (ex: `tenant_tns2025`) | Toujours dans `public` |
| Utilisé pour login | Non | Oui — implémente Spring Security `UserDetails` |

### Modèle de données

```
Schema "public"
├── tenants                          ← une ligne = une entreprise
│   ├── id, company_name, company_code, email
│   ├── status (PENDING/ACTIVE/...)
│   └── admin_username, admin_password  ← credentials temporaires avant approbation
│
└── users                            ← une ligne = une personne
    ├── id, username, email, password (BCrypt)
    ├── role (PLATFORM_ADMIN, COMPANY_ADMIN, ...)
    ├── enabled, deleted
    └── tenant_id → FK vers tenants  ← null pour PLATFORM_ADMIN

Schema "tenant_tns2025"              ← créé lors de l'approbation
├── categories
├── products
└── stock_mvts
```

### Pourquoi les données admin sont dans Tenant ?

Lors de l'inscription (`/auth/register`), l'entreprise fournit les credentials de son futur admin.
Ces informations sont stockées dans `Tenant` temporairement.
**C'est seulement lors de l'approbation** par le `PLATFORM_ADMIN` qu'un vrai `User` est créé
avec ces credentials dans la table `users`.

### Le cas PLATFORM_ADMIN

Le `PLATFORM_ADMIN` est le seul `User` sans `tenant_id`. Il a un scope **global** : il peut
voir et gérer tous les tenants mais n'accède pas aux données métier (produits, stocks…).

```
PLATFORM_ADMIN  →  tenant_id = null  →  opère sur le schema "public"
COMPANY_ADMIN   →  tenant_id = "abc" →  opère sur le schema "tenant_tns2025"
```

---

## 4. Flow d'authentification complet

### Vue d'ensemble

```
[Démarrage app]
      │
      ▼
Flyway seed → platform_admin créé
      │
      ▼
[Étape 1] POST /auth/register   → Tenant PENDING créé (pas encore de User)
      │
      ▼
[Étape 2] POST /auth/login      → Login en tant que platform_admin → JWT sans tenant_id
      │
      ▼
[Étape 3] POST /tenants/approve → Schema créé + User COMPANY_ADMIN créé → Tenant ACTIVE
      │
      ▼
[Étape 4] POST /auth/login      → Login en tant que company admin → JWT avec tenant_id
      │
      ▼
[Étape 5] Utiliser les routes métier (products, categories, stocks…)
```

---

### Étape 0 — Compte PLATFORM_ADMIN (disponible dès le démarrage)

Seedé par la migration `V2__Seed_Platform_Admin.sql`, aucune action requise.

```
username : platform_admin
password : Admin@123
```

---

### Étape 1 — Inscrire un nouveau tenant

**Endpoint :** `POST /api/v1/auth/register`
**Authentification :** Aucune (public)

```json
{
  "companyName": "TechNova Solutions",
  "companyCode": "TNS2025",
  "email": "contact@technova.com",
  "adminFullName": "Abdoulaye Sidibé",
  "adminEmail": "admin@technova.com",
  "adminUsername": "admin_tns",
  "adminPassword": "SecurePass123!"
}
```

| Champ | Obligatoire | Contrainte |
|---|---|---|
| `companyName` | Oui | Non vide |
| `companyCode` | Oui | Unique |
| `email` | Oui | Unique |
| `adminFullName` | Oui | Non vide |
| `adminEmail` | Oui | Unique |
| `adminUsername` | Oui | Unique |
| `adminPassword` | Oui | Non vide |

**Résultat :** Tenant créé avec status `PENDING`. Aucun `User` créé à ce stade.

---

### Étape 2 — Login PLATFORM_ADMIN

**Endpoint :** `POST /api/v1/auth/login`
**Authentification :** Aucune (public)

```json
{
  "username": "platform_admin",
  "password": "Admin@123"
}
```

**Réponse :**

```json
{
  "accessToken": "eyJhbGciOiJSUzI1NiJ9...",
  "tokenType": "Bearer"
}
```

**JWT décodé (payload) :**

```json
{
  "sub": "<userId>",
  "role": "ROLE_PLATFORM_ADMIN",
  "iat": 1714132800,
  "exp": 1714219200,
  "iss": "stock-saas-app"
}
```

> `tenant_id` est absent car le PLATFORM_ADMIN n'appartient à aucun tenant.

---

### Étape 3 — Récupérer l'ID du tenant à approuver

**Endpoint :** `GET /api/v1/tenants?page=0&size=10`
**Header :** `Authorization: Bearer <token_platform_admin>`

Récupérez l'`id` du tenant dont le status est `PENDING`.

---

### Étape 4 — Approuver le tenant

**Endpoint :** `POST /api/v1/tenants/approve/{tenant-id}`
**Header :** `Authorization: Bearer <token_platform_admin>`

**Ce qui se passe en coulisses :**

```
1. Tenant status → ACTIVE
2. CREATE SCHEMA tenant_tns2025
3. Flyway migre : categories, products, stock_mvts dans tenant_tns2025
4. User ROLE_COMPANY_ADMIN créé dans public.users
   - username  : admin_tns
   - password  : SecurePass123! (BCrypt)
   - tenant_id : <id du tenant>
   - enabled   : true
```

---

### Étape 5 — Login Company Admin

**Endpoint :** `POST /api/v1/auth/login`

```json
{
  "username": "admin_tns",
  "password": "SecurePass123!"
}
```

**JWT décodé (payload) :**

```json
{
  "sub": "<userId>",
  "role": "ROLE_COMPANY_ADMIN",
  "tenant_id": "<tenantId>",
  "iat": 1714132800,
  "exp": 1714219200,
  "iss": "stock-saas-app"
}
```

> Cette fois `tenant_id` est présent. Toutes les requêtes suivantes opèrent sur le schema `tenant_tns2025`.

---

### Fonctionnement du JWT à chaque requête

```
Requête HTTP + "Authorization: Bearer <token>"
        │
        ▼
JwtAuthenticationFilter
  ├── Extrait le token du header
  ├── Valide la signature RSA-256
  ├── Extrait userId, tenantId, role
  │
  ├── Si tenantId != null :
  │     TenantSchemaResolver.resolve(tenantId)
  │       └── SELECT company_code FROM tenants WHERE id = ?
  │           → "tenant_tns2025"  (mis en cache @Cacheable)
  │     TenantContext.setCurrentSchema("tenant_tns2025")
  │
  └── SecurityContext ← UsernamePasswordAuthenticationToken(userId, [role])
        │
        ▼
Controller → Service → Repository (JPA)
        │
        ▼
Hibernate → CurrentTenantIdentifierResolver
  └── TenantContext.getCurrentSchema() → "tenant_tns2025"
        │
        ▼
MultiTenantConnectionProvider
  └── SET search_path TO tenant_tns2025, public
        │
        ▼
SQL exécuté dans tenant_tns2025
        │
        ▼
Réponse envoyée → TenantContext.clear()
```

---

## 5. Cycle de vie d'un Tenant

```
                ┌─────────────────────────────────────────┐
                │                                         │
   /register    │  /approve          /suspend             │
  ──────────► PENDING ──────────► ACTIVE ──────────► SUSPENDED
                                    │
                                    │ /deactivate
                                    ▼
                                 INACTIVE
```

| Transition | Endpoint | Rôle requis |
|---|---|---|
| `→ PENDING` | `POST /auth/register` | Public |
| `PENDING → ACTIVE` | `POST /tenants/approve/{id}` | PLATFORM_ADMIN |
| `ACTIVE → SUSPENDED` | `PATCH /tenants/suspend/{id}` | PLATFORM_ADMIN |
| `ACTIVE → INACTIVE` | `PATCH /tenants/deactivate/{id}` | PLATFORM_ADMIN |

> L'approbation (`/approve`) est la seule transition qui provisionne le schema PostgreSQL
> et crée l'utilisateur admin de l'entreprise.

---

## 6. Rôles utilisateurs

| Rôle | Description | Périmètre |
|---|---|---|
| `ROLE_PLATFORM_ADMIN` | Administrateur de la plateforme SaaS | Global — gère les tenants, pas de tenant_id |
| `ROLE_COMPANY_ADMIN` | Administrateur de l'entreprise | Son tenant — créé automatiquement à l'approbation |
| `ROLE_ADMINISTRATOR` | Administrateur opérationnel | Son tenant — peut gérer les utilisateurs en lecture |
| `ROLE_USER` | Utilisateur standard | Son tenant — accès lecture/écriture aux données métier |
| `ROLE_SALES_OPERATOR` | Opérateur commercial | Son tenant — accès aux stocks et mouvements |

### Matrice d'accès par endpoint

| Endpoint | PLATFORM_ADMIN | COMPANY_ADMIN | ADMINISTRATOR | USER | SALES_OPERATOR |
|---|:---:|:---:|:---:|:---:|:---:|
| `POST /auth/login` | ✓ | ✓ | ✓ | ✓ | ✓ |
| `POST /auth/register` | ✓ | ✓ | ✓ | ✓ | ✓ |
| `GET/POST /tenants/**` | ✓ | — | — | — | — |
| `POST /users` | — | ✓ | — | — | — |
| `GET /users/**` | — | ✓ | ✓ | — | — |
| `PUT/DELETE /users/**` | — | ✓ | — | — | — |
| `GET/POST/PUT/DELETE /products/**` | — | ✓ | ✓ | ✓ | ✓ |
| `GET/POST/PUT/DELETE /categories/**` | — | ✓ | ✓ | ✓ | ✓ |
| `GET/POST/PUT/DELETE /stocks/**` | — | ✓ | ✓ | ✓ | ✓ |

---

## 7. API — Référence rapide

### Auth (public)

| Méthode | Path | Description | Body |
|---|---|---|---|
| `POST` | `/api/v1/auth/login` | Login → JWT | `LoginRequest` |
| `POST` | `/api/v1/auth/register` | Inscription tenant | `RegisterTenantRequest` |

### Tenants (PLATFORM_ADMIN)

| Méthode | Path | Description |
|---|---|---|
| `GET` | `/api/v1/tenants` | Liste paginée des tenants |
| `POST` | `/api/v1/tenants/approve/{tenant-id}` | Approuver + provisionner |
| `PATCH` | `/api/v1/tenants/activate/{tenant-id}` | Activer |
| `PATCH` | `/api/v1/tenants/deactivate/{tenant-id}` | Désactiver |
| `PATCH` | `/api/v1/tenants/suspend/{tenant-id}` | Suspendre |

### Users (COMPANY_ADMIN / ADMINISTRATOR)

| Méthode | Path | Description |
|---|---|---|
| `POST` | `/api/v1/users` | Créer un utilisateur |
| `GET` | `/api/v1/users` | Liste paginée |
| `GET` | `/api/v1/users/{user-id}` | Détail |
| `PUT` | `/api/v1/users/{user-id}` | Mettre à jour |
| `DELETE` | `/api/v1/users/{user-id}` | Supprimer (soft delete) |
| `PUT` | `/api/v1/users/{user-id}/enable` | Activer le compte |
| `PUT` | `/api/v1/users/{user-id}/disable` | Désactiver le compte |

### Products, Categories, Stocks (tout rôle authentifié)

| Méthode | Path | Description |
|---|---|---|
| `POST` | `/api/v1/products` | Créer |
| `GET` | `/api/v1/products` | Liste paginée |
| `GET` | `/api/v1/products/{product-id}` | Détail |
| `PUT` | `/api/v1/products/{product-id}` | Mettre à jour |
| `DELETE` | `/api/v1/products/{product-id}` | Supprimer (soft delete) |
| `POST` | `/api/v1/categories` | Créer |
| `GET` | `/api/v1/categories` | Liste paginée |
| `GET` | `/api/v1/categories/{category-id}` | Détail |
| `PUT` | `/api/v1/categories/{category-id}` | Mettre à jour |
| `DELETE` | `/api/v1/categories/{category-id}` | Supprimer |
| `POST` | `/api/v1/stocks` | Créer un mouvement |
| `GET` | `/api/v1/stocks` | Liste paginée |
| `GET` | `/api/v1/stocks/{stock-mvt-id}` | Détail |
| `GET` | `/api/v1/stocks/product/{product-id}` | Mouvements par produit |
| `PUT` | `/api/v1/stocks/{stock-mvt-id}` | Mettre à jour |
| `DELETE` | `/api/v1/stocks/{stock-mvt-id}` | Supprimer |

**Header d'authentification requis sur toutes les routes non-publiques :**

```
Authorization: Bearer <accessToken>
```

---

## 8. Pagination

Tous les endpoints de liste retournent un `PageResponse<T>` :

```json
{
  "content": [ ... ],
  "page": 0,
  "size": 10,
  "totalElements": 42,
  "totalPages": 5
}
```

**Paramètres query :**

| Paramètre | Défaut | Description |
|---|---|---|
| `page` | `0` | Numéro de page (0-indexed) |
| `size` | `10` | Nombre d'éléments par page |

**Exemple :**

```
GET /api/v1/products?page=1&size=20
```
