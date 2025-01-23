CREATE TABLE supplier (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE product (
    id SMALLINT NOT NULL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL
);

CREATE TABLE coffee_beans (
    id SMALLINT NOT NULL PRIMARY KEY,
    weight SMALLINT NOT NULL,
    coffee_bean_type VARCHAR(50) NOT NULL,
    supplier_id SMALLINT NOT NULL,
    FOREIGN KEY (id) REFERENCES product (id),
    FOREIGN KEY (supplier_id) REFERENCES supplier (id)
);

CREATE TABLE coffee_drink (
    id SMALLINT NOT NULL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES product (id)
);

CREATE TABLE coffee_maker (
    id SMALLINT NOT NULL PRIMARY KEY,
    supplier_id SMALLINT NOT NULL,
    coffee_maker_type VARCHAR(255) NOT NULL,
    FOREIGN KEY (id) REFERENCES product (id),
    FOREIGN KEY (supplier_id) REFERENCES supplier (id)
);

CREATE TABLE inventory (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    product_id SMALLINT NOT NULL,
    stock_level INT NOT NULL,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE sales (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    product_id SMALLINT NOT NULL,
    quantity INT NOT NULL,
    sale_date TIMESTAMP NOT NULL,
    total_price FLOAT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (id)
);

CREATE TABLE coffee_house (
    id SMALLINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    inventory_id SMALLINT,
    sales_id SMALLINT,
    FOREIGN KEY (inventory_id) REFERENCES inventory (id),
    FOREIGN KEY (sales_id) REFERENCES sales (id)
);