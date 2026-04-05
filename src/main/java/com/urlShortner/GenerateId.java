package com.urlShortner;

public class GenerateId {

    private GenerateId() {
    }

    public static String generateId(String url) {
        return Integer.toHexString(url.hashCode());
    }

}
