package com.jbe.client.MenuOptions;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.jbe.client.CurrentInvestor;
import com.jbe.client.MainMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.Bean;
import com.jbe.client.Models.BuyOrder;

public class CreateBuyOrder{
    private static Scanner scanner = new Scanner(System.in);
    private ArrayList<Bean> beans;

    public CreateBuyOrder()
    {
        this.beans = RestApiHandler.getBeansList();
    }
    

    public void display() {
        System.out.println("\nAll the beans in the world");
        subMenu();
    }

    public void subMenu(){
        while (true) {
            // print all beans!
            for (int index = 0; index < beans.size(); index++) {
                Bean bean = beans.get(index);
                System.out.println(index+1 + ". "+bean.getName());
            }

            System.out.println("0. Go back to Main Menu");
            System.out.print("\nSelect bean you want to create buy order on: ");
            
            int choice = -1;
    
            try {
                String input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
    
            if (choice > beans.size()) {
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
            Bean bean = beans.get(choice-1);

            // Ask for buying price
            BigDecimal buyingPrice = null;
            while (buyingPrice == null) {
                try {
                    System.out.print("Enter buying price(for each bean) : ");
                    buyingPrice = scanner.nextBigDecimal();
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
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid integer.");
                    scanner.nextLine(); // Consume the invalid input
                }
            }
            BuyOrder newBuyOrder = new BuyOrder(CurrentInvestor.getId(), 
                                                    bean.getBeanId(), 
                                                    buyingPrice, 
                                                    amount, 
                                                    amount, 
                                                    OffsetDateTime.now().toString(), 
                                                    true,
                                                    bean.getName()
                                                    );
            RestApiHandler.CreateBuyOrder(newBuyOrder);
            MainMenu.display();
        }
    }

