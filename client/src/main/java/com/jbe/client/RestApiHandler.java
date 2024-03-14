package com.jbe.client;

import java.util.Scanner;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import com.jbe.client.MenuOptions.ViewSellOrders;
import com.jbe.client.Models.Bean;
import com.jbe.client.Models.BuyOrder;
import com.jbe.client.Models.Investor;
import com.jbe.client.Models.SellOrder;

import org.json.*;

public class RestApiHandler {

    private static Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_MAGENTA = "\u001B[35m";

    public static ArrayList<Bean> getBeansList(){
        ArrayList<Bean> beans = new ArrayList<>();
        String response = APICall.get("/api/beans", null);
        JSONArray jsonResponse = new JSONArray(response);

        for(Object json : jsonResponse)
        {
            JSONObject bean = (JSONObject) json;
            beans.add(new Bean((int) bean.get("beanId"), (String) bean.get("name"), (BigDecimal) bean.get("defaultPrice")));
        }
        return beans;
    }
    //View Available Beans
    public static void getAllBeans()
    {
        String response = APICall.get("/api/beans", null);
        JSONArray jsonResponse = new JSONArray(response);

        System.out.println(ANSI_BLUE + "\nBeans listed on the JBE:" + ANSI_RESET);
        for(Object json : jsonResponse)
        {
            JSONObject bean = (JSONObject) json;
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
    public static void getInventory(int investorId,boolean viewingOwn)
    {
        String response = APICall.get("/api/inventories/investor/" + investorId, null);
        JSONArray jsonResponse = new JSONArray(response);
        BigDecimal netProfit = BigDecimal.valueOf(0);

        if(viewingOwn) 
            System.out.println(ANSI_BLUE + "\nYour Beans:" + ANSI_RESET);
        else
            System.out.println(ANSI_BLUE + "\nAn Investor's inventory:" + ANSI_RESET);
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
            System.out.println(RestApiHandler.ANSI_RED + "Invalid input" + RestApiHandler.ANSI_RESET);
            return;
        }
        } catch (NumberFormatException e) {
            System.out.println(RestApiHandler.ANSI_RED + "You were suppose to input a number and you chose that. Wow" + RestApiHandler.ANSI_RESET );
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
            System.out.println(RestApiHandler.ANSI_RED + "Invalid input" + RestApiHandler.ANSI_RESET );
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
    public static void CreateSellOrder(SellOrder sellOrder)
    {
        JSONObject jsonData = new JSONObject();
        jsonData.put("investorId", CurrentInvestor.getId());
        jsonData.put("beanId", sellOrder.getBeanId());
        jsonData.put("price", sellOrder.getSellingPrice());
        jsonData.put("availableAmount", sellOrder.getAvailableAmount());
        jsonData.put("totalAmount", sellOrder.getTotalAmount());
        jsonData.put("orderDate", OffsetDateTime.now());
        jsonData.put("isActive", true);

        System.out.println(jsonData);
        System.out.println(APICall.post("/api/sell-orders", jsonData.toString()));
        System.out.println(ANSI_GREEN + "Sell order placed" + ANSI_RESET);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
    }

    //Create Buy Order
     public static void CreateBuyOrder(BuyOrder buyOrder)
    {
        JSONObject jsonData = new JSONObject();
        jsonData.put("investorId", CurrentInvestor.getId());
        jsonData.put("beanId", buyOrder.getBeanId());
        jsonData.put("price", buyOrder.getSellingPrice());
        jsonData.put("availableAmount", buyOrder.getAvailableAmount());
        jsonData.put("totalAmount", buyOrder.getTotalAmount());
        jsonData.put("orderDate", OffsetDateTime.now());
        jsonData.put("isActive", true);

        System.out.println(jsonData);
        System.out.println(APICall.post("/api/buy-orders", jsonData.toString()));
        System.out.println(ANSI_GREEN + "buy order placed" + ANSI_RESET);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
    } 

    public static void acceptSellOrder(SellOrder sellOrder, long amount)
    {
        JSONObject jsonData = new JSONObject();
        jsonData.put("investorId", CurrentInvestor.getId());
        jsonData.put("beanId", sellOrder.getBeanId());
        jsonData.put("price", sellOrder.getSellingPrice());
        jsonData.put("availableAmount", amount);
        jsonData.put("totalAmount", amount);
        jsonData.put("orderDate", OffsetDateTime.now());
        jsonData.put("isActive", true);

        System.out.println(jsonData);
        System.out.println(APICall.post("/api/buy-orders", jsonData.toString()));
        System.out.println(ANSI_GREEN + "Buy order placed" + ANSI_RESET);
        System.out.print("\nPress enter to continue...");
        scanner.nextLine().trim();
    }

    public static void createBean(String name, long amount, BigDecimal price) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("name", name);
        jsonData.put("defaultPrice", price);
        APICall.post("/api/beans/"+amount,jsonData.toString());
        System.out.println(RestApiHandler.ANSI_GREEN + "New bean created!" + RestApiHandler.ANSI_RESET);

    }
    public static ArrayList<Investor> getInvestors() {
        ArrayList<Investor> investors = new ArrayList<>();
        String response = APICall.get("/api/investors", null);
        JSONArray jsonResponse = new JSONArray(response);

        for(Object json : jsonResponse)
        {
            JSONObject investor = (JSONObject) json;
            investors.add(new Investor((int) investor.get("investorId"), 
                            (String) investor.get("name"), 
                            (String) investor.get("saId"),
                            (String) investor.get("email")
                            ));
        }
        return investors;

    }
    public static void createInvestor(String name, String SAid, String email) {
        JSONObject jsonData = new JSONObject();
        jsonData.put("name", name);
        jsonData.put("saId", SAid);
        jsonData.put("email", email);
        System.out.println(jsonData.toString());
        APICall.post("/api/investors",jsonData.toString());
        System.out.println(RestApiHandler.ANSI_GREEN + "New investor created!" + RestApiHandler.ANSI_RESET);
    }
    
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
    public static Investor getInvestor(String id){
        //if no response return null
        String response = APICall.get("/api/investors/id/"+id, null);
        JSONObject jsonResponse = new JSONObject(response);
        Investor investor = new Investor((int)jsonResponse.get("investorId"),(String) jsonResponse.get("name"), (String)jsonResponse.get("saId"),(String)jsonResponse.get("email"));
        return investor;
    }
}
