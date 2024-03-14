/*Insert data*/
INSERT INTO bean (name, default_price)
VALUES
	('Butter', 10.50),
	('Kidney', 8.75),
	('Sugar', 12.00);

INSERT INTO investor (investor_id, name, sa_id, email)
VALUES
  (1, 'John Doe', '9834567890123', 'john@yahoo.com'),
  (2, 'Jane Smith', '9934567690123', 'jane@hotmail.com'),
  (3, 'Michael Johnson', '0034567490123', 'michael@gmail.com');

INSERT INTO buy_order (buy_order_id, investor_id, bean_id, price, available_amount, total_amount, order_date, is_active)
VALUES
  (1, 1, 1, 10.75, 100, 1000, '2024-03-08 10:00:00', true),
  (2, 2, 2, 8.00, 150, 1500, '2024-03-08 11:00:00', true),
  (3, 3, 3, 11.50, 200, 2000, '2024-03-08 12:00:00', true);

INSERT INTO sell_order (sell_order_id, investor_id, bean_id, price, available_amount, total_amount, order_date, is_active)
VALUES
  (1, 1, 1, 12.00, 75, 750, '2024-03-08 10:30:00', true),
  (2, 2, 2, 9.50, 100, 1000, '2024-03-08 11:30:00', true),
  (3, 3, 3, 13.00, 125, 1250, '2024-03-08 12:30:00', true);

INSERT INTO transaction (transaction_id, buy_order_id, sell_order_id, transaction_date, amount)
VALUES
  (1, 1, 1, '2024-03-08 10:45:00', 75),
  (2, 2, 2, '2024-03-08 11:45:00', 100),
  (3, 3, 3, '2024-03-08 12:45:00', 125);

INSERT INTO inventory (inventory_id, investor_id, bean_id, amount)
VALUES
  (1, 1, 1, 500),
  (2, 2, 2, 750),
  (3, 3, 3, 1000);