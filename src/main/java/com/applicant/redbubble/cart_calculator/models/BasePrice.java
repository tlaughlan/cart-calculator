package com.applicant.redbubble.cart_calculator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BasePrice {
    @JsonProperty("product-type")
    private String productType;

    @JsonProperty("options")
    private Map<String, List<String>> options;

    @JsonProperty("base-price")
    private int basePrice;

    public String getProductType() {
        return productType;
    }

    public Map<String, List<String>> getOptions() {
        return options;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public static Map<String, List<BasePrice>> groupPricesByProductType(List<BasePrice> basePrices) {
        if (basePrices != null && basePrices.size() != 0) {
            return basePrices.stream().collect(Collectors.groupingBy(BasePrice::getProductType));
        } else {
            return null;
        }
    }
}
