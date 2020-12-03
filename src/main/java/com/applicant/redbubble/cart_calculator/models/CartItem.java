package com.applicant.redbubble.cart_calculator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class CartItem {

    @JsonProperty("product-type")
    private String productType;

    @JsonProperty("options")
    private Map<String, String> options;

    @JsonProperty("artist-markup")
    private int artistMarkup;

    @JsonProperty("quantity")
    private int quantity;

    public CartItem() {}

    public CartItem(String productType, Map<String, String> options, int artistMarkup, int quantity) {
        this.productType = productType;
        this.options = options;
        this.artistMarkup = artistMarkup;
        this.quantity = quantity;
    }

    public String description() {
        return "Cart item with product type " + getProductType() + " and options " + getOptions().toString();
    }
    
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
}
