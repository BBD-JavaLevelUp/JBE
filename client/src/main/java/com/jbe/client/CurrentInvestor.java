package com.jbe.client;

public class CurrentInvestor {
    private static int id;
    private static String name;
    public static boolean signedIn = false;
    public static int getId() {
        return id;
    }
    public static String getName() {
        return name;
    }
    public static void signOut() {
        signedIn = false;
    }
    public static void signIn() {
        signedIn = true;
        id = 1;
    }
    public static void signUp() {
        System.out.println("signing up");
    }
}
