package com.applicant.redbubble.cart_calculator.models;

import com.applicant.redbubble.cart_calculator.App;
import com.applicant.redbubble.cart_calculator.Constants;
import com.applicant.redbubble.cart_calculator.services.FileConsumer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

public class OrderSummaryTest {

    private final ClassLoader classLoader = getClass().getClassLoader();

    private List<CartItem> testCartItems;
    private File testCartFile;
    private List<BasePrice> testPrices;
    private File testBasePriceFile;
    private Map<String, List<BasePrice>> groupedTestPrices;

    private final Integer EXPECTED_ORDER_TOTAL = 10351;

    @Before
    public void setup() {
        testCartFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_CART).getFile());
        testCartItems = FileConsumer.readCartFile(testCartFile);
        testBasePriceFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_PRICES).getFile());
        testPrices = FileConsumer.readBasePriceFile(testBasePriceFile);
        groupedTestPrices = App.groupPricesByProductType(testPrices);
    }

    @Test
    public void orderSummaryInstantiatesCorrectly() {
        OrderSummary orderSummary = new OrderSummary(testCartItems, groupedTestPrices);
        Assert.assertEquals(testCartItems.size(), orderSummary.getPricedCartItems().size());
        Assert.assertEquals(EXPECTED_ORDER_TOTAL, orderSummary.getOrderTotal());
    }
}
