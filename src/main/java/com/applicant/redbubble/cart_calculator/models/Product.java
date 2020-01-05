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
    private int basePrice;

    public String getProductType() {
        return productType;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
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
                    logger.info("Product of type " + this.getProductType() +
                            " with options " + this.getOptions().toString() +
                            " was assigned base price of " + basePrice.getBasePrice());
                    return;
                }
            }
        }
    }
}
