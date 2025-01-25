CREATE TABLE IF NOT EXISTS supplier (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS coffee_house (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    street  VARCHAR(255),
    city  VARCHAR(50),
    state  VARCHAR(50),
    zip  VARCHAR(50),
    supplier INTEGER,
    FOREIGN KEY (supplier) REFERENCES supplier (id)
);

CREATE TABLE IF NOT EXISTS coffee_beans (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    description VARCHAR(255),
    weight FLOAT,
    coffee_bean_type VARCHAR(255) NOT NULL,
    stock_level INTEGER,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    coffee_house INTEGER,
    FOREIGN KEY (coffee_house) REFERENCES coffee_house (id) -- by default, the name of the foreign key column is the table name of the referencing entity; configure a custom name with @MappedCollection(idColumn = "...")
);

CREATE TABLE IF NOT EXISTS coffee_maker (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    description VARCHAR(255),
    weight FLOAT,
    coffee_maker_type VARCHAR(255) NOT NULL,
    stock_level INTEGER,
    last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    coffee_house INTEGER,
    FOREIGN KEY (coffee_house) REFERENCES coffee_house (id)
);

CREATE TABLE IF NOT EXISTS coffee_drink (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price FLOAT NOT NULL,
    description VARCHAR(255),
    weight FLOAT,
    coffee_house INTEGER,
    FOREIGN KEY (coffee_house) REFERENCES coffee_house (id)
);

CREATE TABLE IF NOT EXISTS sale (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    sale_date TIMESTAMP NOT NULL,
    total_price FLOAT NOT NULL,
    coffee_house INTEGER,
    FOREIGN KEY (coffee_house) REFERENCES coffee_house (id)
);
