package com.passManager;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {

    private Hashing() {

    }

    public static String HashPassword(String password) {
        try {
            StringBuilder sb = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digestPassword = md.digest(password.getBytes());

            for (byte b : digestPassword) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new RuntimeException("Hashing algorithm not found");
        }


    }
}
