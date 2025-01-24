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

INSERT INTO coffee_house (name, street, state, city, zip, supplier) VALUES
('Relaxing Coffee', '23 Sunrise Lane', 'Brewsville', 'CA', '90210', 3);

INSERT INTO coffee_beans (name, price, description, weight, coffee_bean_type, stock_level, coffee_house) VALUES
('Mountain Roast', 10.99, 'Rich and smooth blend from the highlands', 500.0, 'ARABICA', 50, 1),
('Dark Forest', 12.49, 'Bold and intense flavor with a hint of chocolate', 500.0, 'ROBUSTA', 30, 1),
('Golden Reserve', 14.99, 'Premium single-origin beans with a nutty finish', 500.0, 'LIBERICA', 20, 1),
('Morning Bliss', 8.99, 'Bright and fruity for a refreshing start', 500.0, 'EXCELSA', 40, 1),
('Nightfall Espresso', 11.49, 'Perfectly roasted for a robust espresso shot', 500.0, 'ARABICA', 25, 1);

INSERT INTO coffee_maker (name, price, description, weight, coffee_maker_type, stock_level, coffee_house) VALUES
('Barista Pro', 199.99, 'Compact and fast single-serve coffee maker.', NULL, 'Single Serve', 25, 1),
('BrewMaster 3000', 299.99, 'Versatile drip coffee machine with programmable settings.', NULL, 'Drip', 15, 1),
('AeroPerfect', 129.99, 'Lightweight and portable Aeropress coffee maker.', NULL, 'Aeropress', 50, 1),
('Percolate Deluxe', 89.99, 'Classic percolator with a sleek stainless steel design.', NULL, 'Percolator', 20, 1),
('ColdBrew Wizard', 149.99, 'Cold brew coffee maker with a built-in filter.', NULL, 'Cold Brew', 10, 1),
('FrenchMaster', 59.99, 'Elegant French press with a durable glass carafe.', NULL, 'French Press', 40, 1),
('Moka Prestige', 39.99, 'Classic Italian-style moka pot for stovetop brewing.', NULL, 'Moka', 30, 1),
('PourOver Pro', 74.99, 'Advanced pour-over coffee dripper with a stand.', NULL, 'Pour Over', 25, 1);

INSERT INTO coffee_drink (name, price, description, weight, coffee_house) VALUES
('Black Coffee', 2.50, 'Classic hot brewed coffee with no additives.', NULL, 1),
('Latte', 4.50, 'Espresso mixed with steamed milk and a thin layer of foam.', NULL, 1),
('Cappuccino', 4.00, 'Espresso with steamed milk and a thick layer of foam.', NULL, 1),
('Americano', 3.00, 'Espresso diluted with hot water.', NULL, 1),
('Espresso', 2.75, 'Rich and bold concentrated coffee shot.', NULL, 1),
('Doppio', 3.50, 'Double shot of espresso for an extra kick.', NULL, 1),
('Cortado', 3.25, 'Espresso cut with a small amount of warm milk.', NULL, 1),
('Red Eye', 4.75, 'Drip coffee with a shot of espresso.', NULL, 1),
('Galao', 4.00, 'Espresso with foamed milk, similar to a latte.', NULL, 1),
('Lungo', 3.25, 'A longer extraction of espresso, milder in taste.',NULL,  1),
('Macchiato', 3.75, 'Espresso topped with a dollop of foam.', NULL, 1),
('Mocha', 4.75, 'Espresso with chocolate syrup and steamed milk.', NULL, 1),
('Ristretto', 3.00, 'A short, highly concentrated espresso shot.', NULL, 1),
('Flat White', 4.25, 'Espresso with micro-foamed milk, velvety texture.', NULL, 1),
('Affogato', 5.00, 'Espresso poured over a scoop of vanilla ice cream.', NULL, 1),
('Café au Lait', 3.50, 'Equal parts drip coffee and steamed milk.', NULL, 1),
('Iced Coffee', 3.00, 'Chilled brewed coffee served over ice.', NULL, 1),
('Iced Espresso', 3.50, 'Espresso served over ice.', NULL, 1),
('Cold Brew', 4.50, 'Coffee steeped in cold water for 12+ hours.', NULL, 1),
('Frappuccino', 5.50, 'Blended iced coffee with milk, sugar, and flavors.', NULL, 1),
('Nitro', 5.00, 'Cold brew infused with nitrogen for a creamy texture.', NULL, 1),
('Mazagran', 4.25, 'Cold coffee with a splash of lemon.', NULL, 1);

INSERT INTO sale (product_name, quantity, sale_date, total_price, coffee_house) VALUES
('Black Coffee', 10, '2025-01-23 08:00:00', 25.00, 1),
('Latte', 5, '2025-01-23 08:30:00', 22.50, 1),
('Cappuccino', 7, '2025-01-23 09:00:00', 28.00, 1),
('Americano', 3, '2025-01-23 09:15:00', 9.00, 1),
('Espresso', 6, '2025-01-23 10:00:00', 16.50, 1),
('Doppio', 4, '2025-01-23 10:30:00', 14.00, 1),
('Cortado', 2, '2025-01-23 11:00:00', 6.50, 1),
('Red Eye', 8, '2025-01-23 11:30:00', 24.00, 1),
('Galao', 5, '2025-01-23 12:00:00', 20.00, 1),
('Lungo', 3, '2025-01-23 12:30:00', 9.75, 1),
('Macchiato', 4, '2025-01-23 13:00:00', 15.00, 1),
('Mocha', 6, '2025-01-23 13:30:00', 28.50, 1),
('Ristretto', 3, '2025-01-23 14:00:00', 9.00, 1),
('Flat White', 2, '2025-01-23 14:30:00', 8.50, 1),
('Affogato', 1, '2025-01-23 15:00:00', 5.00, 1),
('Café au Lait', 7, '2025-01-23 15:30:00', 24.50, 1),
('Iced Coffee', 10, '2025-01-23 16:00:00', 30.00, 1),
('Iced Espresso', 6, '2025-01-23 16:30:00', 21.00, 1),
('Cold Brew', 4, '2025-01-23 17:00:00', 18.00, 1),
('Frappuccino', 8, '2025-01-23 17:30:00', 44.00, 1),
('Nitro', 5, '2025-01-23 18:00:00', 25.00, 1),
('Mazagran', 3, '2025-01-23 18:30:00', 12.75, 1);