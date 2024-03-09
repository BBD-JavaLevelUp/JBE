package com.jbe.client;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.jbe.client.Models.Bean;
import com.jbe.client.Models.InventoryItem;
import com.jbe.client.Models.SellOrder;

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
      inventoryItems.add(new InventoryItem(1, "Arabica Beans", 100));
      inventoryItems.add(new InventoryItem(2, "Cool beans", 200));
      inventoryItems.add(new InventoryItem(3, "Senzu beans", 150));
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
    
}
