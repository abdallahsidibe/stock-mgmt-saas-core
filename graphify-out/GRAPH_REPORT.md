# Graph Report - saas-multi-tenancy  (2026-09-19)

## Corpus Check
- 316 files · ~98,082 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 30 file(s) not represented in the graph (top: .scss 17, (none) 8, .graphify-bak 1)

## Summary
- 2422 nodes · 4910 edges · 150 communities (121 shown, 29 thin omitted)
- Extraction: 95% EXTRACTED · 5% INFERRED · 0% AMBIGUOUS · INFERRED: 247 edges (avg confidence: 0.87)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `013aab0f`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- TenantServiceImpl
- CurrentTenantIdentifierResolverImpl
- TenantMigrationServiceImpl
- Tenant
- org.junit.jupiter.api.DisplayName
- TenantCodeValidatorTest.java
- TenantServiceTest.java
- com.tech:saas-app
- UserRole
- TenantServiceImpl.java
- lombok.RequiredArgsConstructor
- lombok.Getter
- Java Migration Skill
- saas-app-ui
- Security Audit Skill
- Issue Triage Skill
- functions.ts
- StockMvtResponse
- Clean Code Skill
- TenantService
- JPA Patterns Skill
- Test Quality Skill (JUnit 5 + AssertJ)
- Concurrency Review Skill
- API Contract Review Skill
- Performance Smell Detection Skill
- DashboardServiceImpl.java
- TokenService
- Logging Patterns Skill
- SOLID Principles Skill
- Maven Dependency Audit Skill
- StrictHttpResponse
- Guide — Stock Management SaaS Multi-Tenant
- User
- Spring Boot Patterns Skill
- user.service.ts
- Git Commit Message Skill
- stock-mvt.service.ts
- category.service.ts
- MultiTenantConnectionProviderImpl
- Changelog Generator Skill
- JwtTokenService.java
- Architecture Review Skill
- ProductList
- What You Must Do When Invoked
- @angular/core
- manage-product.ts
- Statistics
- org.springframework.stereotype.Component
- JwtAuthenticationFilter.java
- package.json
- product.service.ts
- Tenants, Utilisateurs et Admin Plateforme
- models.ts
- SecurityConfig.java
- CLAUDE.md — Stock Management SaaS Multi-Tenancy
- Design Patterns Skill
- Review Checklist
- .claude/agents/migration-expert.md
- stock-mgmt-saas-multi-tenancy/.claude/agents/migration-expert.md
- TenantContext
- lombok.extern.slf4j.Slf4j
- CLAUDE.md — Stock Management SaaS Multi-Tenancy
- UserServiceImpl
- GlobalExceptionHandler.java
- JwtTokenService
- Règles de style de code
- .claude/skills/deploy/SKILL.md
- dependencies
- .append
- Concurrency Review Skill
- Skills
- Stratégie de test
- Stratégie de test
- StockMvt
- Register
- API Contract Review Skill
- Architecture Review Skill
- Performance Smell Detection Skill
- Mode `quick` (défaut)
- ref_angular_core_testing
- Mode `quick` (défaut)
- ManageCategory
- ManageProduct
- ManageUser
- angular/SKILL.md
- Règles de style de code
- mvnw
- Login
- CategoryList
- Checklist de revue — applique dans cet ordre
- Conventions API REST
- Clean Code
- Design Patterns
- graphify reference: extra exports and benchmark
- Java Migration
- JPA Patterns
- Logging Patterns
- Checklist de revue — applique dans cet ordre
- Conventions API REST
- devDependencies
- TenantList
- .claude/agents/test-writer.md
- Changelog Generator
- Security Audit
- SOLID Principles
- stock-mgmt-saas-multi-tenancy/.claude/agents/test-writer.md
- Skill : deploy
- SaasAppUi
- UserList
- Java Code Review
- Maven Dependency Audit
- Spring Boot Patterns
- app.config.ts
- TenantSchemaResolver
- BasicService
- home.ts
- Périmètre de l'audit
- /project:deploy
- /project:fix-issue
- Diagnostic des erreurs Flyway courantes
- Git Commit Messages
- Issue Triage
- Test Quality (JUnit 5 + AssertJ)
- Application.java
- Périmètre de l'audit
- /project:deploy
- /project:fix-issue
- /project:review
- Diagnostic des erreurs Flyway courantes
- /project:review
- 📦 Stock Management SaaS Core
- ParameterCodec
- graphify reference: add a URL and watch a folder
- graphify reference: commit hook and native CLAUDE.md integration
- graphify reference: incremental update and cluster-only
- graphify reference: GitHub clone and cross-repo merge
- graphify reference: transcribe video and audio
- .claude/CLAUDE.md
- extraction-spec.md
- connection
- entitynotfoundexception

## God Nodes (most connected - your core abstractions)
1. `RequestBuilder` - 68 edges
2. `StrictHttpResponse` - 67 edges
3. `PageResponse` - 39 edges
4. `rxjs` - 39 edges
5. `TokenService` - 36 edges
6. `TenantContext` - 33 edges
7. `@angular/core` - 33 edges
8. `Tenant` - 30 edges
9. `User` - 29 edges
10. `@angular/router` - 25 edges

## Surprising Connections (you probably didn't know these)
- `Ce qu'il faut toujours tester` --references--> `PageResponse`  [INFERRED]
  .claude/rules/testing.md → stock-mgmt-saas-multi-tenancy/src/main/java/com/tech/saas/common/PageResponse.java
- `3. Conventions de code` --references--> `AbstractEntity`  [INFERRED]
  .claude/agents/code-reviewer.md → stock-mgmt-saas-multi-tenancy/src/main/java/com/tech/saas/entities/AbstractEntity.java
- `Entités JPA` --references--> `AbstractEntity`  [INFERRED]
  .claude/rules/code-style.md → stock-mgmt-saas-multi-tenancy/src/main/java/com/tech/saas/entities/AbstractEntity.java
- `Nommage` --references--> `ProductServiceImpl`  [INFERRED]
  .claude/rules/code-style.md → stock-mgmt-saas-multi-tenancy/src/main/java/com/tech/saas/services/impl/ProductServiceImpl.java
- `2. Tests d'intégration (repository + DB)` --references--> `TenantContext`  [INFERRED]
  .claude/rules/testing.md → stock-mgmt-saas-multi-tenancy/src/main/java/com/tech/saas/config/TenantContext.java

## Import Cycles
- None detected.

## Communities (150 total, 29 thin omitted)

### Community 0 - "TenantServiceImpl"
Cohesion: 0.15
Nodes (9): Provisioning — Ordre des opérations, org.springframework.security.crypto.password.PasswordEncoder, Provisioning — Ordre des opérations, TenantMapper, TenantRepository, Override, TenantServiceImpl, ProvisioningService (+1 more)

### Community 1 - "CurrentTenantIdentifierResolverImpl"
Cohesion: 0.13
Nodes (14): availablesettings, Architecture — Multi-Tenancy (Schema per Tenant), Key multi-tenancy classes, Request flow, Tenant Lifecycle, map, multi_tenant_identifier_resolver, org.hibernate.context.spi.CurrentTenantIdentifierResolver (+6 more)

### Community 2 - "TenantMigrationServiceImpl"
Cohesion: 0.33
Nodes (5): Override, TenantMigrationServiceImpl, TenantBatchMigrationResult, TenantMigrationResult, TenantMigrationService

### Community 3 - "Tenant"
Cohesion: 0.11
Nodes (29): collection, column, createdby, createddate, enumerated, enumtype, fetchtype, foreignkey (+21 more)

### Community 4 - "org.junit.jupiter.api.DisplayName"
Cohesion: 0.15
Nodes (11): org.junit.jupiter.api.AfterEach, org.junit.jupiter.api.DisplayName, org.junit.jupiter.api.extension.ExtendWith, org.junit.jupiter.api.Test, org.mockito.junit.jupiter.MockitoExtension, org.springframework.boot.test.context.SpringBootTest, Test Coverage Highlights, ApplicationTests (+3 more)

### Community 5 - "TenantCodeValidatorTest.java"
Cohesion: 0.31
Nodes (5): org.junit.jupiter.params.ParameterizedTest, org.junit.jupiter.params.provider.ValueSource, Override, TenantCodeValidatorTest, test

### Community 6 - "TenantServiceTest.java"
Cohesion: 0.14
Nodes (15): any, assertions, injectmocks, mock, mockito, org.junit.jupiter.api.BeforeEach, registertenantrequest, spy (+7 more)

### Community 8 - "UserRole"
Cohesion: 0.12
Nodes (15): AbstractEntity (base for all tenant-scoped entities), Entities, Enums, AbstractEntity (base for all tenant-scoped entities), Entities, Enums, TypeMvt, IN (+7 more)

### Community 9 - "TenantServiceImpl.java"
Cohesion: 0.08
Nodes (23): classpathresource, completablefuture, executors, executorservice, flyway, invalidrequestexception, io.micrometer.core.instrument.MeterRegistry, java.util.regex.Pattern (+15 more)

### Community 10 - "lombok.RequiredArgsConstructor"
Cohesion: 0.09
Nodes (29): io.swagger.v3.oas.annotations.tags.Tag, lombok.RequiredArgsConstructor, notnull, org.springframework.http.ResponseEntity, org.springframework.security.access.prepost.PreAuthorize, org.springframework.security.core.userdetails.UserDetailsService, org.springframework.web.bind.annotation.DeleteMapping, org.springframework.web.bind.annotation.GetMapping (+21 more)

### Community 11 - "lombok.Getter"
Cohesion: 0.19
Nodes (24): bigdecimal, com.fasterxml.jackson.annotation.JsonInclude, lombok.AllArgsConstructor, lombok.Builder, lombok.Getter, lombok.NoArgsConstructor, lombok.Setter, notblank (+16 more)

### Community 12 - "Java Migration Skill"
Cohesion: 0.05
Nodes (42): Add Missing Dependencies (Maven), Breaking Changes, Breaking Changes, Breaking Changes, Check for Unsafe Usage, Common Migration Issues, Dependency Updates (Spring Boot 3.x), During Migration (+34 more)

### Community 13 - "saas-app-ui"
Cohesion: 0.05
Nodes (41): build, serve, test, builder, configurations, defaultConfiguration, options, cli (+33 more)

### Community 14 - "Security Audit Skill"
Cohesion: 0.05
Nodes (40): Allowlist vs Blocklist, Authentication & Authorization, Authorization Checks, Avoid Java Serialization, Bean Validation (JSR 380), Code Review, Configuration, Configuration Files (+32 more)

### Community 15 - "Issue Triage Skill"
Cohesion: 0.05
Nodes (39): 1. Fetch Issues, 2. Categorize Each Issue, 3. Priority Assessment, 4. Response Templates, Acknowledged Bug, Anti-patterns, Auto-close stale issues, Automation Opportunities (+31 more)

### Community 16 - "functions.ts"
Cohesion: 0.18
Nodes (32): Interpreter guard for subcommands, login(), register(), createCategory(), deleteCategory(), findAllCategories(), findCategoryById(), updateCategory() (+24 more)

### Community 17 - "StockMvtResponse"
Cohesion: 0.24
Nodes (6): StockMvtMapper, StockMvtRequest, StockMvtResponse, Override, StockMvtServiceImpl, StockMvtService

### Community 18 - "Clean Code Skill"
Cohesion: 0.05
Nodes (37): Avoid Flag Arguments, Avoid Obvious Comments, Booleans, Classes, Clean Code Checklist, Clean Code Skill, Comments, Common Code Smells (+29 more)

### Community 19 - "TenantService"
Cohesion: 0.18
Nodes (7): ActivateTenant$Params, ApproveTenant$Params, DeactivateTenant$Params, FindAllTenants$Params, SuspendTenant$Params, TenantService, Injectable

### Community 20 - "JPA Patterns Skill"
Cohesion: 0.06
Nodes (35): 1. Cascade Misuse, 2. Missing Index, 3. toString() with Lazy Fields, Basic Transaction Management, Best Practice: Default to LAZY, Bulk Operations, Common Mistakes, Common Transaction Mistakes (+27 more)

### Community 21 - "Test Quality Skill (JUnit 5 + AssertJ)"
Cohesion: 0.06
Nodes (35): 1. Generate test skeleton first, 2. Implement incrementally, 3. Reuse patterns, After test generation, suggest:, Anti-patterns, AssertJ over standard assertions, AssertJ Power Features, Best Practices Summary (+27 more)

### Community 22 - "Concurrency Review Skill"
Cohesion: 0.06
Nodes (34): 1. Forgetting @EnableAsync, 2. Calling Async from Same Class, 3. @Async on Non-Public Methods, 4. Default Executor Creates Thread Per Task, 5. SecurityContext Not Propagating, Analysis Commands, Choose the Right Collection, Classic Concurrency Issues (+26 more)

### Community 23 - "API Contract Review Skill"
Cohesion: 0.06
Nodes (33): 1. HTTP Semantics, 2. URL Design, 3. Request Handling, 4. Response Design, 5. Error Handling, 6. Compatibility, Anti-Pattern: 200 with Error Body, API Contract Review Skill (+25 more)

### Community 24 - "Performance Smell Detection Skill"
Cohesion: 0.06
Nodes (33): Always Pre-compile in Loops, Analysis Commands, Avoid in Hot Paths: String.format, Boxing/Unboxing, Capacity Hint (Minor Optimization), Collections, 🔴 High Severity (Usually Worth Fixing), 🟢 Low Severity (Nice to Have) (+25 more)

### Community 25 - "DashboardServiceImpl.java"
Cohesion: 0.11
Nodes (17): linkedhashmap, localdate, localdatetime, locale, month, optional, org.springframework.data.domain.Page, org.springframework.data.domain.Pageable (+9 more)

### Community 26 - "TokenService"
Cohesion: 0.07
Nodes (11): For /graphify explain, For /graphify path, graphify reference: query, path, explain, Step 0 — Constrained query expansion (REQUIRED before traversal), Step 1 — Traversal, TokenService, Injectable, AdminDashboard (+3 more)

### Community 27 - "Logging Patterns Skill"
Cohesion: 0.06
Nodes (32): Add User Context, Adding Custom Fields (Logstash Encoder), AI-Friendly Logging, Analyzing Logs (AI/Human), Business Events (INFO), Exception Logging, External Calls (with timing), Flow Steps (for AI tracing) (+24 more)

### Community 28 - "SOLID Principles Skill"
Cohesion: 0.06
Nodes (32): Common OCP Patterns, Common Refactoring Patterns, D - Dependency Inversion Principle (DIP), DIP with Spring, How to Detect DIP Violations, How to Detect ISP Violations, How to Detect LSP Violations, How to Detect OCP Violations (+24 more)

### Community 29 - "Maven Dependency Audit Skill"
Cohesion: 0.06
Nodes (31): 1. Check for Outdated Dependencies, 2. Analyze Dependency Tree, 3. Security Vulnerability Scan, 4. Generate Audit Report, Aggressive (For Active Development), Analyze Unused Dependencies, Audit Workflow, Categorize Updates (+23 more)

### Community 30 - "StrictHttpResponse"
Cohesion: 0.24
Nodes (9): ref_angular_common_http, rxjs, ref_rxjs_operators, CategoryRequest, ProductRequest, UserRequest, ParameterCodecInstance, ParameterOptions (+1 more)

### Community 31 - "Guide — Stock Management SaaS Multi-Tenant"
Cohesion: 0.06
Nodes (31): 1. Prérequis, 2.1 Configurer l'environnement, 2.2 Démarrer PostgreSQL, 2.3 Démarrer l'application, 2.4 Vérifier que tout fonctionne, 2. Lancer le projet, 3. Différence entre Tenant et User, 4. Flow d'authentification complet (+23 more)

### Community 32 - "User"
Cohesion: 0.16
Nodes (7): org.springframework.security.core.GrantedAuthority, org.springframework.security.core.userdetails.UserDetails, Pourquoi les données admin sont dans Tenant ?, Override, Override, User, UserMapper

### Community 33 - "Spring Boot Patterns Skill"
Cohesion: 0.07
Nodes (29): ❌ Anti-patterns, Application Properties, Common Annotations Quick Reference, Configuration Patterns, Configuration Properties Class, Controller Best Practices, Controller Patterns, Controller Test (MockMvc) (+21 more)

### Community 34 - "user.service.ts"
Cohesion: 0.15
Nodes (11): CreateUser$Params, DeleteUser$Params, DisableUser$Params, EnableUser$Params, GetAllUsers$Params, GetUserById$Params, UpdateUser$Params, PageResponseUserResponse (+3 more)

### Community 35 - "Git Commit Message Skill"
Cohesion: 0.07
Nodes (28): Adding new functionality, Anti-patterns, Body (optional but recommended), Breaking Changes, Build/dependency update, Common Patterns for Java Projects, Dependency updates, Documentation improvements (+20 more)

### Community 36 - "stock-mvt.service.ts"
Cohesion: 0.18
Nodes (10): CreateStockMvt$Params, DeleteStockMvt$Params, FindAllStockMvtsByProductId$Params, FindAllStockMvts$Params, FindStockMvtById$Params, UpdateStockMvt$Params, PageResponseStockMvtResponse, StockMvtResponse (+2 more)

### Community 37 - "category.service.ts"
Cohesion: 0.11
Nodes (14): ApiConfiguration, provideApiConfiguration(), Injectable, BaseService, Injectable, CreateCategory$Params, DeleteCategory$Params, FindAllCategories$Params (+6 more)

### Community 38 - "MultiTenantConnectionProviderImpl"
Cohesion: 0.20
Nodes (7): java.sql.Connection, multitenancysettings, org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider, org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer, sqlexception, Override, MultiTenantConnectionProviderImpl

### Community 39 - "Changelog Generator Skill"
Cohesion: 0.07
Nodes (26): 1. Check CLAUDE.md (if exists), 2. Fallback: Detect from git tags, 3. Fallback: Detect from CHANGELOG.md, 4. Last resort: Ask user, Breaking change, Changelog Generator Skill, Example: Full Workflow, Format A: Keep a Changelog (h2 versions) (+18 more)

### Community 40 - "JwtTokenService.java"
Cohesion: 0.15
Nodes (11): base64, date, expiredjwtexception, inputstream, jwts, keyfactory, malformedjwtexception, nonnull (+3 more)

### Community 41 - "Architecture Review Skill"
Cohesion: 0.08
Nodes (24): 1. Package Structure, 1. The Big Ball of Mud, 2. Dependency Direction, 2. The Util Dumping Ground, 3. Anemic Domain Model, 3. Layer Boundaries, 4. Framework Coupling in Domain, 4. Module Boundaries (+16 more)

### Community 43 - "What You Must Do When Invoked"
Cohesion: 0.08
Nodes (23): For /graphify add and --watch, For /graphify query, For the commit hook and native CLAUDE.md integration, For --update and --cluster-only, /graphify, Honesty Rules, Part A - Structural extraction for code files, Part B - Semantic extraction (parallel subagents) (+15 more)

### Community 44 - "@angular/core"
Cohesion: 0.22
Nodes (11): @angular/common, @angular/core, @angular/router, ref_primeng_dialog, ref_primeng_table, ref_primeng_toast, ref_primeng_tooltip, authGuard() (+3 more)

### Community 45 - "manage-product.ts"
Cohesion: 0.39
Nodes (9): @angular/forms, ref_primeng_api, ref_primeng_button, ref_primeng_divider, ref_primeng_floatlabel, ref_primeng_inputtext, ref_primeng_select, ErrorResponse (+1 more)

### Community 46 - "Statistics"
Cohesion: 0.13
Nodes (6): DashboardStatsResponse, MonthlyTrendEntry, DashboardService, Injectable, Statistics, Component

### Community 47 - "org.springframework.stereotype.Component"
Cohesion: 0.24
Nodes (4): org.springframework.stereotype.Component, ProductMapper, Override, ProductServiceImpl

### Community 48 - "JwtAuthenticationFilter.java"
Cohesion: 0.14
Nodes (16): Security, collections, ioexception, jakarta.servlet.FilterChain, jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse, org.springframework.web.filter.OncePerRequestFilter, servletexception (+8 more)

### Community 49 - "package.json"
Cohesion: 0.07
Nodes (26): @angular/build, @angular/cli, @angular/compiler, @angular/compiler-cli, @auth0/angular-jwt, jsdom, ng-openapi-gen, prettier (+18 more)

### Community 50 - "product.service.ts"
Cohesion: 0.18
Nodes (9): CreateProduct$Params, DeleteProduct$Params, FindAllProducts$Params, FindProductById$Params, UpdateProduct$Params, PageResponseProductResponse, ProductResponse, ProductService (+1 more)

### Community 51 - "Tenants, Utilisateurs et Admin Plateforme"
Cohesion: 0.06
Nodes (33): 1. Vue d'ensemble, 2. Le Platform Admin, 3. Les Tenants, 4. Les Utilisateurs (Users), 5. Cycle de vie d'un Tenant, 6. Connexion : Platform Admin vs Tenant, 7. Créer le Platform Admin, 8. Flux complet : de l'inscription à la connexion d'un tenant (+25 more)

### Community 52 - "models.ts"
Cohesion: 0.17
Nodes (10): Login$Params, Register$Params, LoginRequest, LoginResponse, PageResponseTenantResponse, RegisterTenantRequest, StockMvtRequest, TenantResponse (+2 more)

### Community 53 - "SecurityConfig.java"
Cohesion: 0.06
Nodes (41): abstracthttpconfigurer, arrays, authentication, bcryptpasswordencoder, concurrentmapcachemanager, corsconfiguration, drivermanager, hikaridatasource (+33 more)

### Community 54 - "CLAUDE.md — Stock Management SaaS Multi-Tenancy"
Cohesion: 0.11
Nodes (18): Agents (`.claude/agents/`), Auth (public), Build & Test, CLAUDE.md — Stock Management SaaS Multi-Tenancy, Commands (`.claude/commands/`), Configuration (.env / env vars), Database Migrations, Docker (+10 more)

### Community 55 - "Design Patterns Skill"
Cohesion: 0.11
Nodes (17): Adapter, Anti-Patterns to Avoid, Behavioral Patterns, Builder, Creational Patterns, Decorator, Design Patterns Skill, Factory Method (+9 more)

### Community 56 - "Review Checklist"
Cohesion: 0.11
Nodes (17): 1. Null Safety, 2. Exception Handling, 3. Collections & Streams, 4. Concurrency, 5. Java Idioms, 6. Resource Management, 7. API Design, 8. Performance Considerations (+9 more)

### Community 57 - ".claude/agents/migration-expert.md"
Cohesion: 0.17
Nodes (11): Ajout d'index, Ajout de colonne, Ajout de contrainte unique, Ajout de foreign key, Colonnes obligatoires pour toute entité tenant (AbstractEntity), Contexte du projet, Nouvelle table (scope tenant), Numérotation des versions (+3 more)

### Community 58 - "stock-mgmt-saas-multi-tenancy/.claude/agents/migration-expert.md"
Cohesion: 0.17
Nodes (11): Ajout d'index, Ajout de colonne, Ajout de contrainte unique, Ajout de foreign key, Colonnes obligatoires pour toute entité tenant (AbstractEntity), Contexte du projet, Nouvelle table (scope tenant), Numérotation des versions (+3 more)

### Community 59 - "TenantContext"
Cohesion: 0.21
Nodes (14): 1. Isolation multi-tenant (CRITIQUE), A. Isolation des tenants (risque SaaS critique), Important Constraints, Ce qui est interdit, 4. Tests d'isolation multi-tenant, 2. Vérifier TenantContext.clear(), 🔄 Request Execution Flow, 1. Isolation multi-tenant (CRITIQUE) (+6 more)

### Community 60 - "lombok.extern.slf4j.Slf4j"
Cohesion: 0.14
Nodes (18): duplicateresourceexception, jakarta.persistence.EntityNotFoundException, lombok.extern.slf4j.Slf4j, org.springframework.stereotype.Service, org.springframework.transaction.annotation.Transactional, page, pagerequest, Category (+10 more)

### Community 61 - "CLAUDE.md — Stock Management SaaS Multi-Tenancy"
Cohesion: 0.11
Nodes (17): Agents (`.claude/agents/`), Auth (public), Build & Test, CLAUDE.md — Stock Management SaaS Multi-Tenancy, Commands (`.claude/commands/`), Configuration (.env / env vars), Database Migrations, Docker (+9 more)

### Community 62 - "UserServiceImpl"
Cohesion: 0.32
Nodes (3): UserRepository, Override, UserServiceImpl

### Community 63 - "GlobalExceptionHandler.java"
Cohesion: 0.16
Nodes (13): arraylist, Code Conventions, fielderror, org.springframework.http.HttpStatus, org.springframework.security.authentication.BadCredentialsException, org.springframework.security.core.userdetails.UsernameNotFoundException, org.springframework.web.bind.annotation.ExceptionHandler, org.springframework.web.bind.annotation.RestControllerAdvice (+5 more)

### Community 64 - "JwtTokenService"
Cohesion: 0.24
Nodes (5): io.jsonwebtoken.Claims, jakarta.annotation.PostConstruct, java.security.PrivateKey, java.security.PublicKey, JwtTokenService

### Community 65 - "Règles de style de code"
Cohesion: 0.20
Nodes (9): DTOs, Entités JPA, Gestion des erreurs, Java & Lombok, Migrations SQL, Nommage, Règles de style de code, Spring (+1 more)

### Community 66 - ".claude/skills/deploy/SKILL.md"
Cohesion: 0.14
Nodes (13): 1. Connect to the VPS, 2. Install Docker, 3. Create deploy user (ne jamais utiliser root en prod), 4. Créer le dossier app + .env, 5. Authentifier Docker au GHCR (pull des images privées), Architecture, `/deploy add-secret`, `/deploy logs <service>` (+5 more)

### Community 67 - "dependencies"
Cohesion: 0.14
Nodes (14): dependencies, @angular/common, @angular/compiler, @angular/core, @angular/forms, @angular/platform-browser, @angular/router, @auth0/angular-jwt (+6 more)

### Community 68 - ".append"
Cohesion: 0.20
Nodes (4): HeaderParameter, Parameter, PathParameter, QueryParameter

### Community 69 - "Concurrency Review Skill"
Cohesion: 0.15
Nodes (12): Classic Issues, Concurrency Review Skill, Example Usage, Key Topics Covered, Modern Java (21/25), References, Related Skills, Severity Levels (+4 more)

### Community 70 - "Skills"
Cohesion: 0.15
Nodes (12): Adding a New Skill, Architecture & Design, Available Skills, Before You Start, Code Quality, Framework & Data, Implementation Steps, Learn More (+4 more)

### Community 71 - "Stratégie de test"
Cohesion: 0.17
Nodes (11): 1. Tests unitaires (services), 2. Tests d'intégration (repository + DB), 3. Tests de controllers (slice test), Ce qu'il faut toujours tester, Ce qu'il ne faut PAS faire, Couverture minimale attendue, Données de test, Nommage des tests (+3 more)

### Community 72 - "Stratégie de test"
Cohesion: 0.17
Nodes (11): 1. Tests unitaires (services), 2. Tests d'intégration (repository + DB), 3. Tests de controllers (slice test), Ce qu'il faut toujours tester, Ce qu'il ne faut PAS faire, Couverture minimale attendue, Données de test, Nommage des tests (+3 more)

### Community 75 - "API Contract Review Skill"
Cohesion: 0.18
Nodes (10): API Contract Review Skill, Audit vs Template, Common Issues Caught, Example Usage, Key Concepts, References, Related Skills, What It Checks (+2 more)

### Community 76 - "Architecture Review Skill"
Cohesion: 0.18
Nodes (10): Architecture Review Skill, Dependency Direction, Example Usage, Key Concepts, Package Strategies, References, Related Skills, What It Checks (+2 more)

### Community 77 - "Performance Smell Detection Skill"
Cohesion: 0.18
Nodes (10): Example Usage, Java Version Awareness, Performance Smell Detection Skill, References, Related Skills, Severity Levels, What It Checks, What It Does (+2 more)

### Community 78 - "Mode `quick` (défaut)"
Cohesion: 0.18
Nodes (10): 1. Vérifier les endpoints publics, 3. Vérifier les mots de passe, 4. Vérifier les requêtes natives, 5. Vérifier .gitignore, Dépendances à vérifier dans pom.xml, Format du rapport quick, Mode `full`, Mode `quick` (défaut) (+2 more)

### Community 79 - "ref_angular_core_testing"
Cohesion: 0.20
Nodes (3): ref_angular_core_testing, ManageStockMvt, Component

### Community 80 - "Mode `quick` (défaut)"
Cohesion: 0.18
Nodes (10): 1. Vérifier les endpoints publics, 3. Vérifier les mots de passe, 4. Vérifier les requêtes natives, 5. Vérifier .gitignore, Dépendances à vérifier dans pom.xml, Format du rapport quick, Mode `full`, Mode `quick` (défaut) (+2 more)

### Community 84 - "angular/SKILL.md"
Cohesion: 0.20
Nodes (9): Absolute Rules, `/angular build`, `/angular component <ComponentName>`, `/angular feature <feature-name>`, `/angular form <ComponentName>`, Angular Material — Quick Reference, `/angular service <ServiceName>`, Environments (+1 more)

### Community 85 - "Règles de style de code"
Cohesion: 0.20
Nodes (9): DTOs, Entités JPA, Gestion des erreurs, Java & Lombok, Migrations SQL, Nommage, Règles de style de code, Spring (+1 more)

### Community 86 - "mvnw"
Cohesion: 0.38
Nodes (8): mvnw script, clean(), die(), exec_maven(), hash_string(), set_java_home(), trim(), verbose()

### Community 89 - "Checklist de revue — applique dans cet ordre"
Cohesion: 0.22
Nodes (8): 2. Sécurité, 3. Conventions de code, 4. JPA / Hibernate, 5. Gestion des exceptions, 6. Migrations Flyway, Checklist de revue — applique dans cet ordre, Contexte du projet, Format de sortie

### Community 90 - "Conventions API REST"
Cohesion: 0.20
Nodes (9): Codes HTTP, Conventions API REST, Documentation OpenAPI, Format des erreurs, Headers, Pagination, Paramètres de chemin, Sécurité des endpoints (+1 more)

### Community 91 - "Clean Code"
Cohesion: 0.22
Nodes (8): Clean Code, Description, Examples, Principles Covered, Related Skills, Resources, Topics, Use Cases

### Community 92 - "Design Patterns"
Cohesion: 0.22
Nodes (8): Description, Design Patterns, Examples, Patterns Covered, Quick Selection Guide, Related Skills, Resources, Use Cases

### Community 93 - "graphify reference: extra exports and benchmark"
Cohesion: 0.22
Nodes (8): graphify reference: extra exports and benchmark, Step 6b - Wiki (only if --wiki flag), Step 7 - Neo4j export (only if --neo4j or --neo4j-push flag), Step 7a - FalkorDB export (only if --falkordb or --falkordb-push flag), Step 7b - SVG export (only if --svg flag), Step 7c - GraphML export (only if --graphml flag), Step 7d - MCP server (only if --mcp flag), Step 8 - Token reduction benchmark (only if total_words > 5000)

### Community 94 - "Java Migration"
Cohesion: 0.22
Nodes (8): Description, Examples, Java Migration, Migration Paths Covered, Notes / Tips, References, Tools Used, Use Cases

### Community 95 - "JPA Patterns"
Cohesion: 0.22
Nodes (8): Common Mistakes Addressed, Description, Examples, JPA Patterns, Related Skills, Resources, Topics Covered, Use Cases

### Community 96 - "Logging Patterns"
Cohesion: 0.22
Nodes (8): Description, Key Insight: JSON for AI, Logging Patterns, Quick Setup (Spring Boot 3.4+), Related Skills, Resources, Topics Covered, Use Cases

### Community 97 - "Checklist de revue — applique dans cet ordre"
Cohesion: 0.22
Nodes (8): 2. Sécurité, 3. Conventions de code, 4. JPA / Hibernate, 5. Gestion des exceptions, 6. Migrations Flyway, Checklist de revue — applique dans cet ordre, Contexte du projet, Format de sortie

### Community 98 - "Conventions API REST"
Cohesion: 0.22
Nodes (8): Codes HTTP, Conventions API REST, Documentation OpenAPI, Headers, Pagination, Paramètres de chemin, Sécurité des endpoints, URL & Routing

### Community 99 - "devDependencies"
Cohesion: 0.22
Nodes (9): devDependencies, @angular/build, @angular/cli, @angular/compiler-cli, jsdom, ng-openapi-gen, prettier, typescript (+1 more)

### Community 101 - ".claude/agents/test-writer.md"
Cohesion: 0.25
Nodes (7): Checklist de couverture à viser, Contexte du projet, Conventions de nommage des méthodes de test, Patron pour tests d'ISOLATION MULTI-TENANT, Patron pour tests de CONTROLLER, Patron pour tests de SERVICE, Règle fondamentale

### Community 102 - "Changelog Generator"
Cohesion: 0.25
Nodes (7): Changelog Generator, Description, Detection Priority, Examples, Key Features, Notes / Tips, Use Cases

### Community 103 - "Security Audit"
Cohesion: 0.25
Nodes (7): Description, OWASP Top 10 Coverage, Related Skills, Resources, Security Audit, Topics Covered, Use Cases

### Community 104 - "SOLID Principles"
Cohesion: 0.25
Nodes (7): Description, Examples, Principles Covered, Related Skills, Resources, SOLID Principles, Use Cases

### Community 105 - "stock-mgmt-saas-multi-tenancy/.claude/agents/test-writer.md"
Cohesion: 0.25
Nodes (7): Checklist de couverture à viser, Contexte du projet, Conventions de nommage des méthodes de test, Patron pour tests d'ISOLATION MULTI-TENANT, Patron pour tests de CONTROLLER, Patron pour tests de SERVICE, Règle fondamentale

### Community 106 - "Skill : deploy"
Cohesion: 0.25
Nodes (7): Checklist avant déploiement, Mode `local` (défaut), Mode `prod`, Rollback, Skill : deploy, Usage, Étapes

### Community 107 - "SaasAppUi"
Cohesion: 0.25
Nodes (7): Additional Resources, Building, Code scaffolding, Development server, Running end-to-end tests, Running unit tests, SaasAppUi

### Community 109 - "Java Code Review"
Cohesion: 0.29
Nodes (6): Checklist Categories, Description, Examples, Java Code Review, Notes / Tips, Use Cases

### Community 110 - "Maven Dependency Audit"
Cohesion: 0.29
Nodes (6): Description, Examples, Maven Dependency Audit, Notes / Tips, Tools Used, Use Cases

### Community 111 - "Spring Boot Patterns"
Cohesion: 0.29
Nodes (6): Description, Examples, Notes / Tips, Patterns Covered, Spring Boot Patterns, Use Cases

### Community 112 - "app.config.ts"
Cohesion: 0.20
Nodes (8): @angular/platform-browser, ref_primeng_config, ref_primeuix_themes_aura, App, appConfig, routes, Component, httpInterceptorInterceptor()

### Community 113 - "TenantSchemaResolver"
Cohesion: 0.13
Nodes (15): JWT et tenant claim, Migrations tenant, Repositories — Utilisation correcte, Règles d'isolation multi-tenant, Schema naming — Convention, TenantContext — Règles d'utilisation, org.springframework.cache.annotation.Cacheable, org.springframework.jdbc.core.JdbcTemplate (+7 more)

### Community 116 - "Périmètre de l'audit"
Cohesion: 0.33
Nodes (5): B. Authentification & JWT, C. OWASP Top 10 (contexte Spring Boot), D. Exposition des données, Format de sortie, Périmètre de l'audit

### Community 117 - "/project:deploy"
Cohesion: 0.33
Nodes (5): Arguments, Instructions, /project:deploy, Safety Rules, Usage

### Community 118 - "/project:fix-issue"
Cohesion: 0.33
Nodes (5): Arguments, Instructions, /project:fix-issue, Rules, Usage

### Community 119 - "Diagnostic des erreurs Flyway courantes"
Cohesion: 0.40
Nodes (5): Diagnostic des erreurs Flyway courantes, `Found more than one migration with version X`, `relation "xxx" already exists`, `Schema "schema_xxx" does not exist`, `Validate failed: Migration checksum mismatch`

### Community 120 - "Git Commit Messages"
Cohesion: 0.33
Nodes (5): Description, Examples, Git Commit Messages, Notes / Tips, Use Cases

### Community 121 - "Issue Triage"
Cohesion: 0.33
Nodes (5): Description, Examples, Issue Triage, Notes / Tips, Use Cases

### Community 122 - "Test Quality (JUnit 5 + AssertJ)"
Cohesion: 0.33
Nodes (5): Description, Examples, Notes / Tips, Test Quality (JUnit 5 + AssertJ), Use Cases

### Community 123 - "Application.java"
Cohesion: 0.47
Nodes (4): org.springframework.boot.autoconfigure.SpringBootApplication, org.springframework.data.jpa.repository.config.EnableJpaAuditing, springapplication, Application

### Community 124 - "Périmètre de l'audit"
Cohesion: 0.33
Nodes (5): B. Authentification & JWT, C. OWASP Top 10 (contexte Spring Boot), D. Exposition des données, Format de sortie, Périmètre de l'audit

### Community 125 - "/project:deploy"
Cohesion: 0.33
Nodes (5): Arguments, Instructions, /project:deploy, Safety Rules, Usage

### Community 126 - "/project:fix-issue"
Cohesion: 0.33
Nodes (5): Arguments, Instructions, /project:fix-issue, Rules, Usage

### Community 127 - "/project:review"
Cohesion: 0.40
Nodes (4): Arguments, Instructions, /project:review, Usage

### Community 128 - "Diagnostic des erreurs Flyway courantes"
Cohesion: 0.40
Nodes (5): Diagnostic des erreurs Flyway courantes, `Found more than one migration with version X`, `relation "xxx" already exists`, `Schema "schema_xxx" does not exist`, `Validate failed: Migration checksum mismatch`

### Community 129 - "/project:review"
Cohesion: 0.40
Nodes (4): Arguments, Instructions, /project:review, Usage

### Community 133 - "📦 Stock Management SaaS Core"
Cohesion: 0.11
Nodes (17): 1. Prerequisites, 2. Database Setup, 3. Application Configuration, 4. Build and Run, 🔑 Auth Endpoints (Public), 📄 License, 🏗️ Multi-Tenancy Architecture, 📋 Overview (+9 more)

### Community 136 - "graphify reference: add a URL and watch a folder"
Cohesion: 0.50
Nodes (3): For /graphify add, For --watch, graphify reference: add a URL and watch a folder

### Community 137 - "graphify reference: commit hook and native CLAUDE.md integration"
Cohesion: 0.50
Nodes (3): For git commit hook, For native CLAUDE.md integration, graphify reference: commit hook and native CLAUDE.md integration

### Community 138 - "graphify reference: incremental update and cluster-only"
Cohesion: 0.50
Nodes (3): For --cluster-only, For --update (incremental re-extraction), graphify reference: incremental update and cluster-only

## Knowledge Gaps
- **949 isolated node(s):** `📋 Overview`, `1. Prerequisites`, `2. Database Setup`, `3. Application Configuration`, `4. Build and Run` (+944 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 1205 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **29 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TenantContext` connect `TenantContext` to `CurrentTenantIdentifierResolverImpl`, `org.junit.jupiter.api.DisplayName`, `Stratégie de test`, `Stratégie de test`, `JwtAuthenticationFilter.java`, `TenantSchemaResolver`, `Tenants, Utilisateurs et Admin Plateforme`, `lombok.extern.slf4j.Slf4j`, `UserServiceImpl`?**
  _High betweenness centrality (0.016) - this node is a cross-community bridge._
- **Why does `@angular/core` connect `@angular/core` to `user.service.ts`, `stock-mvt.service.ts`, `category.service.ts`, `manage-product.ts`, `Statistics`, `ref_angular_core_testing`, `app.config.ts`, `package.json`, `product.service.ts`, `home.ts`, `models.ts`, `StrictHttpResponse`?**
  _High betweenness centrality (0.013) - this node is a cross-community bridge._
- **Why does `User` connect `User` to `Tenant`, `UserRole`, `TenantServiceImpl.java`, `lombok.Getter`, `SecurityConfig.java`, `DashboardServiceImpl.java`, `lombok.extern.slf4j.Slf4j`, `UserServiceImpl`, `Guide — Stock Management SaaS Multi-Tenant`?**
  _High betweenness centrality (0.013) - this node is a cross-community bridge._
- **What connects `📋 Overview`, `1. Prerequisites`, `2. Database Setup` to the rest of the system?**
  _949 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `CurrentTenantIdentifierResolverImpl` be split into smaller, more focused modules?**
  _Cohesion score 0.1323529411764706 - nodes in this community are weakly interconnected._
- **Should `Tenant` be split into smaller, more focused modules?**
  _Cohesion score 0.11463414634146342 - nodes in this community are weakly interconnected._
- **Should `org.junit.jupiter.api.DisplayName` be split into smaller, more focused modules?**
  _Cohesion score 0.14814814814814814 - nodes in this community are weakly interconnected._