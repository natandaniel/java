CREATE TABLE suppliers (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE coffee_beans (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    description VARCHAR(255),
    weight FLOAT,
    supplier_id SMALLINT,
    coffee_bean_type VARCHAR(255) NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers (id)
);

CREATE TABLE coffee_beans_inventory (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    product_id SMALLINT NOT NULL,
    stock_level INT NOT NULL,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES coffee_beans (id)
);

CREATE TABLE coffee_beans_sales (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    product_id SMALLINT NOT NULL,
    quantity INT NOT NULL,
    sale_date TIMESTAMP NOT NULL,
    total_price FLOAT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES coffee_beans (id)
);

CREATE TABLE coffee_drinks (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    description VARCHAR(255),
    weight FLOAT,
    supplier_id SMALLINT,
    FOREIGN KEY (supplier_id) REFERENCES suppliers (id)
);

CREATE TABLE coffee_drink_sales (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    product_id SMALLINT NOT NULL,
    quantity INT NOT NULL,
    sale_date TIMESTAMP NOT NULL,
    total_price FLOAT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES coffee_drinks (id)
);

CREATE TABLE coffee_makers (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    description VARCHAR(255),
    weight FLOAT,
    supplier_id SMALLINT,
    coffee_maker_type VARCHAR(255) NOT NULL,
    FOREIGN KEY (supplier_id) REFERENCES suppliers (id)
);

CREATE TABLE coffee_makers_inventory (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    product_id SMALLINT NOT NULL,
    stock_level INT NOT NULL,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES coffee_makers (id)
);

CREATE TABLE coffee_maker_sales (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    product_id SMALLINT NOT NULL,
    quantity INT NOT NULL,
    sale_date TIMESTAMP NOT NULL,
    total_price FLOAT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES coffee_makers (id)
);

CREATE TABLE coffee_houses (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);