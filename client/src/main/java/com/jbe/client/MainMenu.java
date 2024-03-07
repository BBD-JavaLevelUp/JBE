package com.jbe.client;

import java.util.Scanner;

import com.jbe.client.MenuOptions.ViewInventory;
import com.jbe.client.MenuOptions.ViewSellOrders;
import com.jbe.client.Models.Investor;

public class MainMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void display(Investor currentInvestor,RestApiHandler restApiHandler) {
    while (true) {
        System.out.println("\nMain Menu:");
        if (currentInvestor == null) {
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
            System.out.println("8. Make a Sell Order");
            System.out.println("9. Make a Buy Order");
            System.out.println("10. Buy from JBE");
            System.out.println("11. Logout");
        }
        System.out.print("Enter your choice: ");
        int choice = -1; // Default to an invalid choice

        try {
            String input = scanner.nextLine().trim(); // Read the whole line of input
            choice = Integer.parseInt(input); // Try to convert the input to an integer
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            continue; // Skip to the next iteration of the loop immediately
        }

        if (currentInvestor == null && (choice < 1 || choice > 3)) {
            System.out.println("Please sign in or sign up to access this feature.");
            continue; // Skip the rest of the loop and show the menu again
        }

        switch (choice) {
            case 1:
                if (currentInvestor == null) {
                    System.out.println("signing up");
                } else {
                    ViewInventory vi = new ViewInventory(currentInvestor, restApiHandler);
                    vi.display();
                }
                break;
            case 2:
                if (currentInvestor == null) {
                    currentInvestor = new Investor();
                } else {
                    System.out.println("viewing profit/loss");
                }
                break;
            case 3:
                if (currentInvestor == null) {
                    System.out.println("Exiting...");
                    System.exit(0);
                } else {
                    System.out.println("viewing transactions");
                }
                break;
            case 6:
                ViewSellOrders vso = new ViewSellOrders(currentInvestor, restApiHandler);
                vso.display();
                break;    
            case 11:
                currentInvestor = null;
                break;
            default:
                System.out.println("Invalid choice. Please try again.\n");
        }
    }
    }
}
