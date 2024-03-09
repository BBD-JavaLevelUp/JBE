-- Insert data into Investors table
INSERT INTO Investors (investorId, name, SAId, email)
VALUES
  (1, 'John Doe', '9834567890123', 'john@yahoo.com'),
  (2, 'Jane Smith', '9934567690123', 'jane@hotmail.com'),
  (3, 'Michael Johnson', '0034567490123', 'michael@gmail.com');

-- Insert data into Beans table
INSERT INTO Beans (beanId, name, defaultPrice)
VALUES
  (1, 'Butter', '10.50'),
  (2, 'Kidney', '8.75'),
  (3, 'Sugar', '12.00');

-- Insert data into BuyOrders table
INSERT INTO BuyOrders (buyOrderId, investorId, beanId, buyingPrice, availableAmount, totalAmount, buyOrderDate, isActive)
VALUES
  (1, 1, 1, 10.75, 100, 1000, '2024-03-08 10:00:00', true),
  (2, 2, 2, 8.00, 150, 1500, '2024-03-08 11:00:00', true),
  (3, 3, 3, 11.50, 200, 2000, '2024-03-08 12:00:00', true);

-- Insert data into SellOrders table
INSERT INTO SellOrders (sellOrderId, investorId, beanId, sellingPrice, availableAmount, totalAmount, sellOrderDate, isActive)
VALUES
  (1, 1, 1, 12.00, 75, 750, '2024-03-08 10:30:00', true),
  (2, 2, 2, 9.50, 100, 1000, '2024-03-08 11:30:00', true),
  (3, 3, 3, 13.00, 125, 1250, '2024-03-08 12:30:00', true);

-- Insert data into Transactions table
INSERT INTO Transactions (transactionId, buyOrderId, sellOrderId, transactionDate, amount)
VALUES
  (1, 1, 1, '2024-03-08 10:45:00', 75),
  (2, 2, 2, '2024-03-08 11:45:00', 100),
  (3, 3, 3, '2024-03-08 12:45:00', 125);

-- Insert data into Inventory table
INSERT INTO Inventory (inventoryId, investorId, beanId, amount)
VALUES
  (1, 1, 1, 500),
  (2, 2, 2, 750),
  (3, 3, 3, 1000);
