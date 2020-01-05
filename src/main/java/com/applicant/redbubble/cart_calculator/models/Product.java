package com.applicant.redbubble.cart_calculator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Product {
    @JsonProperty("product-type")
    private String productType;

    @JsonProperty("options")
    private Map<String, String> options;

    @JsonProperty("artist-markup")
    private int artistMarkup;

    @JsonProperty("quantity")
    private int quantity;

    @JsonIgnore
    private int basePrice;

    public String getProductType() {
        return productType;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
}
