package com.jbe.client;

import java.util.ArrayList;
import java.util.Date;

import com.jbe.client.Models.Bean;
import com.jbe.client.Models.SellOrder;

public class RestApiHandler {

    public static ArrayList<SellOrder> getAllSellOrders() {
        ArrayList<SellOrder> sellOrders = new ArrayList<>();
        sellOrders.add(new SellOrder(1, 101, 1, 1000.50, 50, 100, new Date(), true));
        sellOrders.add(new SellOrder(2, 102, 2, 120.75, 30, 50, new Date(), true));
        sellOrders.add(new SellOrder(3, 103, 3, 90.25, 40, 80, new Date(), true));
        
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
