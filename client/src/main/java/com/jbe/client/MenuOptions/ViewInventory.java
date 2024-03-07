package com.jbe.client.MenuOptions;
import java.util.Scanner;

import com.jbe.client.MainMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.Investor;

public class ViewInventory {
    private static Scanner scanner = new Scanner(System.in);
    public Investor currentInvestor;
    public RestApiHandler restApiHandler;
    
    public ViewInventory(Investor currentInvestor,RestApiHandler restApiHandler){
        this.currentInvestor = currentInvestor;

        this.restApiHandler = restApiHandler;
    }

    public void display() {
        System.out.println("\nInventory:");
        // logic to get inventory using the rest api handler goes here and display the inventories
        subMenu();
    }

    public void subMenu(){
        while (true) {
            System.out.println("\n0. Go back to Main Menu");
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
                case 0:
                    MainMenu.display(currentInvestor, restApiHandler);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        }
    }

