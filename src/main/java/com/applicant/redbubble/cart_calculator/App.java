package com.applicant.redbubble.cart_calculator;

import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.models.Product;
import com.applicant.redbubble.cart_calculator.services.PriceCalculator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

    static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
        if (args.length != 2) {
            logger.error("Please specify two arguments: a cart json file, and a base prices json file.");
            return;
        }

        List<Product> cart;
        List<BasePrice> prices;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            cart = objectMapper.readValue(new File(args[0]), new TypeReference<List<Product>>(){});
            prices = objectMapper.readValue(new File(args[1]), new TypeReference<List<BasePrice>>(){});
        } catch (IOException ioe) {
            logger.error("Trouble reading file >> " + ioe.getMessage());
            return;
        }

        Map<String, List<BasePrice>> groupedBasePrices =
                prices.stream().collect(Collectors.groupingBy(BasePrice::getProductType));
        for (Product product : cart) {
            findBasePrice(product, groupedBasePrices.get(product.getProductType()));
        }
    }

    private static void findBasePrice(Product product, List<BasePrice> basePriceGroup) {
        for (BasePrice basePrice : basePriceGroup) {
            int optionMatchCounter = 0;
            int totalCommonOptions = PriceCalculator.countCommonOptions(product, basePrice);
            if (totalCommonOptions > 0) {
                Map<String, List<String>> basePriceOptions = basePrice.getOptions();
                for (Map.Entry optionsPair : product.getOptions().entrySet()) {
                    if (optionMatchCounter < totalCommonOptions) {
                        if (basePriceOptions.containsKey(optionsPair.getKey())) {
                            if (basePriceOptions.get(optionsPair.getKey()).contains(optionsPair.getValue())) {
                                optionMatchCounter++;
                            } else {
                                break;
                            }
                        }
                    }
                }
                if (optionMatchCounter == totalCommonOptions) {
                    product.setBasePrice(basePrice.getBasePrice());
                    logger.info("Product of type " + product.getProductType() +
                            " with options " + product.getOptions().toString() +
                            " was assigned base price of " + basePrice.getBasePrice());
                    return;
                }
            }
        }
    }
}
