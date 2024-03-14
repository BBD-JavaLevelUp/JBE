package com.jbe.client;

import com.jbe.client.Models.Investor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class CurrentInvestor {
    private static int id;
    private static String name;
    private static String sAId;
    private static String email;
    public static boolean signedIn = false;
    public static boolean isAdmin = false;

    public static int getId() {
        return id;
    }
    public static String getName() {
        return name;
    }
    public String getsAId() {
        return sAId;
    }
    public String getEmail() {
        return email;
    }
    public static void signOut() {
        signedIn = false;
        isAdmin = false;
    }

    public static String hashID(String id) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(id.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static String selectAndReverse(String str) {
        StringBuilder selected = new StringBuilder();
        for (int i = 0; i < 16; i += 2) {
            if (i < str.length()) {
                selected.append(str.charAt(i));
            }
        }
        return selected.reverse().toString();
    }

    public static void signIn(String sId, String password) {
        Investor investor = RestApiHandler.getInvestor(sId);
        if (investor != null) {
            String hashed = hashID(sId);
            if (hashed != null) 
            {
                String result = selectAndReverse(hashed);
                if (password.equals(result)) 
                {
                    System.out.println(RestApiHandler.ANSI_GREEN+"Signed in."+RestApiHandler.ANSI_RESET);
                    signedIn = true;
                    if (investor.getInvestorId() == 1)
                    {
                        isAdmin = true;
                    }
                    id = investor.getInvestorId();
                    name = investor.getName();
                    sAId = investor.getSAId();
                    email = investor.getEmail();
                }
                else 
                {
                    signedIn = false;
                    System.out.println(RestApiHandler.ANSI_RED+"Incorrect password."+RestApiHandler.ANSI_RESET);
                }
            } 
            else 
            {
                System.out.println(RestApiHandler.ANSI_RED+"Error. Try again."+RestApiHandler.ANSI_RESET);
            }
        }
        else
        {
            System.out.println(RestApiHandler.ANSI_RED+"Failed to retrieve investor with given ID."+RestApiHandler.ANSI_RESET);
            signedIn = false;
            // Sign up or try again or exit to sign up/sign in menu (easiest)?
        }
    } 
    public static void signUp(String sName, String sSAid, String sEmail) {
        RestApiHandler.createInvestor(sName, sSAid, sEmail);
        String hashed = hashID(sSAid);
        if (hashed != null) 
        {
            String result = selectAndReverse(hashed);
            System.out.println("Your password is: " + result+"\nPlease keep it safe. You will not see it again.");
            signedIn = true;
            name = sName;
            sAId = sSAid;
            email = sEmail;
        }
        else 
        {
            signedIn = false;
            System.out.println(RestApiHandler.ANSI_RED+"Failed to create user."+RestApiHandler.ANSI_RESET);
        }
    }
}
