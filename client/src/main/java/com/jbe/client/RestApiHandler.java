package com.jbe.client;

import java.util.Scanner;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;

import com.jbe.client.MenuOptions.ViewSellOrders;
import com.jbe.client.Models.Bean;
import com.jbe.client.Models.BuyOrder;
import com.jbe.client.Models.InventoryItem;
import com.jbe.client.Models.Investor;
import com.jbe.client.Models.SellOrder;
import com.jbe.client.Models.TransactionItem;

import org.json.*;

public class RestApiHandler {

    private static Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_MAGENTA = "\u001B[35m";

    //View Available Beans
    public static void getAllBeans()
    {
        String response = APICall.get("/api/beans", null);
        JSONArray jsonResponse = new JSONArray(response);

        System.out.println(ANSI_BLUE + "\nBeans listed on the JBE:" + ANSI_RESET);
        for(Object json : jsonResponse)
        {
            JSONObject bean = (JSONObject) json;
            //BigDecimal jbePrice = (BigDecimal) bean.get("jbePrice");
            //BigDecimal marketPrice = !bean.get("marketPrice").equals(null) ? (BigDecimal) bean.get("marketPrice") : BigDecimal.valueOf(0);
            //BigDecimal lowestPrice = marketPrice.equals(BigDecimal.valueOf(0)) ? jbePrice : jbePrice.min(marketPrice);
            System.out.println(
                " " +
                bean.get("name") + " - R" +
                bean.get("marketPrice") + " per bean"
            );
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
    }

    //View Inventory
    public static void getInventory(int investorId)
    {
        String response = APICall.get("/api/inventories/investor/" + investorId, null);
        JSONArray jsonResponse = new JSONArray(response);
        BigDecimal netProfit = BigDecimal.valueOf(0);

        System.out.println(ANSI_BLUE + "\nYour Beans:" + ANSI_RESET);
        for(Object json : jsonResponse)
        {
            JSONObject inventory = (JSONObject) json;
            BigDecimal profit = (BigDecimal) inventory.get("profit");
            String profitLoss = profit.compareTo(BigDecimal.valueOf(0)) >= 0 ? ANSI_GREEN + "R" + inventory.get("profit") : ANSI_RED + "R" + inventory.get("profit");
            System.out.println(
                " " +
                inventory.get("beanName") + " - " +
                inventory.get("amount") + " beans - " +
                profitLoss + ANSI_RESET
            );

            netProfit = netProfit.add(profit);
        }

        if(netProfit.compareTo(BigDecimal.valueOf(0)) >= 0)
        {
            System.out.println(
                ANSI_MAGENTA + "\nNet Profit/Loss: " + ANSI_RESET + ANSI_GREEN + "R" + netProfit + ANSI_RESET
            );
        }
        else
        {
            System.out.println(
                ANSI_MAGENTA + "\nNet Profit/Loss: " + ANSI_RESET + ANSI_RED + "R" + netProfit + ANSI_RESET
            );
        }

        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
    }

    //View transactions
    public static void getTransactions(int investorId)
    {
        //Sold
        String response = APICall.get("/api/transactions/selling-investor/" + investorId, null);
        JSONArray jsonResponse = new JSONArray(response);

        System.out.println(ANSI_BLUE + "\nBeans Sold:" + ANSI_RESET);
        for(Object json : jsonResponse)
        {
            JSONObject transactions = (JSONObject) json;
            String date = (String) transactions.get("transactionDate");
            date = date.substring(0, 10);

            System.out.println(
                " " +
                transactions.get("beanName") + " - " +
                transactions.get("amount") + " beans sold @ R" +
                transactions.get("price") + " - " +
                date
            );
        }

        //Bought
        response = APICall.get("/api/transactions/buying-investor/" + investorId, null);
        jsonResponse = new JSONArray(response);

        System.out.println(ANSI_BLUE + "\nBeans Bought:" + ANSI_RESET);
        for(Object json : jsonResponse)
        {
            JSONObject transactions = (JSONObject) json;
            String date = (String) transactions.get("transactionDate");
            date = date.substring(0, 10);

            System.out.println(
                " " +
                transactions.get("beanName") + " - " +
                transactions.get("amount") + " beans bought @ R" +
                transactions.get("price") + " - " +
                date
            );
        }

        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
    }

    //View Sell Orders
    public static void getInvestorSellOrders(int investorId)
    {
        String response = APICall.get("/api/sell-orders/investor/" + investorId, null);
        JSONArray jsonResponse = new JSONArray(response);

        System.out.println(ANSI_BLUE + "\nYour Sell Orders:" + ANSI_RESET);
        for(Object json : jsonResponse)
        {
            JSONObject sellOrders = (JSONObject) json;
            int sold = (int) sellOrders.get("totalAmount") - (int) sellOrders.get("availableAmount");
            String active = "Inactive";
            String color = ANSI_RED;
            
            if(sellOrders.get("active").equals(true))
            {
                active = "Active";
                color = ANSI_GREEN;
            }

            System.out.println(
                " " +
                sellOrders.get("beanName") + " - " +
                sold + "/" + sellOrders.get("totalAmount") + " sold @ R" + sellOrders.get("price") + " each - " +
                "Status: " + color + active + ANSI_RESET
            );
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
    }

    //View Buy Orders
    public static void getInvestorBuyOrders(int investorId)
    {
        String response = APICall.get("/api/buy-orders/investor/" + investorId, null);
        JSONArray jsonResponse = new JSONArray(response);

        System.out.println(ANSI_BLUE + "\nYour Buy Orders:" + ANSI_RESET);
        for(Object json : jsonResponse)
        {
            JSONObject buyOrders = (JSONObject) json;
            int bought = (int) buyOrders.get("totalAmount") - (int) buyOrders.get("availableAmount");
            String active = "Inactive";
            String color = ANSI_RED;
            
            if(buyOrders.get("active").equals(true))
            {
                active = "Active";
                color = ANSI_GREEN;
            }

            System.out.println(
                " " +
                buyOrders.get("beanName") + " - " +
                bought + "/" + buyOrders.get("totalAmount") + " bought @ R" + buyOrders.get("price") + " each - " +
                "Status: " + color + active + ANSI_RESET
            );
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
    }

    //View All Sell Orders
    public static void getSellOrders()
    {
        System.out.println("\nChoose a bean: ");
        System.out.println("0. All Beans");

        //Get list of beans
        String response = APICall.get("/api/beans", null);
        JSONArray jsonResponse = new JSONArray(response);
        ArrayList<Bean> beans = new ArrayList<>();
        int i = 1;
        for(Object json : jsonResponse)
        {
            JSONObject bean = (JSONObject) json;
            beans.add(new Bean((int) bean.get("beanId"), (String) bean.get("name"), (BigDecimal) bean.get("defaultPrice")));
            System.out.println(i++ + ". " + bean.get("name"));
        }
        
        String input = scanner.nextLine().trim();
        try{
        int choice = Integer.parseInt(input);

        if(choice == 0)
        {
            response = APICall.get("/api/sell-orders/active", null);
        }
        else if(choice-1 < beans.size())
        {
            response = APICall.get("/api/sell-orders/active/beans/" + beans.get(choice-1).getBeanId(), null);
        }
        else
        {
            System.out.println("Invalid input");
            return;
        }
        } catch (NumberFormatException e) {
            System.out.println("You were suppose to input a number and you chose that. Wow");
            return;
        }

        jsonResponse = new JSONArray(response);
        ArrayList<SellOrder> sellOrders = new ArrayList<>();
        for(Object json : jsonResponse)
        {
            JSONObject sellOrder = (JSONObject) json;
            sellOrders.add(new SellOrder((int) sellOrder.get("sellOrderId"),
                                        (int) sellOrder.get("investorId"),
                                        (int) sellOrder.get("beanId"), 
                                        (BigDecimal) sellOrder.get("price"),
                                        (int) sellOrder.get("availableAmount"),
                                        (int) sellOrder.get("totalAmount"),
                                        (String) sellOrder.get("orderDate"),
                                        (boolean) sellOrder.get("active"),
                                        (String) sellOrder.get("beanName")

                            ));
        }

        System.out.println(ANSI_BLUE + "\nCurrent Listings:" + ANSI_RESET);
        ViewSellOrders vso = new ViewSellOrders(sellOrders);
        vso.display();
    }

    //View All Buy Orders
    public static void getBuyOrders()
    {
        System.out.println("\nChoose a bean: ");
        System.out.println("0. All Beans");

        //Get list of beans
        String response = APICall.get("/api/beans", null);
        JSONArray jsonResponse = new JSONArray(response);
        ArrayList<Bean> beans = new ArrayList<>();
        int i = 1;
        for(Object json : jsonResponse)
        {
            JSONObject bean = (JSONObject) json;
            beans.add(new Bean((int) bean.get("beanId"), (String) bean.get("name"), (BigDecimal) bean.get("defaultPrice")));
            System.out.println(i++ + ". " + bean.get("name"));
        }
        
        String input = scanner.nextLine().trim();
        int choice = Integer.parseInt(input);

        if(choice == 0)
        {
            response = APICall.get("/api/buy-orders/active", null);
        }
        else if(choice-1 < beans.size())
        {
            response = APICall.get("/api/buy-orders/active/beans/" + beans.get(choice-1).getBeanId(), null);
        }
        else
        {
            System.out.println("Invalid input");
            return;
        }

        jsonResponse = new JSONArray(response);

        System.out.println(ANSI_BLUE + "\nCurrent Listings:" + ANSI_RESET);
        for(Object json : jsonResponse)
        {
            JSONObject buyOrders = (JSONObject) json;
            
            System.out.println(
                " " +
                buyOrders.get("beanName") + " - " +
                buyOrders.get("availableAmount") + " bid @ R" + buyOrders.get("price") + " each"
            );
        }
        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
    }

    //Create Sell Order
    public static void CreateSellOrder(SellOrder newSellOrder)
    {
        String body = "{" + 
            "\"investorId\":" + CurrentInvestor.getId() + ",\n" +
            "\"beanId\":" + newSellOrder.getBeanId() + ",\n" +
            "\"price\":" + newSellOrder.getSellingPrice() + ",\n" +
            "\"availableAmount\":" + newSellOrder.getAvailableAmount() + ",\n" +
            "\"totalAmount\":" + newSellOrder.getTotalAmount() + ",\n" +
            "\"orderDate\":" + "\"" + newSellOrder.getSellOrderDate() + "\",\n" + 
            "\"isActive\":" + newSellOrder.isActive() + "\n" + 
            "}";

        System.out.println(body);
        APICall.post("/api/sell-orders", body);
        System.out.println(ANSI_GREEN + "Sell order placed" + ANSI_RESET);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
    }

    //Create Buy Order

/*     public static ArrayList<SellOrder> getAllSellOrders() {
        ArrayList<SellOrder> sellOrders = new ArrayList<>();
        sellOrders.add(new SellOrder(1, 101, 1, new BigDecimal("10.50"), 100, 200, OffsetDateTime.now(), true));
        sellOrders.add(new SellOrder(2, 102, 2, new BigDecimal("12.75"), 150, 250, OffsetDateTime.now(), true));
        sellOrders.add(new SellOrder(3, 103, 3, new BigDecimal("9.25"), 80, 180, OffsetDateTime.now(), true));
        return sellOrders;
    } */

    public static Bean getBean(int beanId) {
        return null;
        // ArrayList<Bean> beans = getAllBeans();
        // for (Bean bean : beans) {
        //     if (bean.getBeanId() == beanId) {
        //         return bean;
        //     }
        // }
        // return null; // Bean with given beanId not found
    }

    public static ArrayList<InventoryItem> getInventory() {
      // call api using current investor api
      ArrayList<InventoryItem> inventoryItems = new ArrayList<>();
      //APICall.makeCall("/api/viewInventory", null);
      inventoryItems.add(new InventoryItem(1, "Arabica Beans", 100,new BigDecimal("109")));
      inventoryItems.add(new InventoryItem(2, "Cool beans", 200,new BigDecimal("79")));
      inventoryItems.add(new InventoryItem(3, "Senzu beans", 150,new BigDecimal("10.9")));
      return inventoryItems;
    }

   
    public static void deleteSellOrder(int sellOrderId) {
        System.out.println("Sell order deleted");
    }
    public static void acceptSellOrder(SellOrder sellOrder, long amount) {
      
        String body = "{" + 
        "\"investorId\":" + CurrentInvestor.getId() + ",\n" +
        "\"beanId\":" + sellOrder.getBeanId() + ",\n" +
        "\"price\":" + sellOrder.getSellingPrice() + ",\n" +
        "\"availableAmount\":" + amount + ",\n" +
        "\"totalAmount\":" + amount + ",\n" +
        "\"orderDate\":" + "\"" + OffsetDateTime.now() + "\",\n" + 
        "\"isActive\": true\n" + 
        "}";

        System.out.println(body);
        System.out.println(APICall.post("/api/buy-orders", body));
        System.out.println(ANSI_GREEN + "Buy order placed" + ANSI_RESET);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
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
    public static void createBean(String name, long amount, BigDecimal price) {
        System.out.println("bean created!");
    }
    public static ArrayList<Investor> getInvestors() {
        ArrayList<Investor> investors = new ArrayList<>();
        investors.add(new Investor(1, "John Doe", "9902185503086", "johndoe@example.com"));
        investors.add(new Investor(2, "Jane Smith", "0001206603017", "janesmith@example.com"));
        investors.add(new Investor(3, "Alice Johnson", "8912287741625", "alicejohnson@example.com"));
        return investors;
    }
    
}
