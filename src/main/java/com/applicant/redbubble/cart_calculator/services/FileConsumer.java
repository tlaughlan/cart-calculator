package com.applicant.redbubble.cart_calculator.services;

import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileConsumer {

    static final Logger logger = LogManager.getLogger(FileConsumer.class.getName());

    public static List<Product> readCartFile(File cartFile) {
        List<Product> productList = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            productList = objectMapper.readValue(cartFile, new TypeReference<List<Product>>(){});
        } catch (IOException ioe) {
            logger.error("Trouble reading file >> " + ioe.getMessage());
        }
        return productList;
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
