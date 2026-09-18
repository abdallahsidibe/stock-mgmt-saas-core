-- =============================================================
--  DEMO SEED DATA — Tenant Schema (Schema Agnostic)
--  Executed dynamically by TenantSeedService for a target tenant schema.
-- =============================================================

-- =============================================================
--  CATEGORIES
-- =============================================================
INSERT INTO categories (id, name, description, deleted, created_at, created_by)
VALUES
    ('cat-electronics', 'Electronics',  'Electronic devices and accessories',    false, NOW() - INTERVAL '6 months', 'system'),
    ('cat-furniture',   'Furniture',    'Office and home furniture',             false, NOW() - INTERVAL '6 months', 'system'),
    ('cat-clothing',    'Clothing',     'Apparel and fashion items',             false, NOW() - INTERVAL '5 months', 'system'),
    ('cat-tools',       'Tools',        'Hand tools and power tools',            false, NOW() - INTERVAL '5 months', 'system')
ON CONFLICT (id) DO NOTHING;

-- =============================================================
--  PRODUCTS
-- =============================================================

-- Electronics
INSERT INTO products (id, name, reference, description, price, alert_threshold, category_id, deleted, created_at, created_by)
VALUES
    ('prod-laptop',    'Laptop Pro 15"',          'ELEC-LP15',  '15-inch professional laptop, 16GB RAM, 512GB SSD', 1299.99, 5,  'cat-electronics', false, NOW() - INTERVAL '6 months', 'system'),
    ('prod-monitor',   '27" 4K Monitor',          'ELEC-MON4K', 'Ultra HD 4K IPS display, 144Hz refresh rate',      599.99,  3,  'cat-electronics', false, NOW() - INTERVAL '5 months', 'system'),
    ('prod-keyboard',  'Mechanical Keyboard',     'ELEC-KBM',   'RGB mechanical keyboard, Cherry MX switches',      129.99,  10, 'cat-electronics', false, NOW() - INTERVAL '5 months', 'system'),
    ('prod-headset',   'Wireless Headset',        'ELEC-WHS',   'Noise-cancelling wireless headset, 30h battery',   249.99,  8,  'cat-electronics', false, NOW() - INTERVAL '4 months', 'system')
ON CONFLICT (id) DO NOTHING;

-- Furniture
INSERT INTO products (id, name, reference, description, price, alert_threshold, category_id, deleted, created_at, created_by)
VALUES
    ('prod-desk',      'Standing Desk',           'FURN-SDK',   'Electric height-adjustable standing desk 160x80cm', 699.99, 3, 'cat-furniture', false, NOW() - INTERVAL '6 months', 'system'),
    ('prod-chair',     'Ergonomic Chair',         'FURN-ERC',   'Lumbar support ergonomic office chair',             449.99, 4, 'cat-furniture', false, NOW() - INTERVAL '5 months', 'system'),
    ('prod-shelf',     'Bookshelf 5-Tier',        'FURN-BSH5',  'Solid wood 5-tier bookshelf',                       199.99, 5, 'cat-furniture', false, NOW() - INTERVAL '4 months', 'system')
ON CONFLICT (id) DO NOTHING;

-- Clothing
INSERT INTO products (id, name, reference, description, price, alert_threshold, category_id, deleted, created_at, created_by)
VALUES
    ('prod-tshirt',    'Classic T-Shirt',         'CLO-TSC',    'Unisex 100% cotton classic T-shirt',                19.99,  20, 'cat-clothing', false, NOW() - INTERVAL '5 months', 'system'),
    ('prod-jacket',    'Winter Jacket',           'CLO-WJK',    'Waterproof insulated winter jacket',                89.99,  10, 'cat-clothing', false, NOW() - INTERVAL '4 months', 'system'),
    ('prod-jeans',     'Slim Jeans',              'CLO-SJN',    'Slim fit denim jeans, various sizes',               49.99,  15, 'cat-clothing', false, NOW() - INTERVAL '3 months', 'system')
ON CONFLICT (id) DO NOTHING;

-- Tools
INSERT INTO products (id, name, reference, description, price, alert_threshold, category_id, deleted, created_at, created_by)
VALUES
    ('prod-drill',     'Cordless Drill',          'TOOL-DRL',   '18V cordless drill with 2 batteries',               149.99, 5, 'cat-tools', false, NOW() - INTERVAL '6 months', 'system'),
    ('prod-hammer',    'Claw Hammer',             'TOOL-HMR',   '16oz steel claw hammer, rubber grip',               24.99,  10, 'cat-tools', false, NOW() - INTERVAL '5 months', 'system'),
    ('prod-sawset',    'Circular Saw',            'TOOL-CRS',   '7-1/4" circular saw 1800W',                         199.99, 3, 'cat-tools', false, NOW() - INTERVAL '4 months', 'system'),
    ('prod-wrench',    'Adjustable Wrench Set',   'TOOL-AWS',   'Set of 3 adjustable wrenches (6", 8", 10")',         39.99,  8, 'cat-tools', false, NOW() - INTERVAL '3 months', 'system')
ON CONFLICT (id) DO NOTHING;

-- =============================================================
--  STOCK MOVEMENTS (sample IN & OUT)
-- =============================================================
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    ('mvt-1', 'IN',  30, CURRENT_DATE - INTERVAL '6 months', 'Initial stock',          'prod-laptop',   false, NOW() - INTERVAL '6 months', 'system'),
    ('mvt-2', 'OUT', 8,  CURRENT_DATE - INTERVAL '5 months', 'Customer orders',        'prod-laptop',   false, NOW() - INTERVAL '5 months', 'system'),
    ('mvt-3', 'IN',  15, CURRENT_DATE - INTERVAL '4 months', 'Restock',                'prod-laptop',   false, NOW() - INTERVAL '4 months', 'system'),
    ('mvt-4', 'OUT', 12, CURRENT_DATE - INTERVAL '3 months', 'B2B sale',               'prod-laptop',   false, NOW() - INTERVAL '3 months', 'system'),
    ('mvt-5', 'IN',  20, CURRENT_DATE - INTERVAL '6 months', 'Initial stock',          'prod-monitor',  false, NOW() - INTERVAL '6 months', 'system'),
    ('mvt-6', 'OUT', 5,  CURRENT_DATE - INTERVAL '5 months', 'Customer orders',        'prod-monitor',  false, NOW() - INTERVAL '5 months', 'system')
ON CONFLICT (id) DO NOTHING;
