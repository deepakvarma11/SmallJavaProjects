package com.urlShortner;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShortenerUtil {
    private final String FILE_NAME = "urls.txt";
    private final String BASE_URL = "https://short.ly/";

    private Map<String, String> urlMap = new HashMap<>();

    public ShortenerUtil() {
        loadDateFromFile();
    }

    public String shortUrl(String URL) {
        String id = urlMap.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue(), URL))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElseGet(() -> {
                    String generateId = GenerateId.generateId(URL);
                    urlMap.put(generateId, URL);
                    saveToFile();
                    return generateId;
                });
        return BASE_URL + id;
    }

    public String getURL(String shortenURL) {
        String id = shortenURL.replace(BASE_URL, "");
        return urlMap.get(id);
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            for (Map.Entry<String, String> entry : urlMap.entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
            }
            writer.newLine();
            System.out.println("Added new URL");

        } catch (IOException e) {
            throw new RuntimeException("File not created: " + e.getMessage());
        }
    }

    private void loadDateFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    urlMap.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("File Not found for url shortner");
        }
    }
}
