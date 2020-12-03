package com.applicant.redbubble.cart_calculator.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PricedCartItem extends CartItem{

    static final Logger logger = LogManager.getLogger(PricedCartItem.class.getName());

    private Integer basePrice;

    private Integer totalPrice;

    public Integer getBasePrice() {
        return basePrice;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public PricedCartItem(CartItem cartItem, List<BasePrice> basePriceGroup) {
        super(
            cartItem.getProductType(),
            cartItem.getOptions(),
            cartItem.getArtistMarkup(),
            cartItem.getQuantity()
        );

        this.basePrice = findBasePrice(basePriceGroup);
        logger.info(description() + " has been assigned BASE PRICE of " + getBasePrice());

        this.totalPrice = calculateTotalPrice();
        logger.info(description() + " has been assigned TOTAL PRICE of " + getTotalPrice());
    }

    private Integer findBasePrice(List<BasePrice> basePriceGroup) {
        for (BasePrice currentBasePrice : basePriceGroup) {
            if (currentBasePrice.containsOptions(getOptions())) {
                return currentBasePrice.getBasePrice();
            }
        }
        logger.error(description() + " could not find BASE PRICE.");
        return null;
    }

    private Integer calculateTotalPrice() {
        if (getBasePrice() != null) {
            return (getBasePrice()
                    + Math.round(getBasePrice() * (new Float(super.getArtistMarkup())/100))) * super.getQuantity();
        } else {
            logger.error(description() + " had BASE PRICE of NULL at time of TOTAL PRICE calculation.");
            return null;
        }
    }
}
