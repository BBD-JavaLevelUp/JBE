package com.jbe.client;

import java.util.ArrayList;
import java.util.Date;

import com.jbe.client.Models.SellOrder;

public class RestApiHandler {

    public static ArrayList<SellOrder> getAllSellOrders() {
        ArrayList<SellOrder> sellOrders = new ArrayList<>();

        // Creating SellOrder instances
        SellOrder sellOrder1 = new SellOrder(1, 101, 201, 10.50, 50, 100, new Date(), true);
        SellOrder sellOrder2 = new SellOrder(2, 102, 202, 12.75, 30, 50, new Date(), true);
        SellOrder sellOrder3 = new SellOrder(3, 103, 203, 9.25, 40, 80, new Date(), true);

        // Adding SellOrder instances to the ArrayList
        sellOrders.add(sellOrder1);
        sellOrders.add(sellOrder2);
        sellOrders.add(sellOrder3);
        
        return sellOrders;
    }
    
}
