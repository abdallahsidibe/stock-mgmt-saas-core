INSERT INTO users (id, created_at, created_by, deleted, enabled, username, email, password, first_name, last_name, role, tenant_id)
VALUES (
    gen_random_uuid(),
    now(),
    'system',
    false,
    true,
    'platform_admin',
    'platform@saas.internal',
    '$2a$10$v3gzQM4jXE9dD5.Iye/ZAetoq.43oDs159waZLQzbkgsPRYxZmE6C',
    'Platform',
    'Admin',
    'ROLE_PLATFORM_ADMIN',
    null
);
