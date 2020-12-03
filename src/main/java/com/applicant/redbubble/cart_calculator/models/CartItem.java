package com.applicant.redbubble.cart_calculator.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

public class CartItem {

    static final Logger logger = LogManager.getLogger(CartItem.class.getName());

    @JsonProperty("product-type")
    private String productType;

    @JsonProperty("options")
    private Map<String, String> options;

    @JsonProperty("artist-markup")
    private int artistMarkup;

    @JsonProperty("quantity")
    private int quantity;

    public String getProductType() {
        return productType;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public int getArtistMarkup() {
        return artistMarkup;
    }

    public int getQuantity() {
        return quantity;
    }

    public String description() {
        return "Cart item with product type " + getProductType() + " and options " + getOptions().toString();
    }
}
