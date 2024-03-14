package com.jbe.client.MenuOptions;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.jbe.client.MainMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.SellOrder;

public class ViewSellOrders{
    private static Scanner scanner = new Scanner(System.in);
    public ArrayList<SellOrder> sellOrders;
    public boolean viewingOwn = false;

    public ViewSellOrders(int id) {
        //sellOrders = RestApiHandler.getInvestorSellOrders(id);
        viewingOwn = true;
    }

    // if an investorID is not provided we get all of the sell orders
/*     public ViewSellOrders() {
        sellOrders = RestApiHandler.getAllSellOrders();
    } */

    public ViewSellOrders(ArrayList<SellOrder> sellOrders) {
        this.sellOrders = sellOrders;
    }

    public void display(){
        while (true) {
            printSellOrders();
            System.out.println("0. Go back to Main Menu");
            if(viewingOwn){
                System.out.print("\nSelect sell order you want to delete: ");
            }
            else{
                System.out.print("\nSelect sell order you want to accept: ");
            }
            
            int choice = -1;
    
            try {
                String input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
    
            if (choice > sellOrders.size()) {
                System.out.println("If you not serious tell me");
                continue; 
            }
    
            handleChoice(choice);
        }
    }

        private void printSellOrders(){
            for (int index = 0; index < sellOrders.size(); index++) {
                SellOrder sellOrder = sellOrders.get(index);
                System.out.println(index+1 + ". " + sellOrder.getBeanName()+ " - " + sellOrder.getAvailableAmount() + " available @ R" + sellOrder.getSellingPrice()+ " each." );
            }
        }

        private void handleChoice(int choice){
            if(choice == 0){
                MainMenu.display();
            }
            SellOrder sellOrder = sellOrders.get(choice-1);

            // Ask for amount of beans to buy
            long amount = -1;
            while (amount == -1) {
                try {
                    System.out.print("Enter amount you want to buy: ");
                    amount = scanner.nextLong();
                    if (amount > sellOrder.getAvailableAmount()) {
                        System.out.println("Amount exceeds available inventory!");
                        amount = -1; // Reset amount to trigger retry
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
            RestApiHandler.acceptSellOrder(sellOrder,amount);
            MainMenu.display();
        }
    }

