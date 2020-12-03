package com.applicant.redbubble.cart_calculator.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderSummary {

    private Integer orderTotal;

    private List<PricedCartItem> pricedCartItems;

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public List<PricedCartItem> getPricedCartItems() {
        return pricedCartItems;
    }

    public OrderSummary(List<CartItem> cartItems, Map<String, List<BasePrice>> groupedBasePrices) {
        this.pricedCartItems = initialisePricedCartItems(cartItems, groupedBasePrices);
        this.orderTotal = calculateOrderTotal(getPricedCartItems());
    }

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
}
