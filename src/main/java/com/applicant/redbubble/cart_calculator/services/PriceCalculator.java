package com.applicant.redbubble.cart_calculator.services;

import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.models.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PriceCalculator {

    static final Logger logger = LogManager.getLogger(PriceCalculator.class.getName());

    /**
     * Counts how many shared options a product and base price have. Used when iterating through all of the
     * appropriate base prices for a product. Once the base price options have found as many value matches as there are
     * common options then we know that base price is a positive match for the product.
     * @param   product
     * @param   basePrice
     * @return  commonOptionsCount
     */
    public static int countCommonOptions(Product product, BasePrice basePrice) {
        int commonOptionsCount;
        Map<String, String> tempMap = new HashMap<>();
        tempMap.putAll(product.getOptions());
        Collection<String> productOptions = tempMap.keySet();
        Collection<String> basePriceOptions = basePrice.getOptions().keySet();
        productOptions.retainAll(basePriceOptions);
        commonOptionsCount = productOptions.size();
        return commonOptionsCount;
    }

    public static int calculateTotalCartPrice(List<Product> cart) {
        int totalCartPrice = 0;
        for (Product product : cart) {
            if (product.getTotalCost() != null) {
                totalCartPrice += product.getTotalCost();
            } else {
                logger.error(product.productDescription() +
                        " has total cost null at time of calculating total cart price.");
            }
        }
        return totalCartPrice;
    }

    public static Map<String, List<BasePrice>> groupPricesByProductType(List<BasePrice> basePrices) {
        if (basePrices != null && basePrices.size() != 0) {
            return basePrices.stream().collect(Collectors.groupingBy(BasePrice::getProductType));
        } else {
            return null;
        }
    }
}
