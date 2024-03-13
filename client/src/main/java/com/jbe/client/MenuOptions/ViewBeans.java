package com.jbe.client.MenuOptions;
import java.math.BigDecimal;
import java.util.Scanner;

import com.jbe.client.AdminMenu;
import com.jbe.client.CurrentInvestor;
import com.jbe.client.MainMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.InventoryItem;

public class ViewBeans{
    private static Scanner scanner = new Scanner(System.in);
    private ViewInventory vi;

    public ViewBeans() {
        vi = new ViewInventory("JBE");
    }

    public void display() {
        System.out.println("\nAvailable JBE Beans");
        subMenu();
    }

    public void subMenu(){
        while (true) {
            vi.printInventory();
            System.out.println("0. Go back to Main Menu");
            if(CurrentInvestor.isAdmin)
            {
                System.out.print("\nSelect bean you want to edit : ");
                int choice = -1;
    
                try {
                    String input = scanner.nextLine().trim();
                    choice = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }
        
                if (choice > vi.inventoryItems.size()) {
                    System.out.println("If you not serious tell me");
                    continue; 
                }
        
                handleChoice(choice);
            }
            else
            {
                int choice = -1;
                try
                {
                    System.out.print("\nEnter your choice : ");
                    String input = scanner.nextLine().trim();
                    choice = Integer.parseInt(input);
                    if(choice != 0)
                    {
                        throw new NumberFormatException();
                    }
                    else
                    {
                        MainMenu.display();
                    }
                } 
                catch (NumberFormatException e)
                {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }
            }
            
        }
    }

    private void handleChoice(int choice)
    {
        if(choice == 0){
            MainMenu.display();
        }
        InventoryItem inventoryItem = vi.inventoryItems.get(choice-1);

        // Ask for a new bean name
        String newName = null;
        while (true) {
            System.out.print("Enter new name (leave blank if no change): ");
            newName = scanner.nextLine();
            if (newName.trim().isEmpty()) {
                newName = inventoryItem.getName();
            }
            break;
        }

        // Ask for new amount of beans
        long newAmount = -1;
        while (true) {
            try {
                System.out.print("Enter new amount (leave blank if no change): ");
                String input = scanner.nextLine();
                if (input.trim().isEmpty()) {
                    newAmount = inventoryItem.getAmount(); 
                    break;
                } else {
                    newAmount = Long.parseLong(input);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Ask for new price
        BigDecimal newPrice = null;
        while (true) {
            System.out.print("Enter new price (leave blank if no change): ");
            String priceInput = scanner.nextLine();
            if (priceInput.trim().isEmpty()) {
                newPrice = inventoryItem.getDefaultPrice(); 
                break;
            } else {
                try {
                    newPrice = new BigDecimal(priceInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid price.");
                }
            }
        }

        RestApiHandler.editBean(inventoryItem.getBeanId(), newName, newAmount, newPrice);
        AdminMenu.display();
    }
}

