package com.codecool.krk.util;

public class UriParser {

    public static long extractIdFromUri(String uri) {
        String [] splitUri = uri.split("/");

        long id;
        try {
            id = Long.parseLong(splitUri[2]);
        } catch (NumberFormatException e) {
            id = -1;
        }
        return id;
    }

}
