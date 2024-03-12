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
	buying_price numeric NOT NULL,
	available_amount bigint NOT NULL,
	total_amount bigint NOT NULL,
	buy_order_date timestamp NOT NULL,
	is_active boolean NOT NULL,
	
	CONSTRAINT fk_BuyOrders_investorId FOREIGN KEY(investor_id) REFERENCES investor(investor_id),
	CONSTRAINT fk_BuyOrders_beanId FOREIGN KEY(bean_id) REFERENCES bean(bean_id)
);

CREATE TABLE sell_order (
	sell_order_id serial PRIMARY KEY,
	investor_id int NOT NULL,
	bean_id int NOT NULL,
	selling_price numeric NOT NULL,
	available_amount bigint NOT NULL,
	total_amount bigint NOT NULL,
	sell_order_date timestamp NOT NULL,
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