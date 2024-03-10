package com.jbe.client;

import java.util.Scanner;

import com.jbe.client.MenuOptions.BuyBeansFromJBE;
import com.jbe.client.MenuOptions.CreateSellOrder;
import com.jbe.client.MenuOptions.ViewInventory;
import com.jbe.client.MenuOptions.ViewSellOrders;


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
            System.out.println("1. View Inventory");
            System.out.println("2. View Profit/Loss");
            System.out.println("3. View Transactions");
            System.out.println("4. View My Sell Orders");
            System.out.println("5. View My Buy Orders"); 
            System.out.println("6. View All Sell Orders"); 
            System.out.println("7. View All Buy Orders");
            System.out.println("8. Create a Sell Order");
            System.out.println("9. Create a Buy Order");
            System.out.println("10. Buy from JBE");
            System.out.println("11. Logout");
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
                } else {
                    ViewInventory vi = new ViewInventory();
                    vi.display();
                }
                break;
            case 2:
                if (!CurrentInvestor.signedIn) {
                    CurrentInvestor.signIn();
                } else {
                    System.out.println("viewing profit/loss");
                }
                break;
            case 3:
                if (!CurrentInvestor.signedIn) {
                    System.out.println("Exiting...");
                    System.exit(0);
                } else {
                    System.out.println("viewing transactions");
                }
                break;
            case 4:
                ViewSellOrders mySellOrders = new ViewSellOrders(CurrentInvestor.getId());
                mySellOrders.display();
                break;   
            case 6:
                ViewSellOrders vso = new ViewSellOrders();
                vso.display();
                break;
            case 8:
                CreateSellOrder cso = new CreateSellOrder();
                cso.display();
                break;
            case 10:
                BuyBeansFromJBE buyBeans = new BuyBeansFromJBE();
                buyBeans.display();
                break;              
            case 11:
                CurrentInvestor.signOut();
                break;
            default:
                System.out.println("Invalid choice. Please try again.\n");
        }
    }
    }
}
