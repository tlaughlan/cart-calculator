package com.applicant.redbubble.cart_calculator.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Product {

    static final Logger logger = LogManager.getLogger(Product.class.getName());

    @JsonProperty("product-type")
    private String productType;

    @JsonProperty("options")
    private Map<String, String> options;

    @JsonProperty("artist-markup")
    private int artistMarkup;

    @JsonProperty("quantity")
    private int quantity;

    @JsonIgnore
    private Integer basePrice;

    @JsonIgnore
    private Integer totalCost;

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

    public Integer getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Integer basePrice) {
        this.basePrice = basePrice;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }

    public Integer findBasePrice(List<BasePrice> basePriceGroup) {
        for (BasePrice currentBasePrice : basePriceGroup) {
            if (currentBasePrice.containsOptions(this.getOptions())) {
                return currentBasePrice.getBasePrice();
            }
        }
        logger.error(this.productDescription() + " could not find BASE PRICE.");
        return null;
    }

    /**
     * The maths of this method is defined as: (base_price + round(base_price * artist_markup)) * quantity
     * where artist_markup is a int representing a percentage.
     */
    public Integer calculateTotalCost() {
        return (basePrice + Math.round(basePrice * (new Float(artistMarkup)/100))) * quantity;
    }

    public String productDescription() {
        return "Product of type " + this.getProductType() + " with options " + this.getOptions().toString();
    }
}
