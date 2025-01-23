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

INSERT INTO product (id, name, price) VALUES 
(1, 'Arabica Blend', 15.99),
(2, 'Robusta Premium', 12.49),
(3, 'Excelsa Light Roast', 14.99),
(4, 'Liberica Specialty', 16.89);

INSERT INTO coffee_beans (id, weight, coffee_bean_type, supplier_id) VALUES
(1, 500, 'Arabica', 1),
(2, 500, 'Robusta', 2),
(3, 250, 'Excelsa', 3),
(4, 500, 'Liberica', 4);

INSERT INTO product (id, name, price) VALUES
(5, 'Black Coffee', 2.50),
(6, 'Latte', 3.75),
(7, 'Cappuccino', 3.50),
(8, 'Americano', 2.75),
(9, 'Espresso', 2.25),
(10, 'Doppio', 3.00),
(11, 'Cortado', 3.25),
(12, 'Red Eye', 3.50),
(13, 'Galão', 3.50),
(14, 'Lungo', 3.00),
(15, 'Macchiato', 3.25),
(16, 'Mocha', 3.75),
(17, 'Ristretto', 2.50),
(18, 'Flat White', 3.50),
(19, 'Affogato', 4.00),
(20, 'Café au Lait', 3.50),
(21, 'Iced Coffee', 3.00),
(22, 'Frappuccino', 4.50);

INSERT INTO coffee_drink (id, description) VALUES
(5, 'just coffee'),
(6, 'espresso, steamed milk'),
(7, 'espresso, steamed milk, foam'),
(8, 'espresso, hot water'),
(9, '1oz espresso'),
(10, '2oz espresso'),
(11, '1oz espresso, 1oz steamed milk'),
(12, 'coffee, espresso'),
(13, 'espresso, foamed milk'),
(14, 'long pulled espresso'),
(15, 'espresso shot, foam'),
(16, 'espresso, chocolate, steamed milk'),
(17, 'short pulled espresso'),
(18, 'espresso, steamed milk'),
(19, 'espresso, ice cream'),
(20, 'coffee, steamed milk'),
(21, 'coffee, ice, cream/sugar'),
(22, 'coffee/espresso, blended ice, whip');

INSERT INTO product (id, name, price) VALUES
(23, 'BrewSmith Deluxe', 89.99),
(24, 'Morning Essence', 49.99),
(25, 'CoffeeCraft Express', 29.99),
(26, 'AeroGlow Press', 79.99),
(27, 'PureBrew Master', 59.99),
(28, 'SiphonVibe Pro', 99.99),
(29, 'ChillBrew Cooler', 69.99),
(30, 'Brewology Pro', 79.99);

INSERT INTO coffee_maker (id, supplier_id, coffee_maker_type) VALUES
(23, 5, 'French Press'),
(24, 6, 'Percolator'),
(25, 7, 'Single Serve'),
(26, 8, 'Aeropress'),
(27, 9, 'Drip'),
(28, 10, 'Pour Over'),
(29, 1, 'Cold Brew'),
(30, 2, 'Moka');

INSERT INTO inventory (product_id, stock_level) VALUES
(1, 100), -- Arabica
(2, 100), -- Robusta
(3, 50),  -- Excelsa
(4, 70),  -- Liberica
(5, 200), -- Black Coffee
(6, 200), -- Latte
(7, 200), -- Cappuccino
(8, 200), -- Americano
(9, 200), -- Espresso
(10, 200), -- Doppio
(11, 200), -- Cortado
(12, 200), -- Red Eye
(13, 200), -- Galão
(14, 200), -- Lungo
(15, 200), -- Macchiato
(16, 200), -- Mocha
(17, 200), -- Ristretto
(18, 200), -- Flat White
(19, 200), -- Affogato
(20, 200), -- Café au Lait
(21, 200), -- Iced Coffee
(22, 200), -- Frappuccino
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