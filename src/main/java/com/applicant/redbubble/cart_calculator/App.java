package com.applicant.redbubble.cart_calculator;

import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.models.Cart;
import com.applicant.redbubble.cart_calculator.services.FileConsumer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.util.List;

public class App {

    static final Logger logger = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) {
        if (args.length != 2) {
            logger.error("Please specify two arguments: a cart json file, and a base prices json file.");
            return;
        }

        Cart cart = new Cart(FileConsumer.readCartFile(new File(args[0])));
        List<BasePrice> prices = FileConsumer.readBasePriceFile(new File (args[1]));

        cart.populateProductBasePrices(prices);
        cart.populateProductTotalCosts();
        cart.setTotalPrice(cart.calculateTotalCartPrice());

        logger.info("Your total cart price is " + cart.getTotalPrice() + "\n");
        System.out.println(cart.getTotalPrice());
    }
}
