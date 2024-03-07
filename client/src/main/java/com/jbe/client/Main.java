package com.jbe.client;

import com.jbe.client.Models.Investor;


public class Main {
    private static Investor currentInvestor; // Represents the currently logged-in investor
    public static void main(String[] args) {
        System.out.println("Welcome to the Johannesburg Bean Exchange!");
        MainMenu.display(currentInvestor);
    }

}
