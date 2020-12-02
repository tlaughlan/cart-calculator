package com.applicant.redbubble.cart_calculator.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Cart {

    static final Logger logger = LogManager.getLogger(Cart.class.getName());

    private List<Product> productList;

    private Integer totalPrice;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Cart (List<Product> productList) {
        setProductList(productList);
        setTotalPrice(null);
    }

    public Product get(int index) {
        return this.getProductList().get(index);
    }

    public static Integer calculateTotalCartPrice(Cart cart) {
        Integer totalCartPrice = 0;
        for (Product product : cart.getProductList()) {
            if (product.getTotalCost() != null) {
                totalCartPrice += product.getTotalCost();
            } else {
                logger.error(product.productDescription() +
                        " has total cost null at time of calculating total cart price.");
            }
        }
        return totalCartPrice;
    }
}
