package com.jbe.client.MenuOptions;
import java.util.ArrayList;
import java.util.Scanner;

import com.jbe.client.AdminMenu;
import com.jbe.client.RestApiHandler;
import com.jbe.client.Models.Investor;

public class ViewInvestors {
    private static Scanner scanner = new Scanner(System.in);
    public ArrayList<Investor>  investors;

    public ViewInvestors() {
        investors = RestApiHandler.getInvestors();
    }

    public void display() {
        System.out.println("\nAll investors");
        subMenu();
    }

    public void subMenu(){
        while (true) {
            printInvestors();
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
                    AdminMenu.display();
                    break;
                default:
                    System.out.println("Either you go back or just keep viewing the investors");
            }
        }
        }
    public void printInvestors(){
        for (int index = 0; index < investors.size(); index++) {
            Investor investor = investors.get(index);
            System.out.println(index+1 + ". " + investor.getName() + " - " + investor.getEmail()+ " - " + investor.getSAId() ) ;
        }
    }
    }

