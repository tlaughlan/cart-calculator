package com.applicant.redbubble.cart_calculator.services;

import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.models.CartItem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileConsumer {

    static final Logger logger = LogManager.getLogger(FileConsumer.class.getName());

    public static List<CartItem> readCartFile(File cartFile) {
        List<CartItem> cartItems = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            cartItems = objectMapper.readValue(cartFile, new TypeReference<List<CartItem>>(){});
        } catch (IOException ioe) {
            logger.error("Trouble reading file >> " + ioe.getMessage());
        }
        return cartItems;
    }

    public static List<BasePrice> readBasePriceFile(File basePriceFile) {
        List<BasePrice> basePrices = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            basePrices = objectMapper.readValue(basePriceFile, new TypeReference<List<BasePrice>>(){});
        } catch (IOException ioe) {
            logger.error("Trouble reading file >> " + ioe.getMessage());
        }
        return basePrices;
    }
}
