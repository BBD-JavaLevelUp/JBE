package com.jbe.client.MenuOptions;
import java.util.ArrayList;
import java.util.Scanner;

import com.jbe.client.MainMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.SellOrder;

public class ViewSellOrders{
    private static Scanner scanner = new Scanner(System.in);
    public ArrayList<SellOrder> sellOrders = RestApiHandler.getAllSellOrders();


    public ViewSellOrders(){

    }

    public void display() {
        System.out.println("\nAll sell orders");
        subMenu();
    }

    public void subMenu(){
        while (true) {
            printSellOrders();
            System.out.println("0. Go back to Main Menu");
            System.out.print("\nSelect sell order you want to accept: ");
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
                String beanName = RestApiHandler.getBean(sellOrder.getBeanId()).getName();
                System.out.println(index+1 + ". " + beanName + " - " + sellOrder.getTotalAmount() + " - R" + sellOrder.getSellingPrice() );
            }
        }

        private void handleChoice(int choice){
            if(choice == 0){
                MainMenu.display();
            }
            SellOrder sellOrder = sellOrders.get(choice-1);
            System.out.println("Sell order accepted");
            MainMenu.display();
        }
    }

