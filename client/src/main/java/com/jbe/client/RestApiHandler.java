package com.jbe.client;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.jbe.client.Models.Bean;
import com.jbe.client.Models.BuyOrder;
import com.jbe.client.Models.InventoryItem;
import com.jbe.client.Models.SellOrder;
import com.jbe.client.Models.TransactionItem;

public class RestApiHandler {

    public static ArrayList<SellOrder> getAllSellOrders() {
        ArrayList<SellOrder> sellOrders = new ArrayList<>();
        sellOrders.add(new SellOrder(1, 101, 1, new BigDecimal("10.50"), 100, 200, OffsetDateTime.now(), true));
        sellOrders.add(new SellOrder(2, 102, 2, new BigDecimal("12.75"), 150, 250, OffsetDateTime.now(), true));
        sellOrders.add(new SellOrder(3, 103, 3, new BigDecimal("9.25"), 80, 180, OffsetDateTime.now(), true));
        return sellOrders;
    }
    public static ArrayList<Bean> getAllBeans() {
        ArrayList<Bean> beans = new ArrayList<>();
        APICall.makeCall("/api/beans/beans", null);
        beans.add(new Bean(1, "Coffee beans", new BigDecimal(10.99)));
        beans.add(new Bean(2, "Baked beans", new BigDecimal(8.99)));
        beans.add(new Bean(3, "More beans", new BigDecimal(12.99)));
        return beans;
    }

    public static Bean getBean(int beanId) {
        ArrayList<Bean> beans = getAllBeans();
        for (Bean bean : beans) {
            if (bean.getBeanId() == beanId) {
                return bean;
            }
        }
        return null; // Bean with given beanId not found
    }
    public static ArrayList<InventoryItem> getInventory() {
      // call api using current investor api
      ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
      APICall.makeCall("/api/viewInventory", null);
      inventoryItems.add(new InventoryItem(1, "Arabica Beans", 100,new BigDecimal("109")));
      inventoryItems.add(new InventoryItem(2, "Cool beans", 200,new BigDecimal("79")));
      inventoryItems.add(new InventoryItem(3, "Senzu beans", 150,new BigDecimal("10.9")));
      return inventoryItems;
    }
    public static ArrayList<SellOrder> getInvestorSellOrders(int id) {
        ArrayList<SellOrder> sellOrders = new ArrayList<>();
        sellOrders.add(new SellOrder(1, 101, 1, new BigDecimal("109"), 140, 200, OffsetDateTime.now(), true));
        sellOrders.add(new SellOrder(2, 102, 2, new BigDecimal("75"), 10, 50, OffsetDateTime.now(), true));
        sellOrders.add(new SellOrder(3, 103, 3, new BigDecimal("587"), 78, 100, OffsetDateTime.now(), true));
        return sellOrders;
    }
    public static void deleteSellOrder(int sellOrderId) {
        System.out.println("Sell order deleted");
    }
    public static void acceptSellOrder(int sellOrderId) {
        System.out.println("Sell order accepted");
    }
    public static void CreateSellOrder(SellOrder newSellOrder) {
        System.out.println("Sell order created!");
    }
    public static void BuyBeansFromJBE(int beanId, int amount) {
        System.out.println("Beans bought!");
    }
    public static ArrayList<InventoryItem> getJBEInventory() {
      ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
      inventoryItems.add(new InventoryItem(1, "Arabica Beans", 100,new BigDecimal("109")));
      inventoryItems.add(new InventoryItem(2, "Cool beans", 200,new BigDecimal("79")));
      inventoryItems.add(new InventoryItem(3, "Senzu beans", 150,new BigDecimal("10.9")));
      inventoryItems.add(new InventoryItem(4, "Roasted beans", 10,new BigDecimal("302")));
      inventoryItems.add(new InventoryItem(6, "Energy beans", 3,new BigDecimal("1001")));
      return inventoryItems;
    }
    public static void deleteBuyOrder(int buyOrderId) {
        System.out.println("Buy order deleted");
    }
    public static void acceptBuyOrder(int buyOrderId) {
        System.out.println("Buy order accepted");
    }
    public static ArrayList<BuyOrder> getInvestorBuyOrders(int id) {
        ArrayList<BuyOrder> buyOrders = new ArrayList<>();
        buyOrders.add(new BuyOrder(1, 101, 1, new BigDecimal("109"), 140, 200, OffsetDateTime.now(), true));
        buyOrders.add(new BuyOrder(2, 102, 2, new BigDecimal("75"), 10, 50, OffsetDateTime.now(), true));
        buyOrders.add(new BuyOrder(3, 103, 3, new BigDecimal("587"), 78, 100, OffsetDateTime.now(), true));
        return buyOrders;
    }
    public static ArrayList<BuyOrder> getAllBuyOrders() {
        ArrayList<BuyOrder> buyOrders = new ArrayList<>();
        buyOrders.add(new BuyOrder(1, 101, 1, new BigDecimal("109"), 140, 200, OffsetDateTime.now(), true));
        buyOrders.add(new BuyOrder(2, 102, 2, new BigDecimal("75"), 10, 50, OffsetDateTime.now(), true));
        buyOrders.add(new BuyOrder(3, 103, 3, new BigDecimal("587"), 78, 100, OffsetDateTime.now(), true));
        return buyOrders;
    }
    public static void CreateBuyOrder(BuyOrder newBuyOrder) {
        System.out.println("buy order created!");
    }
    public static ArrayList<TransactionItem> getTransactions(int id) {
        ArrayList<TransactionItem> transactions = new ArrayList<>();
        transactions.add(new TransactionItem(OffsetDateTime.now(), "Coffee Beans", 100, new BigDecimal("5.00"), "Tebogo", "Alice"));
        transactions.add(new TransactionItem(OffsetDateTime.now(), "Tea Leaves", 50, new BigDecimal("3.50"), "Tebogo", "Eve"));
        transactions.add(new TransactionItem(OffsetDateTime.now(), "Cocoa Beans", 80, new BigDecimal("7.20"), "Tebogo", "Mallory"));
        return transactions;

    }
    public static ArrayList<TransactionItem> getAllTransactions() {
        ArrayList<TransactionItem> transactions = new ArrayList<>();
        transactions.add(new TransactionItem(OffsetDateTime.now(), "Coffee Beans", 100, new BigDecimal("5.00"), "John", "Alice"));
        transactions.add(new TransactionItem(OffsetDateTime.now(), "Tea Leaves", 50, new BigDecimal("3.50"), "Bob", "Eve"));
        transactions.add(new TransactionItem(OffsetDateTime.now(), "Cocoa Beans", 80, new BigDecimal("7.20"), "Charlie", "JBE"));
        transactions.add(new TransactionItem(OffsetDateTime.now(), "Vanilla Beans", 120, new BigDecimal("8.50"), "David", "Frank"));
        transactions.add(new TransactionItem(OffsetDateTime.now(), "Cherry Beans", 70, new BigDecimal("6.75"), "Emily", "JBE"));
        return transactions;

    }
    public static void editBean(int beanId, String newName, long newAmount, BigDecimal newPrice) {
        System.out.println("Bean edited!");
    }
    
}
