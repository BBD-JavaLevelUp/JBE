package com.jbe.client;

import java.util.Scanner;

import com.jbe.client.MenuOptions.CreateBuyOrder;
import com.jbe.client.MenuOptions.CreateSellOrder;

public class MainMenu
{
    private static Scanner scanner = new Scanner(System.in);

    public static void display()
    {
        while (true) {
            System.out.println("\nMain Menu:");
            if (!CurrentInvestor.signedIn) {
                System.out.println("1. Sign Up");
                System.out.println("2. Sign In");
                System.out.println("3. Exit");
            } else {
                System.out.println("1. View Beans");
                System.out.println("2. View My Inventory");
                System.out.println("3. View My Transactions");
                System.out.println("4. View My Sell Orders");
                System.out.println("5. View My Buy Orders"); 
                System.out.println("6. View Available Sell Orders"); 
                System.out.println("7. View Available Buy Orders"); 
                System.out.println("8. Create a Sell Order");
                System.out.println("9. Create a Buy Order");
                System.out.println("10. Logout");
            }
            System.out.print("\nEnter your choice: ");
            int choice = -1; // Default to an invalid choice

            try {
                String input = scanner.nextLine().trim(); // Read the whole line of input
                choice = Integer.parseInt(input); // Try to convert the input to an integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue; // Skip to the next iteration of the loop immediately
            }

            if (!CurrentInvestor.signedIn && (choice < 1 || choice > 3)) {
                System.out.println("Please sign in or sign up to access this feature.");
                continue; // Skip the rest of the loop and show the menu again
            }

            switch (choice)
            {
                case 1:
                    if (!CurrentInvestor.signedIn)
                    {
                        String name = null;
                        String idNum = null;
                        String email = null;
                        while (true) {
                            System.out.print("Enter your full name: ");
                            name = scanner.nextLine();
                            if(!name.isEmpty()){
                                break;
                            }else{
                                System.out.println(RestApiHandler.ANSI_RED+"Name cannot be empty."+RestApiHandler.ANSI_RESET);
                            }
                        }
                        while (true) {
                            System.out.print("Enter your ID Number: ");
                            idNum = scanner.nextLine();
                            if(!idNum.isEmpty()){
                                if (idNum.length() == 13) {
                                    break;
                                } else {
                                    System.out.println(RestApiHandler.ANSI_RED+"ID number should be 13 digits."+RestApiHandler.ANSI_RESET);
                                }
                            }else{
                                System.out.println(RestApiHandler.ANSI_RED+"ID number cannot be empty."+RestApiHandler.ANSI_RESET);
                            }
                        }
                        while (true) {
                            System.out.print("Enter your email: ");
                            email = scanner.nextLine();
                            if(!email.isEmpty()){
                                break;
                            }else{
                                System.out.println(RestApiHandler.ANSI_RED+"Email cannot be empty."+RestApiHandler.ANSI_RESET);
                            }
                        }
                        if (name != null && idNum != null && email != null) {
                            CurrentInvestor.signUp(name, idNum, email);
                        }
                        if(CurrentInvestor.isAdmin)
                        {
                            AdminMenu.display();
                        }
                    }
                    else
                    {
                        RestApiHandler.getAllBeans();
                    }
                    break;
                case 2:
                    if (!CurrentInvestor.signedIn) 
                    {
                        RestApiHandler.login();
                    }
                    else
                    {
                        RestApiHandler.getInventory(CurrentInvestor.getId());
                    }
                    break;
                case 3:
                    if (!CurrentInvestor.signedIn)
                    {
                        System.out.println(RestApiHandler.ANSI_MAGENTA+ "Exiting..." + RestApiHandler.ANSI_RESET);
                        System.exit(0);
                    }
                    else
                    {
                        RestApiHandler.getTransactions(CurrentInvestor.getId());
                        break;
                    }
                    break;
                case 4:
                    RestApiHandler.getInvestorSellOrders(CurrentInvestor.getId());
                    break;
                case 5:
                    RestApiHandler.getInvestorBuyOrders(CurrentInvestor.getId());
                    break;    
                case 6:
                    RestApiHandler.getSellOrders();
                    break;
                case 7:
                    RestApiHandler.getBuyOrders();
                    break;
                case 8:
                    CreateSellOrder cso = new CreateSellOrder();
                    cso.display();
                    break;
                case 9:
                    CreateBuyOrder cbo = new CreateBuyOrder();
                    cbo.display();
                    break;                  
                case 10:
                    CurrentInvestor.signOut();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
}