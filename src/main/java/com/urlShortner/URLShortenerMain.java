package com.urlShortner;

import java.util.Scanner;

public class URLShortenerMain {

    public static void main(String[] args) {
        ShortenerUtil shortenUtil = new ShortenerUtil();
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Shorten URL");
        System.out.println("2. Retrieve URL");
        System.out.println("Enter choice");

        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1: {
                System.out.println("Enter the original url");
                String url = sc.nextLine();
                String shortFormURL = shortenUtil.shortUrl(url);
                System.out.println(shortFormURL);
                break;
            }
            case 2: {
                System.out.println("Enter the shorten URL");
                String originalURL = shortenUtil.getURL(sc.nextLine());
                System.out.println(originalURL);
                break;
            }
            default:
                System.out.println("Invalid choice");
                break;
        }

    }
}
