ALTER TABLE tenants DROP CONSTRAINT IF EXISTS tenants_status_check;

ALTER TABLE tenants ADD CONSTRAINT tenants_status_check
    CHECK ((status)::text = ANY
           ((ARRAY ['PENDING'::character varying, 'PROVISIONING'::character varying, 'ACTIVE'::character varying, 'FAILED'::character varying, 'SUSPENDED'::character varying, 'INACTIVE'::character varying])::text[]));

ALTER TABLE tenants ADD COLUMN IF NOT EXISTS failure_reason text;
