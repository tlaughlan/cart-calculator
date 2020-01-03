package com.applicant.redbubble.cart_calculator;

import com.applicant.redbubble.cart_calculator.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App {

    static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please specify two arguments: a cart json file, and a base prices json file.");
            return;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File cartFile = new File(args[0]);
            List<Product> cart = objectMapper.readValue(cartFile, new TypeReference<List<Product>>(){});
        } catch (IOException ioe) {
            logger.error("Trouble reading file >> " + ioe.getMessage());
            return;
        }
    }
}
