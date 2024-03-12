package com.jbe.client;

public class CurrentInvestor {
    private static int id;
    private static String name;
    public static boolean signedIn = false;
    public static boolean isAdmin = false;
    public static int getId() {
        return id;
    }
    public static String getName() {
        return name;
    }
    public static void signOut() {
        signedIn = false;
        isAdmin = false;
    }
    public static void signIn() {
        signedIn = true;
        id = 1;
    }
    public static void signUp() {
        // for now signing up gets you logged in as admin, change this after auth is completed
        signedIn = true;
        isAdmin = true;
    }
}
