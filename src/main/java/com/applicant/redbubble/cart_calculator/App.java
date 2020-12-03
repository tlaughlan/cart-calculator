package com.applicant.redbubble.cart_calculator;

import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.models.CartItem;
import com.applicant.redbubble.cart_calculator.models.PricedCartItem;
import com.applicant.redbubble.cart_calculator.services.FileConsumer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {

    static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
        if (args.length != 2) {
            logger.error("Please specify two arguments: a cart json file, and a base prices json file.");
            return;
        }

//        Cart cart = new Cart(FileConsumer.readCartFile(new File(args[0])));
        List<BasePrice> prices = FileConsumer.readBasePriceFile(new File (args[1]));
//
//        cart.populateProductBasePrices(prices);
//        cart.populateProductTotalCosts();
//        cart.setTotalPrice(cart.calculateTotalCartPrice());

        List<CartItem> cartItems = FileConsumer.readCartFile(new File(args[0]));

        List<PricedCartItem> pricedCartItems = new ArrayList<>();
        for (CartItem currentCartItem : cartItems) {
            pricedCartItems.add(new PricedCartItem(currentCartItem, prices));
        }

        Integer totalCartCost = 0;

        for (PricedCartItem currentPricedCartItem : pricedCartItems) {
            totalCartCost += currentPricedCartItem.getTotalPrice();
        }

        logger.info("Your total cart price is " + totalCartCost + "\n");
        System.out.println(totalCartCost);
    }
}
