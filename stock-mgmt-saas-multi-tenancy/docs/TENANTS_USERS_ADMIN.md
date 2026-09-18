# Tenants, Utilisateurs et Admin Plateforme

## Table des matières

1. [Vue d'ensemble du modèle multi-tenant](#1-vue-densemble)
2. [Le Platform Admin](#2-le-platform-admin)
3. [Les Tenants](#3-les-tenants)
4. [Les Utilisateurs (Users)](#4-les-utilisateurs-users)
5. [Cycle de vie d'un Tenant](#5-cycle-de-vie-dun-tenant)
6. [Connexion : Platform Admin vs Tenant](#6-connexion--platform-admin-vs-tenant)
7. [Créer le Platform Admin](#7-créer-le-platform-admin)
8. [Flux complet : de l'inscription à la connexion d'un tenant](#8-flux-complet--de-linscription-à-la-connexion-dun-tenant)
9. [Résumé des rôles et permissions](#9-résumé-des-rôles-et-permissions)

---

## 1. Vue d'ensemble

L'application adopte une architecture **SaaS multi-tenant avec isolation par schema PostgreSQL**.

```
┌─────────────────────────────────────────────────┐
│              Platform Admin                     │
│  (gère tous les tenants, aucun schema propre)   │
└──────────────────────┬──────────────────────────┘
                       │ approuve / suspend / désactive
          ┌────────────┴────────────┐
          ▼                         ▼
   ┌─────────────┐           ┌─────────────┐
   │  Tenant A   │           │  Tenant B   │
   │ schema:     │           │ schema:     │
   │ tenant_acme │           │ tenant_beta │
   └──────┬──────┘           └──────┬──────┘
          │                         │
   ┌──────▼──────┐           ┌──────▼──────┐
   │  Users A    │           │  Users B    │
   │ (company    │           │ (company    │
   │  admin,     │           │  admin,     │
   │  operators) │           │  operators) │
   └─────────────┘           └─────────────┘
```

**Règle fondamentale :** les données d'un tenant ne sont jamais visibles par un autre tenant.
Le schema PostgreSQL change à chaque requête en fonction du JWT présenté.

---

## 2. Le Platform Admin

### Qui est-ce ?

Le **Platform Admin** est l'administrateur global de la plateforme SaaS. Il n'appartient à **aucun tenant** — son `tenant_id` est `null` dans la base de données.

Il est créé directement en base via une migration Flyway (`V2__Seed_Platform_Admin.sql`) lors du premier démarrage de l'application.

### Caractéristiques

| Attribut | Valeur |
|---|---|
| Rôle | `ROLE_PLATFORM_ADMIN` |
| `tenant_id` | `null` |
| Schema PostgreSQL | `public` (pas de schema tenant) |
| Stocké dans | Table `public.users` |
| Créé par | Migration Flyway `V2__Seed_Platform_Admin.sql` |

### Ce que le Platform Admin peut faire

| Action | Endpoint |
|---|---|
| Lister tous les tenants | `GET /api/v1/tenants` |
| Approuver un tenant (provision le schema) | `POST /api/v1/tenants/approve/{tenant-id}` |
| Activer un tenant | `PATCH /api/v1/tenants/activate/{tenant-id}` |
| Désactiver un tenant | `PATCH /api/v1/tenants/deactivate/{tenant-id}` |
| Suspendre un tenant | `PATCH /api/v1/tenants/suspend/{tenant-id}` |

> Le Platform Admin **ne peut pas** accéder aux données métier des tenants (produits, stocks, catégories).
> Ces endpoints sont protégés par Hibernate et `TenantContext` — sans `tenant_id` dans le JWT, le schema `public` est utilisé et ces tables n'y existent pas.

### JWT du Platform Admin

```json
{
  "sub": "<userId>",
  "role": "ROLE_PLATFORM_ADMIN",
  "iat": 1714000000,
  "exp": 1714086400,
  "iss": "stock-saas-app"
}
```

**Remarque importante :** le claim `tenant_id` est **absent** du token. C'est ce qui distingue le Platform Admin d'un utilisateur tenant au niveau du JWT.

---

## 3. Les Tenants

### Définition

Un **Tenant** représente une entreprise cliente qui s'abonne à la plateforme. Il possède :

- un **schema PostgreSQL dédié** (`tenant_<companyCode>`)
- ses propres **produits, catégories, mouvements de stock**
- un ou plusieurs **utilisateurs**

### Structure de l'entité Tenant

```java
// Table : public.tenants
Tenant {
    String id;               // UUID, clé primaire
    String companyName;      // "Acme Corp"
    String companyCode;      // "acme" → schema = tenant_acme
    String email;            // email de contact de l'entreprise
    TenantStatus status;     // PENDING | ACTIVE | SUSPENDED | INACTIVE

    // Credentials du premier admin (stockés à l'inscription)
    String adminFullName;
    String adminEmail;
    String adminUsername;
    String adminPassword;    // BCrypt hashé
}
```

### Schema PostgreSQL du Tenant

Le nom du schema est dérivé du `companyCode` :

```
companyCode = "acme"   → schema = tenant_acme
companyCode = "BETA"   → schema = tenant_beta  (lowercase)
companyCode = "Corp42" → schema = tenant_corp42
```

Ce schema contient les tables :
- `categories`
- `products`
- `stock_mvts`

---

## 4. Les Utilisateurs (Users)

### Types d'utilisateurs

| Rôle | Description | Périmètre |
|---|---|---|
| `ROLE_PLATFORM_ADMIN` | Administrateur global de la plateforme | Aucun tenant |
| `ROLE_COMPANY_ADMIN` | Premier admin créé lors du provisioning tenant | Son tenant |
| `ROLE_ADMINISTRATOR` | Administrateur d'un tenant | Son tenant |
| `ROLE_USER` | Utilisateur standard | Son tenant |
| `ROLE_SALES_OPERATOR` | Opérateur de vente | Son tenant |

### Structure de l'entité User

```java
// Table : public.users
User {
    String id;           // UUID
    Tenant tenant;       // null pour ROLE_PLATFORM_ADMIN
    String username;     // unique
    String email;        // unique
    String password;     // BCrypt hashé
    String firstName;
    String lastName;
    UserRole role;       // enum ci-dessus
    boolean enabled;
}
```

### Où sont stockés les utilisateurs ?

**Tous** les utilisateurs (Platform Admin comme utilisateurs tenant) sont dans la table **`public.users`**.
La colonne `tenant_id` fait la distinction :

```sql
-- Platform Admin : tenant_id = null
SELECT * FROM public.users WHERE role = 'ROLE_PLATFORM_ADMIN';

-- Utilisateurs d'un tenant : tenant_id pointe vers public.tenants
SELECT * FROM public.users WHERE tenant_id = '<uuid-du-tenant>';
```

---

## 5. Cycle de vie d'un Tenant

```
[Inscription via /api/v1/auth/register]
              │
              ▼
         PENDING ──────────────────────────────────────────────┐
              │                                                  │
              │ POST /api/v1/tenants/approve/{id}                │ (Platform Admin)
              │ → Crée le schema PostgreSQL                      │
              │ → Exécute les migrations Flyway                  │
              │ → Crée le ROLE_COMPANY_ADMIN dans public.users   │
              ▼                                                  │
          ACTIVE ◄───────────────────────────────────────────── ┘
              │
    ┌─────────┴─────────┐
    │                   │
    ▼                   ▼
SUSPENDED          INACTIVE
(PATCH /suspend)   (PATCH /deactivate)
    │
    │ PATCH /activate
    ▼
  ACTIVE
```

### Ce qui se passe lors de l'approbation (`/approve`)

1. Vérification que le tenant est en statut `PENDING`
2. Mise à jour du statut → `ACTIVE`
3. Création du schema : `CREATE SCHEMA tenant_<companyCode>`
4. Exécution de Flyway sur `db/migration/tenant/`
5. Création du `ROLE_COMPANY_ADMIN` dans `public.users` avec les credentials fournis à l'inscription
6. En cas d'erreur : rollback du schema (`DROP SCHEMA ... CASCADE`) et retour au statut `PENDING`

---

## 6. Connexion : Platform Admin vs Tenant

Les deux utilisent le **même endpoint** `/api/v1/auth/login` avec les mêmes paramètres.
La différence réside dans le **JWT généré** et dans ce qui se passe **après**.

### Endpoint de connexion

```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "...",
  "password": "..."
}
```

---

### Connexion Platform Admin

```
1. POST /api/v1/auth/login { username: "platform_admin", password: "..." }
   │
   ▼
2. Spring Security authentifie l'utilisateur (UsernamePasswordAuthenticationToken)
   │
   ▼
3. JwtTokenService.generateAccessToken(tenantId=null, userId, role="ROLE_PLATFORM_ADMIN")
   │
   ▼
4. JWT généré :
   {
     "sub": "<userId>",
     "role": "ROLE_PLATFORM_ADMIN",
     // PAS de claim "tenant_id"
     "iss": "stock-saas-app",
     "iat": ...,
     "exp": ...
   }
   │
   ▼
5. Sur chaque requête suivante :
   - JwtAuthenticationFilter extrait le token
   - tenantId = null  →  TenantContext reste vide
   - Aucun schema tenant n'est activé
   - Hibernate utilise le schema public
   - Seuls les endpoints /api/v1/tenants/** sont accessibles (rôle requis)
```

---

### Connexion d'un utilisateur Tenant

```
1. POST /api/v1/auth/login { username: "admin_acme", password: "..." }
   │
   ▼
2. Spring Security authentifie l'utilisateur
   │
   ▼
3. JwtTokenService.generateAccessToken(tenantId="<uuid>", userId, role="ROLE_COMPANY_ADMIN")
   │
   ▼
4. JWT généré :
   {
     "sub": "<userId>",
     "role": "ROLE_COMPANY_ADMIN",
     "tenant_id": "<uuid-du-tenant>",   // ← claim présent
     "iss": "stock-saas-app",
     "iat": ...,
     "exp": ...
   }
   │
   ▼
5. Sur chaque requête suivante :
   - JwtAuthenticationFilter extrait le token
   - tenantId = "<uuid>"  →  TenantContext.setCurrentTenant(tenantId)
   - TenantSchemaResolver : SELECT company_code FROM tenants WHERE id = ?
                            → companyCode = "acme"
                            → schemaName = "tenant_acme"
   - TenantContext.setCurrentSchema("tenant_acme")
   - Hibernate → MultiTenantConnectionProviderImpl → SET search_path = tenant_acme
   - Toutes les requêtes JPA s'exécutent dans le schema tenant_acme
```

---

### Tableau comparatif

| | Platform Admin | Utilisateur Tenant |
|---|---|---|
| Endpoint de login | `/api/v1/auth/login` | `/api/v1/auth/login` |
| Claim `tenant_id` dans JWT | Absent (`null`) | Présent (UUID du tenant) |
| Schema PostgreSQL actif | `public` | `tenant_<companyCode>` |
| `TenantContext` | Vide | Renseigné |
| Accès à `/api/v1/tenants/**` | Oui (`ROLE_PLATFORM_ADMIN`) | Non (403 Forbidden) |
| Accès aux produits/stocks | Non (schema non défini) | Oui (son schema) |
| Données visibles | Table `public.tenants` / `public.users` | Tables de son schema uniquement |

---

## 7. Créer le Platform Admin

### Méthode 1 : Migration Flyway (recommandée, déjà en place)

Le fichier `src/main/resources/db/migration/common/V2__Seed_Platform_Admin.sql` est exécuté automatiquement au démarrage :

```sql
INSERT INTO users (id, created_at, created_by, deleted, enabled,
                   username, email, password, first_name, last_name, role, tenant_id)
VALUES (
    gen_random_uuid(),
    now(),
    'system',
    false,
    true,
    'platform_admin',
    'platform@saas.internal',
    '$2a$10$v3gzQM4jXE9dD5.Iye/ZAetoq.43oDs159waZLQzbkgsPRYxZmE6C',  -- à remplacer
    'Platform',
    'Admin',
    'ROLE_PLATFORM_ADMIN',
    null   -- pas de tenant
);
```

> Le hash BCrypt correspond au mot de passe défini lors de la création de la migration.
> Pour générer un nouveau hash :

```java
// En Java
String hash = new BCryptPasswordEncoder().encode("votre_mot_de_passe");
System.out.println(hash);
```

```bash
# Ou via htpasswd (ligne de commande)
htpasswd -bnBC 10 "" votre_mot_de_passe | tr -d ':\n'
```

### Connexion après démarrage

```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "platform_admin",
  "password": "<mot_de_passe_en_clair>"
}
```

Réponse :

```json
{
  "accessToken": "eyJhbGciOiJSUzI1NiJ9...",
  "tokenType": "Bearer"
}
```

### Utiliser le token pour gérer les tenants

```http
GET /api/v1/tenants
Authorization: Bearer eyJhbGciOiJSUzI1NiJ9...
```

```http
POST /api/v1/tenants/approve/3f7a8c12-...
Authorization: Bearer eyJhbGciOiJSUzI1NiJ9...
```

---

## 8. Flux complet : de l'inscription à la connexion d'un tenant

### Étape 1 — L'entreprise s'inscrit

```http
POST /api/v1/auth/register
Content-Type: application/json

{
  "companyName": "Acme Corp",
  "companyCode": "acme",
  "email": "contact@acme.com",
  "adminFullName": "Alice Dupont",
  "adminEmail": "alice@acme.com",
  "adminUsername": "alice_acme",
  "adminPassword": "SecurePass123!"
}
```

→ Tenant créé en base avec statut `PENDING`. Aucun schema créé.

### Étape 2 — Le Platform Admin approuve

```http
POST /api/v1/tenants/approve/3f7a8c12-...
Authorization: Bearer <token_platform_admin>
```

→ Schema `tenant_acme` créé.
→ Migrations Flyway exécutées.
→ Utilisateur `alice_acme` créé dans `public.users` avec rôle `ROLE_COMPANY_ADMIN`.

### Étape 3 — L'admin du tenant se connecte

```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "username": "alice_acme",
  "password": "SecurePass123!"
}
```

→ JWT retourné avec `tenant_id` et `role: ROLE_COMPANY_ADMIN`.

### Étape 4 — L'admin du tenant accède à ses données

```http
GET /api/v1/products
Authorization: Bearer <token_alice_acme>
```

→ Hibernate bascule sur `tenant_acme` → résultats isolés au tenant Acme.

---

## 9. Résumé des rôles et permissions

| Rôle | Tenant | Login possible | Gestion tenants | Produits/Stocks | Gestion users |
|---|---|---|---|---|---|
| `ROLE_PLATFORM_ADMIN` | Aucun | Oui | Oui (tout) | Non | Non |
| `ROLE_COMPANY_ADMIN` | Son tenant | Oui | Non | Oui | Oui |
| `ROLE_ADMINISTRATOR` | Son tenant | Oui | Non | Oui | Oui |
| `ROLE_USER` | Son tenant | Oui | Non | Lecture seule | Non |
| `ROLE_SALES_OPERATOR` | Son tenant | Oui | Non | Partiel | Non |

---

> **Important :** La sécurité d'isolation entre tenants repose sur le JWT signé en RSA.
> Le `tenant_id` dans le token est **immutable côté client** — il ne peut être ni falsifié ni modifié sans invalider la signature RSA.
