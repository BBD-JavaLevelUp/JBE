package com.jbe.client;

import java.util.Scanner;

import com.jbe.client.MenuOptions.CreateBean;
import com.jbe.client.MenuOptions.ViewBeans;
import com.jbe.client.MenuOptions.ViewInvestorInventory;
import com.jbe.client.MenuOptions.ViewInvestors;


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
                RestApiHandler.getAllBeans();
                break;
            case 2:
                CreateBean cb = new CreateBean();
                cb.display();
                break;
            case 3:
                ViewInvestors vi = new ViewInvestors();
                vi.display();
                break;
            case 4:
                ViewInvestorInventory vii = new ViewInvestorInventory();
                vii.display();
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
