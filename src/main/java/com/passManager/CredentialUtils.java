package com.passManager;

import java.io.*;

public class CredentialUtils {

    private final String FILE_NAME = "password.txt";

    public void saveCredentials(String username, String password) {
        String hashed = Hashing.HashPassword(password);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(username + ":" + hashed);
            writer.newLine();
            System.out.println("Credentials saved");
        } catch (IOException ioException) {
            throw new RuntimeException("error saving credentials: " + ioException.getMessage());
        }
    }

    public boolean verifyCredential(String username, String pass) {
        String hashed = Hashing.HashPassword(pass);

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(hashed)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error retrieving the credentials from file");
        }
        return false;
    }
}
