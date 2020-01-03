package com.applicant.redbubble.cart_calculator.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    @JsonProperty("product-type")
    private String productType;

    @JsonProperty("options")
    private Object options;

    @JsonProperty("artist-markup")
    private int artistMarkup;

    @JsonProperty("quantity")
    private int quantity;
}
