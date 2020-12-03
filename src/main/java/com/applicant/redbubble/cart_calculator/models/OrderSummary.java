package com.applicant.redbubble.cart_calculator.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderSummary {

    private Integer orderTotal;

    private List<PricedCartItem> pricedCartItems;

    public OrderSummary(List<CartItem> cartItems, Map<String, List<BasePrice>> groupedBasePrices) {
        this.pricedCartItems = initialisePricedCartItems(cartItems, groupedBasePrices);
        this.orderTotal = calculateOrderTotal(getPricedCartItems());
    }

    /**
     * This method loops through all the of cart items which have been read in from the input file. These are made into
     * PricedCartItem objects, which will be assigned a base price and total price. This is determined by the base
     * prices, which have been grouped by productType in order to reduce time complexity.
     * @param cartItems
     * @param groupedBasePrices
     * @return
     */
    private List<PricedCartItem> initialisePricedCartItems
            (List<CartItem> cartItems, Map<String, List<BasePrice>> groupedBasePrices) {
        List<PricedCartItem> pricedCartItems = new ArrayList<>();
        for (CartItem currentCartItem : cartItems) {
            List<BasePrice> pricingGroup = groupedBasePrices.get(currentCartItem.getProductType());
            pricedCartItems.add(new PricedCartItem(currentCartItem, pricingGroup));
        }
        return pricedCartItems;
    }

    private Integer calculateOrderTotal(List<PricedCartItem> pricedCartItems) {
        Integer totalCartCost = 0;
        for (PricedCartItem currentPricedCartItem : pricedCartItems) {
            totalCartCost += currentPricedCartItem.getTotalPrice();
        }
        return totalCartCost;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public List<PricedCartItem> getPricedCartItems() {
        return pricedCartItems;
    }
}
