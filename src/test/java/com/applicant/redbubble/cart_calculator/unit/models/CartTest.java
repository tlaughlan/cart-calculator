package com.applicant.redbubble.cart_calculator.unit.models;

import com.applicant.redbubble.cart_calculator.Constants;
import com.applicant.redbubble.cart_calculator.models.Cart;
import com.applicant.redbubble.cart_calculator.models.Product;
import com.applicant.redbubble.cart_calculator.services.FileConsumer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class CartTest {

    private final ClassLoader classLoader = getClass().getClassLoader();

    private File testCartFile;
    private List<Product> dummyProductslist;

    private final Integer DUMMY_PRODUCT_0_TOTAL_COST = 2500;
    private final Integer DUMMY_PRODUCT_1_TOTAL_COST = 3500;
    private final Integer DUMMY_CART_EXPECTED_TOTAL_PRICE = DUMMY_PRODUCT_0_TOTAL_COST + DUMMY_PRODUCT_1_TOTAL_COST;

    @Before
    public void setup() {
        testCartFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_CART).getFile());
        dummyProductslist = FileConsumer.readCartFile(testCartFile);
        dummyProductslist.get(0).setTotalCost(2500);
        dummyProductslist.get(1).setTotalCost(3500);
    }

    @Test
    public void testCalculateTotalCartPriceSuccessfully() {
        Cart dummyCart = new Cart(dummyProductslist);
        Assert.assertEquals(DUMMY_CART_EXPECTED_TOTAL_PRICE, Cart.calculateTotalCartPrice(dummyCart));
    }

    @Test
    public void testGracefulFailureOfNullProductTotalPrice() {
        dummyProductslist.get(1).setTotalCost(null);
        Cart dummyCart = new Cart(dummyProductslist);
        Assert.assertEquals(DUMMY_PRODUCT_0_TOTAL_COST, Cart.calculateTotalCartPrice(dummyCart));
    }
}
