package com.jbe.client.MenuOptions;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.jbe.client.MainMenu;
import com.jbe.client.Models.Investor;
import com.jbe.client.Models.SellOrder;

public class ViewSellOrders{
    private static Scanner scanner = new Scanner(System.in);
    public Investor currentInvestor;
    public ArrayList<SellOrder> sellOrders = new ArrayList<>();


    public ViewSellOrders(Investor currentInvestor){
        this.currentInvestor = currentInvestor;
        // Creating SellOrder instances
        SellOrder sellOrder1 = new SellOrder(1, 101, 201, 10.50, 50, 100, new Date(), true);
        SellOrder sellOrder2 = new SellOrder(2, 102, 202, 12.75, 30, 50, new Date(), true);
        SellOrder sellOrder3 = new SellOrder(3, 103, 203, 9.25, 40, 80, new Date(), true);

        // Adding SellOrder instances to the ArrayList
        sellOrders.add(sellOrder1);
        sellOrders.add(sellOrder2);
        sellOrders.add(sellOrder3);

    }

    public void display() {
        System.out.println("\nAll sell orders");
        subMenu();
    }

    public void subMenu(){
        while (true) {
            System.out.println("\n0. Go back to Main Menu");
            printSellOrders();
            System.out.print("Select sell order you want to accept: ");
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
                System.out.println(index+1 + ". " + sellOrder.getTotalAmount() + " R" + sellOrder.getSellingPrice() );
            }
        }

        private void handleChoice(int choice){
            if(choice == 0){
                MainMenu.display(currentInvestor);
            }
            SellOrder sellOrder = sellOrders.get(choice-1);
            System.out.println("Sell order accepted");
            MainMenu.display(currentInvestor);
        }
    }

