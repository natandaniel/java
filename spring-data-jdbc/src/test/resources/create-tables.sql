create database if not exists mysql_test_db;

create table if not exists supplier
  (id integer NOT NULL,
  name varchar(40) NOT NULL,
  street varchar(40) NOT NULL,
  city varchar(20) NOT NULL,
  state char(2) NOT NULL,
  zip char(5),
  PRIMARY KEY (id));

create table if not exists coffee
  (name varchar(32) NOT NULL,
  sup_id int NOT NULL,
  price numeric(10,2) NOT NULL,
  sales integer NOT NULL,
  total integer NOT NULL,
  PRIMARY KEY (name),
  FOREIGN KEY (sup_id) REFERENCES supplier (id));

create table if not exists coffee_description
  (cof_name varchar(32) NOT NULL,
  cof_desc blob NOT NULL,
  PRIMARY KEY (cof_name),
  FOREIGN KEY (cof_name) REFERENCES coffee (name));

create table if not exists rss_feed
  (rss_name varchar(32) NOT NULL,
  rss_feed_xml longtext NOT NULL,
  PRIMARY KEY (rss_name));

create table if not exists cof_inventory
  (warehouse_id integer NOT NULL,
  cof_name varchar(32) NOT NULL,
  sup_id int NOT NULL,
  quan int NOT NULL,
  date_val timestamp,
  FOREIGN KEY (cof_name) REFERENCES coffee (name),
  FOREIGN KEY (sup_id) REFERENCES supplier (id));

create table if not exists merch_inventory
  (item_id integer NOT NULL,
  item_name varchar(20),
  sup_id int,
  quan int,
  date_val timestamp,
  PRIMARY KEY (item_id),
  FOREIGN KEY (sup_id) REFERENCES supplier (id));

create table if not exists coffee_house
  (store_id integer NOT NULL,
  city varchar(32),
  coffee int NOT NULL,
  merch int NOT NULL,
  total int NOT NULL,
  PRIMARY KEY (store_id));

create table if not exists data_repository
  (document_name varchar(50),
  url varchar(200));