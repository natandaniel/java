INSERT INTO supplier (name) VALUES
('GreenLeaf Supplies'),
('Global Beans Inc.'),
('BrewMasters Ltd.'),
('Café Essentials Co.'),
('BeanWorld Distributors'),
('Golden Brew Supplies'),
('Premium Roasters Inc.'),
('Harvest Beans Suppliers'),
('World of Coffee Traders'),
('FreshBrew Co.');

INSERT INTO coffee_beans (name, price, description, weight, supplier_id, coffee_bean_type) VALUES
('Arabica Blend', 15.99, '', 500.0, 1, 'Arabica'),
('Robusta Premium', 12.49, '', 500.0, 2, 'Robusta'),
('Excelsa Light Roast', 14.99, '', 250.0, 3, 'Excelsa'),
('Liberica Specialty', 16.89, '', 500.0, 4, 'Liberica');

INSERT INTO coffee_beans_inventory (product_id, stock_level) VALUES
(1, 100),
(2, 100),
(3, 50),
(4, 70);

INSERT INTO coffee_drink (name, price, description, weight, supplier_id) VALUES
('Black Coffee', 2.50, 'just coffee', NULL, NULL),
('Latte', 3.75, 'espresso, steamed milk', NULL, NULL),
('Cappuccino', 3.50, 'espresso, steamed milk, foam', NULL, NULL),
('Americano', 2.75, 'espresso, hot water', NULL, NULL),
('Espresso', 2.25, '1oz espresso', NULL, NULL),
('Doppio', 3.00, '2oz espresso', NULL, NULL),
('Cortado', 3.25, '1oz espresso, 1oz steamed milk', NULL, NULL),
('Red Eye', 3.50, 'coffee, espresso', NULL, NULL),
('Galão', 3.50, 'espresso, foamed milk', NULL, NULL),
('Lungo', 3.00, 'long pulled espresso', NULL, NULL),
('Macchiato', 3.25, 'espresso shot, foam', NULL, NULL),
('Mocha', 3.75, 'espresso, chocolate, steamed milk', NULL, NULL),
('Ristretto', 2.50, 'short pulled espresso', NULL, NULL),
('Flat White', 3.50, 'espresso, steamed milk', NULL, NULL),
('Affogato', 4.00, 'espresso, ice cream', NULL, NULL),
('Café au Lait', 3.50, 'coffee, steamed milk', NULL, NULL),
('Iced Coffee', 3.00, 'coffee, ice, cream/sugar', NULL, NULL),
('Frappuccino', 4.50, 'coffee/espresso, blended ice, whip', NULL, NULL);

INSERT INTO coffee_maker (id, name, price, description, weight, supplier_id, coffee_maker_type) VALUES
('BrewSmith Deluxe', 89.99, NULL, NULL, 5, 'French Press'),
('Morning Essence', 49.99, NULL, NULL, 6, 'Percolator'),
('CoffeeCraft Express', 29.99, NULL, NULL, 7, 'Single Serve'),
('AeroGlow Press', 79.99, NULL, NULL, 8, 'Aeropress'),
('PureBrew Master', 59.99, NULL, NULL, 9, 'Drip'),
('SiphonVibe Pro', 99.99, NULL, NULL, 10, 'Pour Over'),
('ChillBrew Cooler', 69.99), NULL, NULL, 1, 'Cold Brew',
('Brewology Pro', 79.99, NULL, NULL, 2, 'Moka');

INSERT INTO coffee_maker_inventory (coffee_maker_id, stock_level) VALUES
(1, 100),
(2, 100),
(3, 50),
(4, 70),
(23, 50),  -- French Press
(24, 50),  -- Percolator
(25, 30),  -- Single Serve Brewer
(26, 40),  -- AeroPress
(27, 40),  -- Drip Coffee Maker
(28, 40),  -- Pour Over Set
(29, 30),  -- Cold Brew Kit
(30, 50);  -- Moka Pot




INSERT INTO sales (product_id, quantity, sale_date, total_price) VALUES
(1, 10, '2025-01-10 10:30:00', 159.90), -- Arabica
(2, 5, '2025-01-12 14:00:00', 62.45), -- Robusta
(5, 20, '2025-01-15 08:45:00', 50.00), -- Black Coffee
(6, 15, '2025-01-15 09:00:00', 56.25), -- Latte
(7, 10, '2025-01-15 09:15:00', 35.00), -- Cappuccino
(25, 2, '2025-01-20 11:30:00', 151.98); -- Single Serve Brewer

INSERT INTO coffee_house (name, inventory_id, sales_id) VALUES
('CupOfCoffee', 1, 1);