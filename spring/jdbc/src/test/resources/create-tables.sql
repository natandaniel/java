create database if not exists mysql_test_db;

create table if not exists SUPPLIERS
  (SUP_ID integer NOT NULL,
  SUP_NAME varchar(40) NOT NULL,
  STREET varchar(40) NOT NULL,
  CITY varchar(20) NOT NULL,
  STATE char(2) NOT NULL,
  ZIP char(5),
  PRIMARY KEY (SUP_ID));

create table if not exists COFFEES
  (COF_NAME varchar(32) NOT NULL,
  SUP_ID int NOT NULL,
  PRICE numeric(10,2) NOT NULL,
  SALES integer NOT NULL,
  TOTAL integer NOT NULL,
  PRIMARY KEY (COF_NAME),
  FOREIGN KEY (SUP_ID) REFERENCES SUPPLIERS (SUP_ID));

create table if not exists COFFEE_DESCRIPTIONS
  (COF_NAME varchar(32) NOT NULL,
  COF_DESC blob NOT NULL,
  PRIMARY KEY (COF_NAME),
  FOREIGN KEY (COF_NAME) REFERENCES COFFEES (COF_NAME));

create table if not exists RSS_FEEDS
  (RSS_NAME varchar(32) NOT NULL,
  RSS_FEED_XML longtext NOT NULL,
  PRIMARY KEY (RSS_NAME));

create table if not exists COF_INVENTORY
  (WAREHOUSE_ID integer NOT NULL,
  COF_NAME varchar(32) NOT NULL,
  SUP_ID int NOT NULL,
  QUAN int NOT NULL,
  DATE_VAL timestamp,
  FOREIGN KEY (COF_NAME) REFERENCES COFFEES (COF_NAME),
  FOREIGN KEY (SUP_ID) REFERENCES SUPPLIERS (SUP_ID));

create table if not exists MERCH_INVENTORY
  (ITEM_ID integer NOT NULL,
  ITEM_NAME varchar(20),
  SUP_ID int,
  QUAN int,
  DATE_VAL timestamp,
  PRIMARY KEY (ITEM_ID),
  FOREIGN KEY (SUP_ID) REFERENCES SUPPLIERS (SUP_ID));

create table if not exists COFFEE_HOUSES
  (STORE_ID integer NOT NULL,
  CITY varchar(32),
  COFFEE int NOT NULL,
  MERCH int NOT NULL,
  TOTAL int NOT NULL,
  PRIMARY KEY (STORE_ID));

create table if not exists DATA_REPOSITORY
  (DOCUMENT_NAME varchar(50),
  URL varchar(200));