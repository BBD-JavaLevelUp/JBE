package com.jbe.client.MenuOptions;
import java.util.ArrayList;
import java.util.Scanner;

import com.jbe.client.MainMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.BuyOrder;

public class ViewBuyOrders{
    private static Scanner scanner = new Scanner(System.in);
    public ArrayList<BuyOrder> buyOrders;
    public boolean viewingOwn = false;

    public ViewBuyOrders(int id) {
        //buyOrders = RestApiHandler.getInvestorBuyOrders(id);
        viewingOwn = true;
    }

    // if an investorID is not provided we get all of the buy orders
    public ViewBuyOrders() {
        buyOrders = RestApiHandler.getAllBuyOrders();
    }

    public void display() {
        System.out.println("\nAll buy orders");
        subMenu();
    }

    public void subMenu(){
        while (true) {
            printBuyOrders();
            System.out.println("0. Go back to Main Menu");
            if(viewingOwn){
                System.out.print("\nSelect buy order you want to delete: ");
            }
            else{
                System.out.print("\nSelect buy order you want to accept: ");
            }
            
            int choice = -1;
    
            try {
                String input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
    
            if (choice > buyOrders.size()) {
                System.out.println("If you not serious tell me");
                continue; 
            }
    
            handleChoice(choice);
        }
    }

        private void printBuyOrders(){
            for (int index = 0; index < buyOrders.size(); index++) {
                BuyOrder buyOrder = buyOrders.get(index);
                String beanName = RestApiHandler.getBean(buyOrder.getBeanId()).getName();
                System.out.println(index+1 + ". " + beanName + " - " + buyOrder.getTotalAmount() + " - R" + buyOrder.getBuyingPrice() );
            }
        }

        private void handleChoice(int choice){
            if(choice == 0){
                MainMenu.display();
            }
            BuyOrder buyOrder = buyOrders.get(choice-1);
            if(viewingOwn){
                RestApiHandler.deleteBuyOrder(buyOrder.getBuyOrderId());
            }
            else{
                RestApiHandler.acceptBuyOrder(buyOrder.getBuyOrderId());
            }
            MainMenu.display();
        }
    }

