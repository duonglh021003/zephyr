package com.example.demo.validation;

public class Validates {

    public static String name(String name) {
        if (name.isEmpty()) {
            return "true";
        }
        return "false";
    }

}
