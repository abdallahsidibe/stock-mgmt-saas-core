# Graph Report - saas-multi-tenancy  (2026-09-19)

## Corpus Check
- 315 files · ~97,196 words
- Verdict: corpus is large enough that graph structure adds value.
- Unclassified: 30 file(s) not represented in the graph (top: .scss 17, (none) 8, .graphify-bak 1)

## Summary
- 2405 nodes · 4880 edges · 154 communities (128 shown, 26 thin omitted)
- Extraction: 95% EXTRACTED · 5% INFERRED · 0% AMBIGUOUS · INFERRED: 234 edges (avg confidence: 0.87)
- Token cost: 0 input · 0 output

## Graph Freshness
- Built from commit: `d88f150a`
- Run `git rev-parse HEAD` and compare to check if the graph is stale.
- Run `graphify update .` after code changes (no API cost).

## Community Hubs (Navigation)
- TenantServiceImpl
- ProvisioningServiceImpl
- TenantMigrationServiceImpl
- Tenant
- org.junit.jupiter.api.DisplayName
- TenantCodeValidatorTest.java
- TenantServiceTest.java
- com.tech:saas-app
- TenantServiceImpl.java
- TenantMigrationServiceImpl.java
- lombok.RequiredArgsConstructor
- lombok.Getter
- Java Migration Skill
- saas-app-ui
- Security Audit Skill
- Issue Triage Skill
- functions.ts
- PageResponse
- Clean Code Skill
- product.service.ts
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
- stock-mvt.ts
- What You Must Do When Invoked
- @angular/core
- manage-product.ts
- Statistics
- ProductServiceImpl
- JwtAuthenticationFilter.java
- package.json
- ProductService
- Tenants, Utilisateurs et Admin Plateforme
- models.ts
- SecurityConfig.java
- CLAUDE.md — Stock Management SaaS Multi-Tenancy
- Design Patterns Skill
- Review Checklist
- .claude/agents/migration-expert.md
- stock-mgmt-saas-multi-tenancy/.claude/agents/migration-expert.md
- TenantContext
- org.springframework.stereotype.Component
- CLAUDE.md — Stock Management SaaS Multi-Tenancy
- UserServiceImpl
- GlobalExceptionHandler.java
- DatabaseInitializerConfig.java
- org.springframework.context.annotation.Bean
- .claude/skills/deploy/SKILL.md
- dependencies
- .append
- Concurrency Review Skill
- Skills
- Stratégie de test
- Stratégie de test
- StockMvt
- AuthenticationServiceImpl.java
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
- app.ts
- Règles d'isolation multi-tenant
- scripts
- home.ts
- Périmètre de l'audit
- /project:deploy
- /project:fix-issue
- Règles d'isolation multi-tenant
- Git Commit Messages
- Issue Triage
- Test Quality (JUnit 5 + AssertJ)
- Application.java
- Périmètre de l'audit
- /project:deploy
- /project:fix-issue
- /project:review
- Écosystème Claude Code — `.claude/`
- /project:review
- 2. Le Platform Admin
- 6. Connexion : Platform Admin vs Tenant
- 8. Flux complet : de l'inscription à la connexion d'un tenant
- Getting Started
- ParameterCodec
- REST API — Base path `/api/v1`
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
3. `rxjs` - 39 edges
4. `PageResponse` - 39 edges
5. `TokenService` - 36 edges
6. `@angular/core` - 33 edges
7. `TenantContext` - 31 edges
8. `Tenant` - 30 edges
9. `User` - 29 edges
10. `@angular/router` - 25 edges

## Surprising Connections (you probably didn't know these)
- `Ce qu'il faut toujours tester` --references--> `PageResponse`  [INFERRED]
  .claude/rules/testing.md → stock-mgmt-saas-multi-tenancy/src/main/java/com/tech/saas/common/PageResponse.java
- `2. Tests d'intégration (repository + DB)` --references--> `TenantContext`  [INFERRED]
  .claude/rules/testing.md → stock-mgmt-saas-multi-tenancy/src/main/java/com/tech/saas/config/TenantContext.java
- `Ce qu'il ne faut PAS faire` --references--> `TenantContext`  [INFERRED]
  .claude/rules/testing.md → stock-mgmt-saas-multi-tenancy/src/main/java/com/tech/saas/config/TenantContext.java
- `3. Conventions de code` --references--> `AbstractEntity`  [INFERRED]
  .claude/agents/code-reviewer.md → stock-mgmt-saas-multi-tenancy/src/main/java/com/tech/saas/entities/AbstractEntity.java
- `Entités JPA` --references--> `AbstractEntity`  [INFERRED]
  .claude/rules/code-style.md → stock-mgmt-saas-multi-tenancy/src/main/java/com/tech/saas/entities/AbstractEntity.java

## Import Cycles
- None detected.

## Communities (154 total, 26 thin omitted)

### Community 0 - "TenantServiceImpl"
Cohesion: 0.16
Nodes (8): Provisioning — Ordre des opérations, Provisioning — Ordre des opérations, TenantMapper, TenantRepository, Override, TenantServiceImpl, ProvisioningService, TenantServiceTest

### Community 1 - "ProvisioningServiceImpl"
Cohesion: 0.13
Nodes (14): Architecture — Multi-Tenancy (Schema per Tenant), Key multi-tenancy classes, Request flow, Schema naming — Convention, Tenant Lifecycle, org.springframework.cache.annotation.Cacheable, org.springframework.jdbc.core.JdbcTemplate, Architecture — Multi-Tenancy (Schema per Tenant) (+6 more)

### Community 2 - "TenantMigrationServiceImpl"
Cohesion: 0.33
Nodes (5): Override, TenantMigrationServiceImpl, TenantBatchMigrationResult, TenantMigrationResult, TenantMigrationService

### Community 3 - "Tenant"
Cohesion: 0.10
Nodes (31): collection, column, createdby, createddate, enumerated, enumtype, fetchtype, foreignkey (+23 more)

### Community 4 - "org.junit.jupiter.api.DisplayName"
Cohesion: 0.18
Nodes (6): org.junit.jupiter.api.AfterEach, org.junit.jupiter.api.DisplayName, org.junit.jupiter.api.Test, org.springframework.boot.test.context.SpringBootTest, ApplicationTests, TenantMultiTenancyIntegrationTest

### Community 5 - "TenantCodeValidatorTest.java"
Cohesion: 0.42
Nodes (4): org.junit.jupiter.params.ParameterizedTest, org.junit.jupiter.params.provider.ValueSource, TenantCodeValidatorTest, test

### Community 6 - "TenantServiceTest.java"
Cohesion: 0.13
Nodes (19): any, assertions, injectmocks, mock, mockito, org.junit.jupiter.api.BeforeEach, org.junit.jupiter.api.extension.ExtendWith, org.mockito.junit.jupiter.MockitoExtension (+11 more)

### Community 8 - "TenantServiceImpl.java"
Cohesion: 0.14
Nodes (16): duplicateresourceexception, invalidrequestexception, jakarta.persistence.EntityNotFoundException, lombok.extern.slf4j.Slf4j, optional, org.springframework.security.crypto.password.PasswordEncoder, org.springframework.stereotype.Service, org.springframework.transaction.annotation.Transactional (+8 more)

### Community 9 - "TenantMigrationServiceImpl.java"
Cohesion: 0.12
Nodes (17): classpathresource, completablefuture, executors, executorservice, flyway, io.micrometer.core.instrument.MeterRegistry, java.util.regex.Pattern, javax.sql.DataSource (+9 more)

### Community 10 - "lombok.RequiredArgsConstructor"
Cohesion: 0.10
Nodes (27): io.swagger.v3.oas.annotations.tags.Tag, lombok.RequiredArgsConstructor, notnull, org.springframework.http.ResponseEntity, org.springframework.security.access.prepost.PreAuthorize, org.springframework.web.bind.annotation.DeleteMapping, org.springframework.web.bind.annotation.GetMapping, org.springframework.web.bind.annotation.PatchMapping (+19 more)

### Community 11 - "lombok.Getter"
Cohesion: 0.19
Nodes (23): bigdecimal, com.fasterxml.jackson.annotation.JsonInclude, lombok.AllArgsConstructor, lombok.Builder, lombok.Getter, lombok.NoArgsConstructor, lombok.Setter, notblank (+15 more)

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

### Community 17 - "PageResponse"
Cohesion: 0.11
Nodes (13): PageResponse, StockMvt, TypeMvt, IN, OUT, StockMvtMapper, StockMvtRepository, StockMvtRequest (+5 more)

### Community 18 - "Clean Code Skill"
Cohesion: 0.05
Nodes (37): Avoid Flag Arguments, Avoid Obvious Comments, Booleans, Classes, Clean Code Checklist, Clean Code Skill, Comments, Common Code Smells (+29 more)

### Community 19 - "product.service.ts"
Cohesion: 0.11
Nodes (15): ApiConfiguration, provideApiConfiguration(), Injectable, BaseService, Injectable, Register$Params, ActivateTenant$Params, ApproveTenant$Params (+7 more)

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
Nodes (17): arraylist, linkedhashmap, localdate, localdatetime, locale, month, org.springframework.data.domain.Page, org.springframework.data.domain.Pageable (+9 more)

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
Cohesion: 0.30
Nodes (6): ref_angular_common_http, rxjs, ref_rxjs_operators, ParameterCodecInstance, ParameterOptions, StrictHttpResponse

### Community 31 - "Guide — Stock Management SaaS Multi-Tenant"
Cohesion: 0.06
Nodes (31): 1. Prérequis, 2.1 Configurer l'environnement, 2.2 Démarrer PostgreSQL, 2.3 Démarrer l'application, 2.4 Vérifier que tout fonctionne, 2. Lancer le projet, 3. Différence entre Tenant et User, 4. Flow d'authentification complet (+23 more)

### Community 32 - "User"
Cohesion: 0.09
Nodes (15): org.springframework.security.core.GrantedAuthority, org.springframework.security.core.userdetails.UserDetailsService, Override, Override, User, UserRole, ROLE_ADMINISTRATOR, ROLE_COMPANY_ADMIN (+7 more)

### Community 33 - "Spring Boot Patterns Skill"
Cohesion: 0.07
Nodes (29): ❌ Anti-patterns, Application Properties, Common Annotations Quick Reference, Configuration Patterns, Configuration Properties Class, Controller Best Practices, Controller Patterns, Controller Test (MockMvc) (+21 more)

### Community 34 - "user.service.ts"
Cohesion: 0.14
Nodes (11): CreateUser$Params, DeleteUser$Params, DisableUser$Params, EnableUser$Params, GetAllUsers$Params, GetUserById$Params, UpdateUser$Params, PageResponseUserResponse (+3 more)

### Community 35 - "Git Commit Message Skill"
Cohesion: 0.07
Nodes (28): Adding new functionality, Anti-patterns, Body (optional but recommended), Breaking Changes, Build/dependency update, Common Patterns for Java Projects, Dependency updates, Documentation improvements (+20 more)

### Community 36 - "stock-mvt.service.ts"
Cohesion: 0.16
Nodes (10): CreateStockMvt$Params, DeleteStockMvt$Params, FindAllStockMvtsByProductId$Params, FindAllStockMvts$Params, FindStockMvtById$Params, UpdateStockMvt$Params, PageResponseStockMvtResponse, StockMvtResponse (+2 more)

### Community 37 - "category.service.ts"
Cohesion: 0.15
Nodes (10): CreateCategory$Params, DeleteCategory$Params, FindAllCategories$Params, FindCategoryById$Params, UpdateCategory$Params, CategoryRequest, CategoryResponse, PageResponseCategoryResponse (+2 more)

### Community 38 - "MultiTenantConnectionProviderImpl"
Cohesion: 0.13
Nodes (13): availablesettings, java.sql.Connection, map, multi_tenant_identifier_resolver, multitenancysettings, org.hibernate.context.spi.CurrentTenantIdentifierResolver, org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider, org.springframework.boot.hibernate.autoconfigure.HibernatePropertiesCustomizer (+5 more)

### Community 39 - "Changelog Generator Skill"
Cohesion: 0.07
Nodes (26): 1. Check CLAUDE.md (if exists), 2. Fallback: Detect from git tags, 3. Fallback: Detect from CHANGELOG.md, 4. Last resort: Ask user, Breaking change, Changelog Generator Skill, Example: Full Workflow, Format A: Keep a Changelog (h2 versions) (+18 more)

### Community 40 - "JwtTokenService.java"
Cohesion: 0.11
Nodes (16): base64, date, expiredjwtexception, inputstream, io.jsonwebtoken.Claims, jakarta.annotation.PostConstruct, java.security.PrivateKey, java.security.PublicKey (+8 more)

### Community 41 - "Architecture Review Skill"
Cohesion: 0.08
Nodes (24): 1. Package Structure, 1. The Big Ball of Mud, 2. Dependency Direction, 2. The Util Dumping Ground, 3. Anemic Domain Model, 3. Layer Boundaries, 4. Framework Coupling in Domain, 4. Module Boundaries (+16 more)

### Community 42 - "stock-mvt.ts"
Cohesion: 0.18
Nodes (10): @angular/common, ref_primeng_api, ref_primeng_dialog, ref_primeng_table, ref_primeng_toast, ref_primeng_tooltip, PageResponseProductResponse, ProductResponse (+2 more)

### Community 43 - "What You Must Do When Invoked"
Cohesion: 0.08
Nodes (23): For /graphify add and --watch, For /graphify query, For the commit hook and native CLAUDE.md integration, For --update and --cluster-only, /graphify, Honesty Rules, Part A - Structural extraction for code files, Part B - Semantic extraction (parallel subagents) (+15 more)

### Community 44 - "@angular/core"
Cohesion: 0.21
Nodes (10): @angular/core, @angular/router, ref_primeng_config, ref_primeuix_themes_aura, routes, authGuard(), platformAdminGuard(), tenantAdminGuard() (+2 more)

### Community 45 - "manage-product.ts"
Cohesion: 0.21
Nodes (10): @angular/forms, ref_primeng_button, ref_primeng_divider, ref_primeng_floatlabel, ref_primeng_inputtext, ref_primeng_select, Register, Component (+2 more)

### Community 46 - "Statistics"
Cohesion: 0.13
Nodes (6): DashboardStatsResponse, MonthlyTrendEntry, DashboardService, Injectable, Statistics, Component

### Community 47 - "ProductServiceImpl"
Cohesion: 0.14
Nodes (12): DTOs, Entités JPA, Gestion des erreurs, Java & Lombok, Migrations SQL, Nommage, Règles de style de code, Spring (+4 more)

### Community 48 - "JwtAuthenticationFilter.java"
Cohesion: 0.13
Nodes (16): Security, collections, ioexception, jakarta.servlet.FilterChain, jakarta.servlet.http.HttpServletRequest, jakarta.servlet.http.HttpServletResponse, org.springframework.web.filter.OncePerRequestFilter, servletexception (+8 more)

### Community 49 - "package.json"
Cohesion: 0.10
Nodes (19): @angular/build, @angular/cli, @angular/compiler, @angular/compiler-cli, @auth0/angular-jwt, jsdom, ng-openapi-gen, prettier (+11 more)

### Community 50 - "ProductService"
Cohesion: 0.16
Nodes (8): CreateProduct$Params, DeleteProduct$Params, FindAllProducts$Params, FindProductById$Params, UpdateProduct$Params, ProductRequest, ProductService, Injectable

### Community 51 - "Tenants, Utilisateurs et Admin Plateforme"
Cohesion: 0.11
Nodes (18): 1. Vue d'ensemble, 3. Les Tenants, 4. Les Utilisateurs (Users), 5. Cycle de vie d'un Tenant, 7. Créer le Platform Admin, 9. Résumé des rôles et permissions, Ce qui se passe lors de l'approbation (`/approve`), Connexion après démarrage (+10 more)

### Community 52 - "models.ts"
Cohesion: 0.20
Nodes (8): Login$Params, LoginRequest, LoginResponse, PageResponseTenantResponse, RegisterTenantRequest, StockMvtRequest, TenantResponse, UserRequest

### Community 53 - "SecurityConfig.java"
Cohesion: 0.16
Nodes (14): abstracthttpconfigurer, arrays, corsconfiguration, httpmethod, org.springframework.boot.web.servlet.FilterRegistrationBean, org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity, org.springframework.security.config.annotation.web.builders.HttpSecurity, org.springframework.security.config.annotation.web.configuration.EnableWebSecurity (+6 more)

### Community 54 - "CLAUDE.md — Stock Management SaaS Multi-Tenancy"
Cohesion: 0.11
Nodes (17): AbstractEntity (base for all tenant-scoped entities), Agents (`.claude/agents/`), Build & Test, CLAUDE.md — Stock Management SaaS Multi-Tenancy, Commands (`.claude/commands/`), Configuration (.env / env vars), Database Migrations, Docker (+9 more)

### Community 55 - "Design Patterns Skill"
Cohesion: 0.11
Nodes (17): Adapter, Anti-Patterns to Avoid, Behavioral Patterns, Builder, Creational Patterns, Decorator, Design Patterns Skill, Factory Method (+9 more)

### Community 56 - "Review Checklist"
Cohesion: 0.11
Nodes (17): 1. Null Safety, 2. Exception Handling, 3. Collections & Streams, 4. Concurrency, 5. Java Idioms, 6. Resource Management, 7. API Design, 8. Performance Considerations (+9 more)

### Community 57 - ".claude/agents/migration-expert.md"
Cohesion: 0.12
Nodes (16): Ajout d'index, Ajout de colonne, Ajout de contrainte unique, Ajout de foreign key, Colonnes obligatoires pour toute entité tenant (AbstractEntity), Contexte du projet, Diagnostic des erreurs Flyway courantes, `Found more than one migration with version X` (+8 more)

### Community 58 - "stock-mgmt-saas-multi-tenancy/.claude/agents/migration-expert.md"
Cohesion: 0.12
Nodes (16): Ajout d'index, Ajout de colonne, Ajout de contrainte unique, Ajout de foreign key, Colonnes obligatoires pour toute entité tenant (AbstractEntity), Contexte du projet, Diagnostic des erreurs Flyway courantes, `Found more than one migration with version X` (+8 more)

### Community 59 - "TenantContext"
Cohesion: 0.23
Nodes (13): 1. Isolation multi-tenant (CRITIQUE), A. Isolation des tenants (risque SaaS critique), Important Constraints, Ce qui est interdit, 4. Tests d'isolation multi-tenant, 2. Vérifier TenantContext.clear(), 1. Isolation multi-tenant (CRITIQUE), A. Isolation des tenants (risque SaaS critique) (+5 more)

### Community 60 - "org.springframework.stereotype.Component"
Cohesion: 0.23
Nodes (6): org.springframework.stereotype.Component, CategoryMapper, CategoryRequest, CategoryService, CategoryServiceImpl, Override

### Community 61 - "CLAUDE.md — Stock Management SaaS Multi-Tenancy"
Cohesion: 0.12
Nodes (15): AbstractEntity (base for all tenant-scoped entities), Auth (public), Build & Test, CLAUDE.md — Stock Management SaaS Multi-Tenancy, Configuration (.env / env vars), Database Migrations, Docker, Entities (+7 more)

### Community 62 - "UserServiceImpl"
Cohesion: 0.32
Nodes (3): UserRepository, Override, UserServiceImpl

### Community 63 - "GlobalExceptionHandler.java"
Cohesion: 0.20
Nodes (12): Code Conventions, Format des erreurs, fielderror, org.springframework.http.HttpStatus, org.springframework.security.authentication.BadCredentialsException, org.springframework.security.core.userdetails.UsernameNotFoundException, org.springframework.web.bind.annotation.ExceptionHandler, org.springframework.web.bind.annotation.RestControllerAdvice (+4 more)

### Community 64 - "DatabaseInitializerConfig.java"
Cohesion: 0.20
Nodes (11): drivermanager, hikaridatasource, org.springframework.boot.context.properties.ConfigurationProperties, org.springframework.context.annotation.Configuration, org.springframework.context.annotation.Primary, preparedstatement, resultset, statement (+3 more)

### Community 65 - "org.springframework.context.annotation.Bean"
Cohesion: 0.22
Nodes (9): bcryptpasswordencoder, concurrentmapcachemanager, org.springframework.cache.annotation.EnableCaching, org.springframework.cache.CacheManager, org.springframework.context.annotation.Bean, org.springframework.security.authentication.AuthenticationManager, org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration, BeansConfigs (+1 more)

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

### Community 74 - "AuthenticationServiceImpl.java"
Cohesion: 0.24
Nodes (7): authentication, org.springframework.data.domain.AuditorAware, securitycontextholder, AuditorAwareImpl, Override, JpaAuditingConfig, usernamepasswordauthenticationtoken

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
Cohesion: 0.22
Nodes (8): Codes HTTP, Conventions API REST, Documentation OpenAPI, Headers, Pagination, Paramètres de chemin, Sécurité des endpoints, URL & Routing

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

### Community 112 - "app.ts"
Cohesion: 0.38
Nodes (4): @angular/platform-browser, App, appConfig, Component

### Community 113 - "Règles d'isolation multi-tenant"
Cohesion: 0.29
Nodes (6): JWT et tenant claim, Migrations tenant, Repositories — Utilisation correcte, Règles d'isolation multi-tenant, Schema naming — Convention, TenantContext — Règles d'utilisation

### Community 114 - "scripts"
Cohesion: 0.29
Nodes (7): scripts, api-gen, build, ng, start, test, watch

### Community 116 - "Périmètre de l'audit"
Cohesion: 0.33
Nodes (5): B. Authentification & JWT, C. OWASP Top 10 (contexte Spring Boot), D. Exposition des données, Format de sortie, Périmètre de l'audit

### Community 117 - "/project:deploy"
Cohesion: 0.33
Nodes (5): Arguments, Instructions, /project:deploy, Safety Rules, Usage

### Community 118 - "/project:fix-issue"
Cohesion: 0.33
Nodes (5): Arguments, Instructions, /project:fix-issue, Rules, Usage

### Community 119 - "Règles d'isolation multi-tenant"
Cohesion: 0.33
Nodes (5): JWT et tenant claim, Migrations tenant, Repositories — Utilisation correcte, Règles d'isolation multi-tenant, TenantContext — Règles d'utilisation

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

### Community 128 - "Écosystème Claude Code — `.claude/`"
Cohesion: 0.40
Nodes (5): Agents (`.claude/agents/`), Commands (`.claude/commands/`), Rules (`.claude/rules/`) — chargées automatiquement, Skills (`.claude/skills/`), Écosystème Claude Code — `.claude/`

### Community 129 - "/project:review"
Cohesion: 0.40
Nodes (4): Arguments, Instructions, /project:review, Usage

### Community 130 - "2. Le Platform Admin"
Cohesion: 0.40
Nodes (5): 2. Le Platform Admin, Caractéristiques, Ce que le Platform Admin peut faire, JWT du Platform Admin, Qui est-ce ?

### Community 131 - "6. Connexion : Platform Admin vs Tenant"
Cohesion: 0.40
Nodes (5): 6. Connexion : Platform Admin vs Tenant, Connexion d'un utilisateur Tenant, Connexion Platform Admin, Endpoint de connexion, Tableau comparatif

### Community 132 - "8. Flux complet : de l'inscription à la connexion d'un tenant"
Cohesion: 0.40
Nodes (5): 8. Flux complet : de l'inscription à la connexion d'un tenant, Étape 1 — L'entreprise s'inscrit, Étape 2 — Le Platform Admin approuve, Étape 3 — L'admin du tenant se connecte, Étape 4 — L'admin du tenant accède à ses données

### Community 133 - "Getting Started"
Cohesion: 0.40
Nodes (4): Getting Started, Guides, Maven Parent overrides, Reference Documentation

### Community 135 - "REST API — Base path `/api/v1`"
Cohesion: 0.50
Nodes (4): Auth (public), Products / Categories / StockMvts / Users, REST API — Base path `/api/v1`, Tenants (PLATFORM_ADMIN)

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
- **941 isolated node(s):** `$schema`, `version`, `packageManager`, `analytics`, `newProjectRoot` (+936 more)
  These have ≤1 connection - possible missing edges or undocumented components. (Counts symbols only; 1198 node(s) total have ≤1 connection when file, concept and rationale nodes are included.)
- **26 thin communities (<3 nodes) omitted from report** — run `graphify query` to explore isolated nodes.

## Suggested Questions
_Questions this graph is uniquely positioned to answer:_

- **Why does `TenantContext` connect `TenantContext` to `ProvisioningServiceImpl`, `2. Le Platform Admin`, `6. Connexion : Platform Admin vs Tenant`, `org.junit.jupiter.api.DisplayName`, `TenantServiceTest.java`, `Stratégie de test`, `Stratégie de test`, `TenantServiceImpl.java`, `JwtAuthenticationFilter.java`, `Règles d'isolation multi-tenant`, `Règles d'isolation multi-tenant`, `UserServiceImpl`?**
  _High betweenness centrality (0.025) - this node is a cross-community bridge._
- **Why does `PageResponse` connect `PageResponse` to `User`, `Stratégie de test`, `Stratégie de test`, `TenantServiceImpl.java`, `lombok.RequiredArgsConstructor`, `lombok.Getter`, `UserServiceImpl`?**
  _High betweenness centrality (0.014) - this node is a cross-community bridge._
- **Why does `ProvisioningServiceImpl` connect `ProvisioningServiceImpl` to `TenantServiceImpl`, `TenantServiceTest.java`, `TenantServiceImpl.java`, `TenantMigrationServiceImpl.java`, `lombok.RequiredArgsConstructor`, `.claude/agents/migration-expert.md`, `stock-mgmt-saas-multi-tenancy/.claude/agents/migration-expert.md`?**
  _High betweenness centrality (0.014) - this node is a cross-community bridge._
- **What connects `$schema`, `version`, `packageManager` to the rest of the system?**
  _941 weakly-connected nodes found - possible documentation gaps or missing edges._
- **Should `ProvisioningServiceImpl` be split into smaller, more focused modules?**
  _Cohesion score 0.13333333333333333 - nodes in this community are weakly interconnected._
- **Should `Tenant` be split into smaller, more focused modules?**
  _Cohesion score 0.10253699788583509 - nodes in this community are weakly interconnected._
- **Should `TenantServiceTest.java` be split into smaller, more focused modules?**
  _Cohesion score 0.13405797101449277 - nodes in this community are weakly interconnected._