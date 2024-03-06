package com.jbe.client;

import com.jbe.client.Models.Investor;


public class Main {
    private static Investor currentInvestor; // Represents the currently logged-in investor
    private static RestApiHandler restApiHandler = new RestApiHandler(); // Handles REST API calls
    public static void main(String[] args) {
        System.out.println("Welcome to the Johannesburg Bean Exchange!");
        MainMenu.display(currentInvestor,restApiHandler);
    }

}
