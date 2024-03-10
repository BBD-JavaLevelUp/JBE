package com.jbe.client.MenuOptions;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.jbe.client.CurrentInvestor;
import com.jbe.client.MainMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.InventoryItem;
import com.jbe.client.Models.SellOrder;

public class CreateSellOrder{
    private static Scanner scanner = new Scanner(System.in);
    private ViewInventory vi;

    public CreateSellOrder() {
        vi = new ViewInventory();
    }

    public void display() {
        System.out.println("\nYour beans");
        subMenu();
    }

    public void subMenu(){
        while (true) {
            vi.printInventory();
            System.out.println("0. Go back to Main Menu");
            System.out.print("\nSelect bean you want to create sell order on: ");
            
            
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
    }

        private void handleChoice(int choice){
            if(choice == 0){
                MainMenu.display();
            }
            InventoryItem inventoryItem = vi.inventoryItems.get(choice-1);

            // Ask for selling price
            BigDecimal sellingPrice = null;
            while (sellingPrice == null) {
                try {
                    System.out.print("Enter selling price: ");
                    sellingPrice = scanner.nextBigDecimal();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid decimal.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }

            // Ask for amount of beans to sell
            int amount = -1;
            while (amount == -1) {
                try {
                    System.out.print("Enter amount: ");
                    amount = scanner.nextInt();
                    if (amount > inventoryItem.getAmount()) {
                        System.out.println("Amount exceeds available inventory!");
                        amount = -1; // Reset amount to trigger retry
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
            SellOrder newSellOrder = new SellOrder(choice, CurrentInvestor.getId(), choice, sellingPrice, amount, amount, OffsetDateTime.now(), true);
            RestApiHandler.CreateSellOrder(newSellOrder);
            MainMenu.display();
        }
    }

