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
	('Sugar', 12.00);

INSERT INTO investor (name, sa_id, email)
VALUES
  ('JBE', '0000000000000', 'services@jbe.com'),
  ('Jane Smith', '9934567690123', 'jane@hotmail.com'),
  ('Michael Johnson', '0034567490123', 'michael@gmail.com');

INSERT INTO buy_order (investor_id, bean_id, price, available_amount, total_amount, order_date, is_active)
VALUES
  (1, 1, 10.75, 100, 1000, '2024-03-08 10:00:00', true),
  (2, 2, 8.00, 150, 1500, '2024-03-08 11:00:00', true),
  (3, 3, 11.50, 200, 2000, '2024-03-08 12:00:00', true);

INSERT INTO sell_order (investor_id, bean_id, price, available_amount, total_amount, order_date, is_active)
VALUES
  (1, 1, 12.00, 75, 750, '2024-03-08 10:30:00', true),
  (2, 2, 9.50, 100, 1000, '2024-03-08 11:30:00', true),
  (3, 3, 13.00, 125, 1250, '2024-03-08 12:30:00', true);

INSERT INTO transaction (buy_order_id, sell_order_id, transaction_date, amount)
VALUES
  (1, 1, '2024-03-08 10:45:00', 75),
  (2, 2, '2024-03-08 11:45:00', 100),
  (3, 3, '2024-03-08 12:45:00', 125);

INSERT INTO inventory (investor_id, bean_id, amount)
VALUES
  (1, 1, 500),
  (2, 2, 750),
  (3, 3, 1000);