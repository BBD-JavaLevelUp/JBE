package com.jbe.client.MenuOptions;
import java.util.ArrayList;
import java.util.Scanner;

import com.jbe.client.MainMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.TransactionItem;

public class ViewTransactions {
    private static Scanner scanner = new Scanner(System.in);
    public ArrayList<TransactionItem>  transactionItems;

    public ViewTransactions(int id) {
        //transactionItems = RestApiHandler.getTransactions(id);
    }

    public ViewTransactions() {
        transactionItems = RestApiHandler.getAllTransactions();
    }

    public void display() {
        System.out.println("\nTransactions");
        subMenu();
    }

    public void subMenu(){
        while (true) {
            printTransactions();
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
                    System.out.println("Either you go back or just keep viewing your transactions");
            }
        }
        }
    public void printTransactions(){
        for (int index = 0; index < transactionItems.size(); index++) {
            TransactionItem transactionItem = transactionItems.get(index);
            String seller = transactionItem.getSellerName() + " sold " + transactionItem.getBeanName() + " - " + transactionItem.getAmount()+ " - R" + transactionItem.getPrice();
            String buyer = " to " + transactionItem.getBuyerName();
            System.out.println(index+1 + ". " + transactionItem.getTransactionDate() + " - " + seller + buyer) ;
        }
    }
    }

