package com.applicant.redbubble.cart_calculator.models;

import com.applicant.redbubble.cart_calculator.Constants;
import com.applicant.redbubble.cart_calculator.services.FileConsumer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PricedCartItemTest {

    private final ClassLoader classLoader = getClass().getClassLoader();

    private List<CartItem> testCartItems;
    private File testCartFile;
    private List<BasePrice> testPrices;
    private File testBasePriceFile;

    private final Integer ITEM_1_EXPECTED_BASE_PRICE = 4212;
    private final Integer ITEM_1_EXPECTED_TOTAL_PRICE = 10108;
    private final Integer ITEM_2_EXPECTED_BASE_PRICE = 221;
    private final Integer ITEM_2_EXPECTED_TOTAL_PRICE = 243;

    @Before
    public void setup() {
        testCartFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_CART).getFile());
        testCartItems = FileConsumer.readCartFile(testCartFile);
        testBasePriceFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_PRICES).getFile());
        testPrices = FileConsumer.readBasePriceFile(testBasePriceFile);
    }

    @Test
    public void pricedCartItemsInstantiateCorrectly() {
        List<PricedCartItem> pricedCartItems = new ArrayList<>();
        Assert.assertEquals(2, testCartItems.size());
        for (CartItem currentCartItem : testCartItems) {
            pricedCartItems.add(new PricedCartItem(currentCartItem, testPrices));
        }

        Assert.assertEquals(2, pricedCartItems.size());
        Assert.assertEquals(ITEM_1_EXPECTED_BASE_PRICE, pricedCartItems.get(0).getBasePrice());
        Assert.assertEquals(ITEM_1_EXPECTED_TOTAL_PRICE, pricedCartItems.get(0).getTotalPrice());
        Assert.assertEquals(ITEM_2_EXPECTED_BASE_PRICE, pricedCartItems.get(1).getBasePrice());
        Assert.assertEquals(ITEM_2_EXPECTED_TOTAL_PRICE, pricedCartItems.get(1).getTotalPrice());
    }
}
