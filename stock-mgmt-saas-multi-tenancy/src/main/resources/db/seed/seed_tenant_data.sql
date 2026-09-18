-- =============================================================
--  SEED DATA — Tenant Schema
--  Usage : remplacer 'schema_<companyCode>' par le vrai schema
--           ex : SET search_path TO schema_techcorp;
-- =============================================================

-- Adapter le schema ici
SET search_path TO schema_novatech;

-- =============================================================
--  CATEGORIES
-- =============================================================
INSERT INTO categories (id, name, description, deleted, created_at, created_by)
VALUES
    ('cat-electronics', 'Electronics',  'Electronic devices and accessories',    false, NOW() - INTERVAL '6 months', 'system'),
    ('cat-furniture',   'Furniture',    'Office and home furniture',             false, NOW() - INTERVAL '6 months', 'system'),
    ('cat-clothing',    'Clothing',     'Apparel and fashion items',             false, NOW() - INTERVAL '5 months', 'system'),
    ('cat-tools',       'Tools',        'Hand tools and power tools',            false, NOW() - INTERVAL '5 months', 'system');

-- =============================================================
--  PRODUCTS
-- =============================================================

-- Electronics
INSERT INTO products (id, name, reference, description, price, alert_threshold, category_id, deleted, created_at, created_by)
VALUES
    ('prod-laptop',    'Laptop Pro 15"',          'ELEC-LP15',  '15-inch professional laptop, 16GB RAM, 512GB SSD', 1299.99, 5,  'cat-electronics', false, NOW() - INTERVAL '6 months', 'system'),
    ('prod-monitor',   '27" 4K Monitor',          'ELEC-MON4K', 'Ultra HD 4K IPS display, 144Hz refresh rate',      599.99,  3,  'cat-electronics', false, NOW() - INTERVAL '5 months', 'system'),
    ('prod-keyboard',  'Mechanical Keyboard',     'ELEC-KBM',   'RGB mechanical keyboard, Cherry MX switches',      129.99,  10, 'cat-electronics', false, NOW() - INTERVAL '5 months', 'system'),
    ('prod-headset',   'Wireless Headset',        'ELEC-WHS',   'Noise-cancelling wireless headset, 30h battery',   249.99,  8,  'cat-electronics', false, NOW() - INTERVAL '4 months', 'system');

-- Furniture
INSERT INTO products (id, name, reference, description, price, alert_threshold, category_id, deleted, created_at, created_by)
VALUES
    ('prod-desk',      'Standing Desk',           'FURN-SDK',   'Electric height-adjustable standing desk 160x80cm', 699.99, 3, 'cat-furniture', false, NOW() - INTERVAL '6 months', 'system'),
    ('prod-chair',     'Ergonomic Chair',         'FURN-ERC',   'Lumbar support ergonomic office chair',             449.99, 4, 'cat-furniture', false, NOW() - INTERVAL '5 months', 'system'),
    ('prod-shelf',     'Bookshelf 5-Tier',        'FURN-BSH5',  'Solid wood 5-tier bookshelf',                       199.99, 5, 'cat-furniture', false, NOW() - INTERVAL '4 months', 'system');

-- Clothing
INSERT INTO products (id, name, reference, description, price, alert_threshold, category_id, deleted, created_at, created_by)
VALUES
    ('prod-tshirt',    'Classic T-Shirt',         'CLO-TSC',    'Unisex 100% cotton classic T-shirt',                19.99,  20, 'cat-clothing', false, NOW() - INTERVAL '5 months', 'system'),
    ('prod-jacket',    'Winter Jacket',           'CLO-WJK',    'Waterproof insulated winter jacket',                89.99,  10, 'cat-clothing', false, NOW() - INTERVAL '4 months', 'system'),
    ('prod-jeans',     'Slim Jeans',              'CLO-SJN',    'Slim fit denim jeans, various sizes',               49.99,  15, 'cat-clothing', false, NOW() - INTERVAL '3 months', 'system');

-- Tools
INSERT INTO products (id, name, reference, description, price, alert_threshold, category_id, deleted, created_at, created_by)
VALUES
    ('prod-drill',     'Cordless Drill',          'TOOL-DRL',   '18V cordless drill with 2 batteries',               149.99, 5, 'cat-tools', false, NOW() - INTERVAL '6 months', 'system'),
    ('prod-hammer',    'Claw Hammer',             'TOOL-HMR',   '16oz steel claw hammer, rubber grip',               24.99,  10, 'cat-tools', false, NOW() - INTERVAL '5 months', 'system'),
    ('prod-sawset',    'Circular Saw',            'TOOL-CRS',   '7-1/4" circular saw 1800W',                         199.99, 3, 'cat-tools', false, NOW() - INTERVAL '4 months', 'system'),
    ('prod-wrench',    'Adjustable Wrench Set',   'TOOL-AWS',   'Set of 3 adjustable wrenches (6", 8", 10")',         39.99,  8, 'cat-tools', false, NOW() - INTERVAL '3 months', 'system');

-- =============================================================
--  STOCK MOVEMENTS (last 6 months — IN & OUT)
-- =============================================================

-- Laptop
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  30, NOW() - INTERVAL '6 months', 'Initial stock',          'prod-laptop',   false, NOW() - INTERVAL '6 months', 'system'),
    (gen_random_uuid(), 'OUT', 8,  NOW() - INTERVAL '5 months', 'Customer orders',        'prod-laptop',   false, NOW() - INTERVAL '5 months', 'system'),
    (gen_random_uuid(), 'IN',  15, NOW() - INTERVAL '4 months', 'Restock',                'prod-laptop',   false, NOW() - INTERVAL '4 months', 'system'),
    (gen_random_uuid(), 'OUT', 12, NOW() - INTERVAL '3 months', 'B2B sale',               'prod-laptop',   false, NOW() - INTERVAL '3 months', 'system'),
    (gen_random_uuid(), 'OUT', 10, NOW() - INTERVAL '2 months', 'Retail orders',          'prod-laptop',   false, NOW() - INTERVAL '2 months', 'system'),
    (gen_random_uuid(), 'IN',  20, NOW() - INTERVAL '1 month',  'Monthly restock',        'prod-laptop',   false, NOW() - INTERVAL '1 month',  'system');

-- Monitor
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  20, NOW() - INTERVAL '6 months', 'Initial stock',          'prod-monitor',  false, NOW() - INTERVAL '6 months', 'system'),
    (gen_random_uuid(), 'OUT', 5,  NOW() - INTERVAL '5 months', 'Customer orders',        'prod-monitor',  false, NOW() - INTERVAL '5 months', 'system'),
    (gen_random_uuid(), 'IN',  10, NOW() - INTERVAL '3 months', 'Restock',                'prod-monitor',  false, NOW() - INTERVAL '3 months', 'system'),
    (gen_random_uuid(), 'OUT', 8,  NOW() - INTERVAL '1 month',  'B2B sale',               'prod-monitor',  false, NOW() - INTERVAL '1 month',  'system');

-- Keyboard
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  50, NOW() - INTERVAL '5 months', 'Initial stock',          'prod-keyboard', false, NOW() - INTERVAL '5 months', 'system'),
    (gen_random_uuid(), 'OUT', 15, NOW() - INTERVAL '4 months', 'Online orders',          'prod-keyboard', false, NOW() - INTERVAL '4 months', 'system'),
    (gen_random_uuid(), 'OUT', 12, NOW() - INTERVAL '2 months', 'Retail',                 'prod-keyboard', false, NOW() - INTERVAL '2 months', 'system'),
    (gen_random_uuid(), 'IN',  20, NOW() - INTERVAL '1 month',  'Restock',                'prod-keyboard', false, NOW() - INTERVAL '1 month',  'system');

-- Headset (low stock scenario)
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  15, NOW() - INTERVAL '5 months', 'Initial stock',          'prod-headset',  false, NOW() - INTERVAL '5 months', 'system'),
    (gen_random_uuid(), 'OUT', 6,  NOW() - INTERVAL '4 months', 'Customer orders',        'prod-headset',  false, NOW() - INTERVAL '4 months', 'system'),
    (gen_random_uuid(), 'OUT', 5,  NOW() - INTERVAL '2 months', 'B2B sale',               'prod-headset',  false, NOW() - INTERVAL '2 months', 'system');
-- Stock restant : 4 → en alerte (threshold = 8)

-- Desk
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  15, NOW() - INTERVAL '6 months', 'Initial stock',          'prod-desk',     false, NOW() - INTERVAL '6 months', 'system'),
    (gen_random_uuid(), 'OUT', 4,  NOW() - INTERVAL '4 months', 'Customer orders',        'prod-desk',     false, NOW() - INTERVAL '4 months', 'system'),
    (gen_random_uuid(), 'OUT', 3,  NOW() - INTERVAL '2 months', 'Showroom sale',          'prod-desk',     false, NOW() - INTERVAL '2 months', 'system'),
    (gen_random_uuid(), 'IN',  5,  NOW() - INTERVAL '1 month',  'Restock',                'prod-desk',     false, NOW() - INTERVAL '1 month',  'system');

-- Chair (low stock scenario)
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  10, NOW() - INTERVAL '6 months', 'Initial stock',          'prod-chair',    false, NOW() - INTERVAL '6 months', 'system'),
    (gen_random_uuid(), 'OUT', 4,  NOW() - INTERVAL '4 months', 'Customer orders',        'prod-chair',    false, NOW() - INTERVAL '4 months', 'system'),
    (gen_random_uuid(), 'OUT', 4,  NOW() - INTERVAL '2 months', 'Corporate order',        'prod-chair',    false, NOW() - INTERVAL '2 months', 'system');
-- Stock restant : 2 → en alerte (threshold = 4)

-- Shelf
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  20, NOW() - INTERVAL '5 months', 'Initial stock',          'prod-shelf',    false, NOW() - INTERVAL '5 months', 'system'),
    (gen_random_uuid(), 'OUT', 7,  NOW() - INTERVAL '3 months', 'Customer orders',        'prod-shelf',    false, NOW() - INTERVAL '3 months', 'system'),
    (gen_random_uuid(), 'OUT', 4,  NOW() - INTERVAL '1 month',  'Retail',                 'prod-shelf',    false, NOW() - INTERVAL '1 month',  'system');

-- T-Shirt
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  100, NOW() - INTERVAL '5 months', 'Initial stock',         'prod-tshirt',   false, NOW() - INTERVAL '5 months', 'system'),
    (gen_random_uuid(), 'OUT', 30,  NOW() - INTERVAL '4 months', 'Online orders',         'prod-tshirt',   false, NOW() - INTERVAL '4 months', 'system'),
    (gen_random_uuid(), 'IN',  50,  NOW() - INTERVAL '3 months', 'Restock',               'prod-tshirt',   false, NOW() - INTERVAL '3 months', 'system'),
    (gen_random_uuid(), 'OUT', 25,  NOW() - INTERVAL '2 months', 'Retail',                'prod-tshirt',   false, NOW() - INTERVAL '2 months', 'system'),
    (gen_random_uuid(), 'OUT', 20,  NOW() - INTERVAL '1 month',  'Seasonal sale',         'prod-tshirt',   false, NOW() - INTERVAL '1 month',  'system');

-- Jacket (low stock scenario)
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  25, NOW() - INTERVAL '4 months', 'Winter stock',           'prod-jacket',   false, NOW() - INTERVAL '4 months', 'system'),
    (gen_random_uuid(), 'OUT', 10, NOW() - INTERVAL '3 months', 'Customer orders',        'prod-jacket',   false, NOW() - INTERVAL '3 months', 'system'),
    (gen_random_uuid(), 'OUT', 8,  NOW() - INTERVAL '2 months', 'Seasonal orders',        'prod-jacket',   false, NOW() - INTERVAL '2 months', 'system'),
    (gen_random_uuid(), 'OUT', 5,  NOW() - INTERVAL '1 month',  'End of season',          'prod-jacket',   false, NOW() - INTERVAL '1 month',  'system');
-- Stock restant : 2 → en alerte (threshold = 10)

-- Jeans
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  60, NOW() - INTERVAL '3 months', 'Initial stock',          'prod-jeans',    false, NOW() - INTERVAL '3 months', 'system'),
    (gen_random_uuid(), 'OUT', 18, NOW() - INTERVAL '2 months', 'Online orders',          'prod-jeans',    false, NOW() - INTERVAL '2 months', 'system'),
    (gen_random_uuid(), 'OUT', 12, NOW() - INTERVAL '1 month',  'Retail',                 'prod-jeans',    false, NOW() - INTERVAL '1 month',  'system');

-- Drill
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  20, NOW() - INTERVAL '6 months', 'Initial stock',          'prod-drill',    false, NOW() - INTERVAL '6 months', 'system'),
    (gen_random_uuid(), 'OUT', 6,  NOW() - INTERVAL '4 months', 'Customer orders',        'prod-drill',    false, NOW() - INTERVAL '4 months', 'system'),
    (gen_random_uuid(), 'IN',  10, NOW() - INTERVAL '3 months', 'Restock',                'prod-drill',    false, NOW() - INTERVAL '3 months', 'system'),
    (gen_random_uuid(), 'OUT', 5,  NOW() - INTERVAL '1 month',  'Retail',                 'prod-drill',    false, NOW() - INTERVAL '1 month',  'system');

-- Hammer
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  40, NOW() - INTERVAL '6 months', 'Initial stock',          'prod-hammer',   false, NOW() - INTERVAL '6 months', 'system'),
    (gen_random_uuid(), 'OUT', 12, NOW() - INTERVAL '4 months', 'Customer orders',        'prod-hammer',   false, NOW() - INTERVAL '4 months', 'system'),
    (gen_random_uuid(), 'OUT', 10, NOW() - INTERVAL '2 months', 'Retail',                 'prod-hammer',   false, NOW() - INTERVAL '2 months', 'system'),
    (gen_random_uuid(), 'IN',  15, NOW() - INTERVAL '1 month',  'Restock',                'prod-hammer',   false, NOW() - INTERVAL '1 month',  'system');

-- Circular Saw (low stock)
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  8,  NOW() - INTERVAL '5 months', 'Initial stock',          'prod-sawset',   false, NOW() - INTERVAL '5 months', 'system'),
    (gen_random_uuid(), 'OUT', 3,  NOW() - INTERVAL '3 months', 'Customer orders',        'prod-sawset',   false, NOW() - INTERVAL '3 months', 'system'),
    (gen_random_uuid(), 'OUT', 3,  NOW() - INTERVAL '1 month',  'B2B sale',               'prod-sawset',   false, NOW() - INTERVAL '1 month',  'system');
-- Stock restant : 2 → en alerte (threshold = 3)

-- Wrench Set
INSERT INTO stock_mvts (id, type_mvt, quantity, date_mvt, comment, product_id, deleted, created_at, created_by) VALUES
    (gen_random_uuid(), 'IN',  30, NOW() - INTERVAL '4 months', 'Initial stock',          'prod-wrench',   false, NOW() - INTERVAL '4 months', 'system'),
    (gen_random_uuid(), 'OUT', 8,  NOW() - INTERVAL '3 months', 'Customer orders',        'prod-wrench',   false, NOW() - INTERVAL '3 months', 'system'),
    (gen_random_uuid(), 'OUT', 6,  NOW() - INTERVAL '1 month',  'Retail',                 'prod-wrench',   false, NOW() - INTERVAL '1 month',  'system');
