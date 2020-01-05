package com.applicant.redbubble.cart_calculator.models;

import com.applicant.redbubble.cart_calculator.services.PriceCalculator;
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

    public void findBasePrice(List<BasePrice> basePriceGroup) {
        for (BasePrice basePrice : basePriceGroup) {
            int optionMatchCounter = 0;
            int totalCommonOptions = PriceCalculator.countCommonOptions(this, basePrice);
            if (totalCommonOptions > 0) {
                Map<String, List<String>> basePriceOptions = basePrice.getOptions();
                for (Map.Entry optionsPair : this.getOptions().entrySet()) {
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
                    this.setBasePrice(basePrice.getBasePrice());
                    logger.info(this.productDescription() +
                            " was assigned BASE PRICE of " + basePrice.getBasePrice());
                    return;
                }
            }
        }
    }

    /**
     * The maths of this method is defined as: (base_price + round(base_price * artist_markup)) * quantity
     * where artist_markup is a int representing a percentage.
     */
    public void calculateTotalCost() {
        if (this.getBasePrice() != null) {
            int basePriceInt = this.getBasePrice().intValue();
            if (basePriceInt == 0) {
                logger.warn(this.productDescription() +
                        " has base price of 0 at time of total cost calculation. Is this intentional?");
            }
            this.setTotalCost((basePriceInt +
                    Math.round(basePriceInt * (new Float(this.getArtistMarkup())/100))) * this.getQuantity());
            logger.info(this.productDescription() + " was assigned TOTAL COST of " + this.getTotalCost());
        } else {
            logger.error(this.productDescription() +
                    " is null at time of total cost calculation.");
        }
    }

    public String productDescription() {
        return "Product of type " + this.getProductType() + " with options " + this.getOptions().toString();
    }
}
