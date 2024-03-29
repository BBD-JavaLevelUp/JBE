DROP TABLE IF EXISTS inventory;
DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS sell_order;
DROP TABLE IF EXISTS buy_order;
DROP TABLE IF EXISTS investor;
DROP TABLE IF EXISTS bean;

/*Create tables*/
CREATE TABLE bean (
	bean_id serial PRIMARY KEY,
	name varchar(100) NOT NULL,
	default_price numeric NOT NULL
);

CREATE TABLE investor (
	investor_id serial PRIMARY KEY,
	name varchar(50) NOT NULL,
	sa_id varchar(13) NOT NULL,
	email varchar(100)
);

CREATE TABLE buy_order (
	buy_order_id serial PRIMARY KEY,
	investor_id int NOT NULL,
	bean_id int NOT NULL,
	price numeric NOT NULL,
	available_amount bigint NOT NULL,
	total_amount bigint NOT NULL,
	order_date timestamp NOT NULL,
	is_active boolean NOT NULL,
	
	CONSTRAINT fk_BuyOrders_investorId FOREIGN KEY(investor_id) REFERENCES investor(investor_id),
	CONSTRAINT fk_BuyOrders_beanId FOREIGN KEY(bean_id) REFERENCES bean(bean_id)
);

CREATE TABLE sell_order (
	sell_order_id serial PRIMARY KEY,
	investor_id int NOT NULL,
	bean_id int NOT NULL,
	price numeric NOT NULL,
	available_amount bigint NOT NULL,
	total_amount bigint NOT NULL,
	order_date timestamp NOT NULL,
	is_active boolean NOT NULL,
	
	CONSTRAINT fk_SellOrders_investorId FOREIGN KEY(investor_id) REFERENCES investor(investor_id),
	CONSTRAINT fk_SellOrders_beanId FOREIGN KEY(bean_id) REFERENCES bean(bean_id)
);

CREATE TABLE transaction (
	transaction_id serial PRIMARY KEY,
	buy_order_id int NOT NULL,
	sell_order_id int NOT NULL,
	transaction_date timestamp NOT NULL,
	amount bigint NOT NULL,
	
	CONSTRAINT fk_Transactions_buyOrderId FOREIGN KEY(buy_order_id) REFERENCES buy_order(buy_order_id),
	CONSTRAINT fk_Transactions_sellOrderId FOREIGN KEY(sell_order_id) REFERENCES sell_order(sell_order_id)
);

CREATE TABLE inventory (
	inventory_id serial PRIMARY KEY,
	investor_id int NOT NULL,
	bean_id int NOT NULL,
	amount bigint NOT NULL,
	
	CONSTRAINT fk_Inventory_investorId FOREIGN KEY(investor_id) REFERENCES investor(investor_id),
	CONSTRAINT fk_Inventory_beanId FOREIGN KEY(bean_id) REFERENCES bean(bean_id)
);


INSERT INTO bean (name, default_price)
VALUES
	('Red', 10.50),
	('Kidney', 8.75),
	('Sugar', 12.00),
	('Baked', 4.50),
	('Coffee', 100.00),
	('Magic', 4000.00);

INSERT INTO investor (name, sa_id, email)
VALUES
  ('JBE', '0000000000000', 'services@jbe.com');

INSERT INTO sell_order (investor_id, bean_id, price, available_amount, total_amount, order_date, is_active)
VALUES
  (1, 1, 10.50, 750, 750, '2024-03-08 10:30:00', true),
  (1, 2, 8.75, 1000, 1000, '2024-03-08 11:30:00', true),
  (1, 3, 12.00, 1250, 1250, '2024-03-08 12:30:00', true),
  (1, 4, 4.50, 500, 500, '2024-03-08 12:30:00', true),
  (1, 5, 100.00, 3000, 3000, '2024-03-08 12:30:00', true),
  (1, 6, 4000.00, 80, 80, '2024-03-08 12:30:00', true);

INSERT INTO inventory (investor_id, bean_id, amount)
VALUES
  (1, 1, 750),
  (1, 2, 1000),
  (1, 3, 1250),
  (1, 4, 500),
  (1, 5, 3000),
  (1, 6, 80);