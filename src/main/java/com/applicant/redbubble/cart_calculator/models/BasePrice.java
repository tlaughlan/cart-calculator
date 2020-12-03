package com.applicant.redbubble.cart_calculator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class BasePrice {
    @JsonProperty("product-type")
    private String productType;

    @JsonProperty("options")
    private Map<String, List<String>> options;

    @JsonProperty("base-price")
    private int basePrice;

    /**
     * This method will loop through all of the options lists for this base price. If each options list contains a match
     * with the product options then true is returned. The method fails early as soon as an option finds no matches.
     * @param productOptions
     * @return
     */
    public boolean containsOptions(Map<String, String> productOptions) {
        for (Map.Entry<String, List<String>> bpOption : this.getOptions().entrySet()) {
            String productOption = productOptions.get(bpOption.getKey());
            if (!bpOption.getValue().contains(productOption)) {
                return false;
            }
        }
        return true;
    }

    public String getProductType() {
        return productType;
    }

    public Map<String, List<String>> getOptions() {
        return options;
    }

    public int getBasePrice() {
        return basePrice;
    }
}
