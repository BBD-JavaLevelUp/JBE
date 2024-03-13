package com.jbe.client;

import java.util.Scanner;

import com.jbe.client.MenuOptions.BuyBeansFromJBE;
import com.jbe.client.MenuOptions.CreateBuyOrder;
import com.jbe.client.MenuOptions.CreateSellOrder;
import com.jbe.client.MenuOptions.ViewBeans;
import com.jbe.client.MenuOptions.ViewBuyOrders;
import com.jbe.client.MenuOptions.ViewInventory;
import com.jbe.client.MenuOptions.ViewSellOrders;
import com.jbe.client.MenuOptions.ViewTransactions;


public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void display() {
    while (true) {
        System.out.println("\nMain Menu:");
        if (!CurrentInvestor.signedIn) {
            System.out.println("1. Sign Up");
            System.out.println("2. Sign In");
            System.out.println("3. Exit");
        } else {
            System.out.println("1. View Beans");
            System.out.println("2. View Inventory");
            System.out.println("3. View Profit/Loss");
            System.out.println("4. View Transactions");
            System.out.println("5. View My Sell Orders");
            System.out.println("6. View My Buy Orders"); 
            System.out.println("7. View All Sell Orders"); 
            System.out.println("8. View All Buy Orders");
            System.out.println("9. Create a Sell Order");
            System.out.println("10. Create a Buy Order");
            System.out.println("11. Buy from JBE");
            System.out.println("12. Logout");
        }
        System.out.print("\nEnter your choice: ");
        int choice = -1; // Default to an invalid choice

        try {
            String input = scanner.nextLine().trim(); // Read the whole line of input
            choice = Integer.parseInt(input); // Try to convert the input to an integer
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            continue; // Skip to the next iteration of the loop immediately
        }

        if (!CurrentInvestor.signedIn && (choice < 1 || choice > 3)) {
            System.out.println("Please sign in or sign up to access this feature.");
            continue; // Skip the rest of the loop and show the menu again
        }

        switch (choice) {
            case 1:
                if (!CurrentInvestor.signedIn) {
                    CurrentInvestor.signUp();
                    if(CurrentInvestor.isAdmin){
                        AdminMenu.display();
                    }
                } else
                {
                    RestApiHandler.getAllBeans();
                }
                break;
            case 2:
                if (!CurrentInvestor.signedIn) {
                    CurrentInvestor.signIn();
                    if(CurrentInvestor.isAdmin){
                        AdminMenu.display();
                    }
                } else
                {
                    RestApiHandler.getInventory(CurrentInvestor.getId());
                }
                break;
            case 3:
                if (!CurrentInvestor.signedIn) {
                    System.out.println("Exiting...");
                    System.exit(0);
                } else {
                    System.out.println("viewing profit/loss");
                }
                break;
            case 4:
                ViewTransactions vt = new ViewTransactions(CurrentInvestor.getId());
                vt.display();
                break;
            case 5:
                ViewSellOrders mySellOrders = new ViewSellOrders(CurrentInvestor.getId());
                mySellOrders.display();
                break;
            case 6:
                ViewBuyOrders myBuyOrders = new ViewBuyOrders(CurrentInvestor.getId());
                myBuyOrders.display();
                break;    
            case 7:
                ViewSellOrders vso = new ViewSellOrders();
                vso.display();
                break;
            case 8:
                ViewBuyOrders vbo = new ViewBuyOrders();
                vbo.display();
                break;
            case 9:
                CreateSellOrder cso = new CreateSellOrder();
                cso.display();
                break;
            case 10:
                CreateBuyOrder cbo = new CreateBuyOrder();
                cbo.display();
            break;            
            case 11:
                BuyBeansFromJBE buyBeans = new BuyBeansFromJBE();
                buyBeans.display();
                break;              
            case 12:
                CurrentInvestor.signOut();
                break;
            default:
                System.out.println("Invalid choice. Please try again.\n");
        }
    }
    }
}
