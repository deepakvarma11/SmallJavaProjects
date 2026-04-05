package com.passManager;

import java.util.Scanner;

public class PasswordManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CredentialUtils credentialUtils = new CredentialUtils();

        System.out.println("Password manager");
        System.out.println("1. Save credentials");
        System.out.println("2. Verify credentials");

        System.out.println("Enter your choice");

        int choice = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter username");
        String username = sc.nextLine();
        System.out.println("Enter password");
        String pass = sc.nextLine();

        if (choice == 1) {
            credentialUtils.saveCredentials(username, pass);
        } else if (choice == 2) {
            if (credentialUtils.verifyCredential(username, pass))
                System.out.println("Credential valid");
            else
                System.out.println("Credentials invalid");
        } else {
            System.out.println("Incorrect choice");
        }

    }
}