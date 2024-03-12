package com.jbe.client.MenuOptions;
import java.math.BigDecimal;
import java.util.Scanner;

import com.jbe.client.AdminMenu;
import com.jbe.client.RestApiHandler;

public class CreateBean{
    private static Scanner scanner = new Scanner(System.in);


    public void display() {
        System.out.println("\nCreating a new bean");
        subMenu();
    }

    public void subMenu(){

        // Ask for bean name
        String name;
        while (true) {
            System.out.print("Enter name: ");
            name = scanner.nextLine();
            if (!name.trim().isEmpty()) {
                break;
            } else {
                System.out.println("Name cannot be blank. Please enter a valid name.");
            }
        }

        // Ask for amount of beans
        long amount;
        while (true) {
            try {
                System.out.print("Enter amount: ");
                String input = scanner.nextLine();
                amount = Long.parseLong(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Ask for price
        BigDecimal price;
        while (true) {
            System.out.print("Enter default price: ");
            String priceInput = scanner.nextLine();
            try {
                price = new BigDecimal(priceInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }
            RestApiHandler.createBean(name, amount, price);
            AdminMenu.display();
    }

}

