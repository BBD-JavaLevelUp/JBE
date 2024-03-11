package com.jbe.client;

import java.util.Scanner;

import com.jbe.client.MenuOptions.ViewBeans;

public class AdminMenu {

    private static Scanner scanner = new Scanner(System.in);

    public static void display() {
    while (true) {
        System.out.println("\nMain Menu:");
        System.out.println("1. View beans");
        System.out.println("2. Create a bean");
        System.out.println("3. View investors");
        System.out.println("4. View an investor\'s inventory");
        System.out.println("5. Logout");
        
        System.out.print("\nEnter your choice: ");
        int choice = -1; // Default to an invalid choice

        try {
            String input = scanner.nextLine().trim(); 
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            continue;
        }
        switch (choice) {            
            case 1:
                ViewBeans vb = new ViewBeans();
                vb.display();
                break;
            case 5:
                CurrentInvestor.signOut();
                MainMenu.display();
                break;
            default:
                System.out.println("Invalid choice. Please try again.\n");
        }
    }
    }
}
