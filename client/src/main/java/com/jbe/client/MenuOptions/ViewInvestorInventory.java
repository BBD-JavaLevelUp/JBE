package com.jbe.client.MenuOptions;
import java.util.Scanner;

import com.jbe.client.AdminMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.Investor;

public class ViewInvestorInventory{
    private static Scanner scanner = new Scanner(System.in);
    private ViewInvestors viewInvestors;

    public ViewInvestorInventory() {;
        viewInvestors = new ViewInvestors();
    }

    public void display() {
        System.out.println("\nAll investors");
        subMenu();
    }

    public void subMenu(){
        while (true) {
            viewInvestors.printInvestors();
            System.out.println("0. Go back to Main Menu");
            System.out.print("\nSelect an investor to see their inventory: ");
            
            int choice = -1;
            try {
                String input = scanner.nextLine().trim();
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
    
            if (choice > viewInvestors.investors.size()) {
                System.out.println("If you not serious tell me");
                continue; 
            }
    
            handleChoice(choice);
        }
    }

    private void handleChoice(int choice){
        if(choice == 0){
            AdminMenu.display();
        }
        Investor investor = viewInvestors.investors.get(choice-1);
        RestApiHandler.getInventory(investor.getInvestorId(),false);
    }
    }

