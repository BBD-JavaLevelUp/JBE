package com.jbe.client.MenuOptions;
import java.util.ArrayList;
import java.util.Scanner;

import com.jbe.client.MainMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.InventoryItem;

public class ViewInventory {
    private static Scanner scanner = new Scanner(System.in);
    public ArrayList<InventoryItem>  inventoryItems;

    public ViewInventory(String string) {
        inventoryItems = RestApiHandler.getJBEInventory();
    }

    public ViewInventory() {
        inventoryItems = RestApiHandler.getInventory();
    }
    public ViewInventory(int investorId) {
        //inventoryItems = RestApiHandler.getInventory(investorId);
    }


    public void display() {
        System.out.println("\nInventory:");
        subMenu();
    }

    public void subMenu(){
        while (true) {
            printInventory();
            System.out.println("0. Go back to Main Menu");
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
                case 0:
                    MainMenu.display();
                    break;
                default:
                    System.out.println("Either you go back or just keep viewing your inventory");
            }
        }
        }
    public void printInventory(){
        for (int index = 0; index < inventoryItems.size(); index++) {
            InventoryItem inventoryItem = inventoryItems.get(index);
            System.out.println(index+1 + ". " + inventoryItem.getName() + " - " + inventoryItem.getAmount()+ " - R" + inventoryItem.getDefaultPrice() + " each.") ;
        }
    }
    }

