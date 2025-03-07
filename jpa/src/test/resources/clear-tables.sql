DELETE FROM  sale;
DELETE FROM  coffee_beans;
DELETE FROM  coffee_drink;
DELETE FROM  coffee_maker;
DELETE FROM  coffee_house;
DELETE FROM  supplier;

ALTER TABLE coffee_beans ALTER COLUMN id RESTART WITH 1;
ALTER TABLE coffee_house ALTER COLUMN id RESTART WITH 1;
