package com.jbe.client;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.jbe.client.Models.Bean;
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
        beans.add(new Bean(1, "Coffee beans", 10.99));
        beans.add(new Bean(2, "Baked beans", 8.99));
        beans.add(new Bean(3, "More beans", 12.99));
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
    
}
