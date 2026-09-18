# 📦 Stock Management SaaS Core

> **Enterprise Multi-Tenant Stock & Inventory Management Platform** built with **Java 17, Spring Boot 4.0.5, PostgreSQL 17, Hibernate, and Flyway**.

![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.5-brightgreen.svg)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-17-blue.svg)
![Flyway](https://img.shields.io/badge/Flyway-Migrations-red.svg)
![JWT](https://img.shields.io/badge/Auth-JWT%20RSA%20256-blueviolet.svg)

---

## 📋 Overview

**Stock Management SaaS Core** is a production-ready, highly scalable SaaS backend designed for multi-tenant stock and inventory management. Each customer (tenant) operates within a **strictly isolated PostgreSQL schema** (`schema-per-tenant`), ensuring data confidentiality, security, and performance compliance at enterprise scale.

---

## 🏗️ Multi-Tenancy Architecture

The application uses a **Shared Database + Schema per Tenant** multi-tenancy strategy.

```text
                  SaaS Application (Spring Boot 4.0.5)
                         │
              ┌──────────┴──────────┐
              │                     │
        Tenant Runtime       Tenant Provisioning & Migrations
              │                     │
       Tenant Context          TenantProvisioningService
              │                     ├── 1. Regex validation: ^[a-z0-9_]{3,30}$
   SET search_path = tenant_xxx     ├── 2. Status -> PROVISIONING
              │                     ├── 3. CREATE SCHEMA "tenant_xxx"
              │                     ├── 4. Flyway.migrate("db/migration/tenant")
              │                     ├── 5. Optional demo seeding (TenantSeedService)
              │                     └── 6. Status -> ACTIVE (or FAILED with failureReason)
              │                     │
              │                TenantMigrationService (1,000+ Tenants Scalability)
              │                     └── Parallel thread pool executor for batch migrations
              ▼                     ▼
                    PostgreSQL 17 Database
                         │
       ┌─────────────────┼─────────────────┐
       ▼                 ▼                 ▼
 public schema     tenant_novatech   tenant_techcorp
(tenants, users)  (flyway_history,  (flyway_history,
 Flyway public     categories,       categories,
 V1 -> V4          products,         products,
                   stock_mvts)       stock_mvts)
                   Flyway tenant     Flyway tenant
```

### 🔄 Request Execution Flow

1. **JWT Authentication**: `JwtAuthenticationFilter` extracts the `tenant` claim from the incoming JWT token.
2. **Schema Resolution**: `TenantSchemaResolver` looks up the `companyCode` from `public.tenants` (cached with `@Cacheable("tenantSchemas")`) and formats the target schema (`tenant_<companyCode>`).
3. **ThreadLocal Holder**: `TenantContext` stores the tenant ID and schema name for the current request thread.
4. **Hibernate Schema Switch**: `MultiTenantConnectionProviderImpl` executes `SET search_path TO tenant_<companyCode>, public` on connection acquire, and resets to `SET search_path TO public` on release.
5. **Thread Safety**: `TenantContext.clear()` is guaranteed in a `finally` block post-request execution to prevent ThreadLocal leaks.

---

## ⚡ Key Technical Features

- **Automated Schema Provisioning**: Fully automated schema creation and table initialization upon tenant approval (`TenantProvisioningService`).
- **SQL Injection Prevention**: Strict regex identifier validation (`TenantCodeValidator`: `^[a-z0-9_]{3,30}$`) and double-quoted schema execution (`CREATE SCHEMA IF NOT EXISTS "tenant_xxx"`).
- **Flyway Migration Isolation**:
  - `classpath:db/migration/public`: Platform tables (`tenants`, `users`).
  - `classpath:db/migration/tenant`: Tenant-isolated tables (`categories`, `products`, `stock_mvts`).
- **Scalable Batch Migration Service (1,000+ Tenants)**: `TenantMigrationService` runs Flyway schema migrations concurrently across all active tenant schemas using an `ExecutorService` thread pool.
- **Robust Error Recovery & State Machine**:
  - Tenant Lifecycle: `PENDING` ➔ `PROVISIONING` ➔ `ACTIVE` (or `FAILED` with persisted `failureReason`).
  - Automatic DDL rollback on failure.
- **Dynamic Demo Seeding**: `TenantSeedService` safely loads schema-agnostic demo data (`db/seed/demo/seed_tenant_demo_data.sql`).
- **Observability & Metrics**: Integrated Micrometer metrics (`Actuator`) tracking provisioning duration, migration success, and failure counts.
- **RSA Asymmetric JWT Authentication**: Token signing via RSA Private Key (`certs/private_key.pem`) and verification via RSA Public Key (`certs/public_key.pem`).

---

## 🚀 Quick Start Guide

### 1. Prerequisites

- **Java 17+**
- **Maven 3.8+**
- **Docker & Docker Compose** (for PostgreSQL)

### 2. Database Setup

Start PostgreSQL 17 container:

```bash
docker compose up -d
```

### 3. Application Configuration

Copy `.env.example` to `.env` (optional override):

```env
SPRING_PROFILES_ACTIVE=dev
DB_HOST=localhost
DB_PORT=5432
DB_NAME=saas-app-db
DB_USERNAME=postgres
DB_PASSWORD=postgres
SERVER_PORT=8080
```

### 4. Build and Run

```bash
# Navigate to backend directory
cd stock-mgmt-saas-multi-tenancy

# Build and package
./mvnw clean package -DskipTests

# Run the Spring Boot application
./mvnw spring-boot:run
```

Swagger UI will be available at: **`http://localhost:8080/swagger-ui.html`**

---

## 🌐 REST API Endpoints Overview

Base URL: `/api/v1`

### 🔑 Auth Endpoints (Public)

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/v1/auth/register` | Register a new tenant (Status: `PENDING`) |
| `POST` | `/api/v1/auth/login` | Authenticate user & return JWT token |

### 🏢 Tenant Management (`ROLE_PLATFORM_ADMIN`)

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/v1/tenants/approve/{id}` | Approve tenant, provision schema & run migrations |
| `PATCH` | `/api/v1/tenants/activate/{id}` | Activate tenant |
| `PATCH` | `/api/v1/tenants/deactivate/{id}` | Deactivate tenant |
| `PATCH` | `/api/v1/tenants/suspend/{id}` | Suspend tenant |
| `GET` | `/api/v1/tenants?page=0&size=10` | Get paginated tenant list |

### 📦 Products / Categories / Stock Movements (`ROLE_COMPANY_ADMIN` / `ROLE_USER`)

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/v1/categories` | Create product category |
| `GET` | `/api/v1/categories` | List categories for current tenant |
| `POST` | `/api/v1/products` | Create product |
| `GET` | `/api/v1/products` | List products for current tenant |
| `POST` | `/api/v1/stock-mvts` | Record stock movement (`IN` / `OUT`) |
| `GET` | `/api/v1/dashboard/stats` | Get tenant dashboard statistics & stock alerts |

---

## 🧪 Testing Suite

Run all unit and integration tests:

```bash
./mvnw clean test
```

### Test Coverage Highlights

- **`TenantCodeValidatorTest`**: Validates regex boundaries and SQL injection safety.
- **`TenantServiceTest`**: Tests state machine (`PENDING` ➔ `PROVISIONING` ➔ `ACTIVE` / `FAILED`), error persistence, and admin user creation.
- **`TenantMigrationServiceTest`**: Verifies batch migration execution across active tenants.
- **`TenantMultiTenancyIntegrationTest`**: Verifies `TenantContext` isolation, ThreadLocal cleanup, and schema identifier resolution.

---

## 📂 Project Structure

```text
stock-mgmt-saas-multi-tenancy/
├── src/main/java/com/tech/saas/
│   ├── Application.java                  # Main Spring Boot Entrypoint
│   ├── auth/                             # Authentication & JWT Service
│   ├── common/                           # Shared DTOs (PageResponse)
│   ├── config/                           # Multi-tenancy & Hibernate Config
│   ├── controllers/                      # REST API Controllers (v1)
│   ├── entities/                         # JPA Domain Entities & Enums
│   ├── exceptions/                       # Global Exception Handlers
│   ├── mappers/                          # DTO Entity Mappers
│   ├── repositories/                     # Spring Data JPA Repositories
│   ├── requests/                         # Inbound API Request DTOs
│   ├── responses/                        # Outbound API Response DTOs
│   ├── security/                         # SecurityConfig & JwtFilter
│   ├── services/                         # Business Logic Services & Impls
│   └── utils/                            # TenantCodeValidator & Helpers
└── src/main/resources/
    ├── application.yml                   # Application Configuration
    ├── certs/                            # RSA Private / Public Key Pairs
    └── db/
        ├── migration/
        │   ├── public/                   # Public Schema Flyway Migrations (V1->V4)
        │   └── tenant/                   # Tenant Schema Flyway Migrations (V1->...)
        └── seed/
            └── demo/                     # Schema-Agnostic Demo Seed SQL
```

---

## 📄 License

This project is open-source under the MIT License.
