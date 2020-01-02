package com.applicant.redbubble.cart_calculator;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please specify two arguments: a cart json file, and a base prices json file.");
            return;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File cartFile = new File(args[0]);
            Object cartIn = objectMapper.readValue(cartFile, Object.class);
            System.out.println(cartIn);
        } catch (IOException ioe) {
            System.out.print("Trouble reading cart file >>\n" + ioe.getMessage());
            return;
        }
    }

}
