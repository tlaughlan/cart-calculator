package com.applicant.redbubble.cart_calculator.integration.services;

import com.applicant.redbubble.cart_calculator.Constants;
import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.models.Cart;
import com.applicant.redbubble.cart_calculator.models.Product;
import com.applicant.redbubble.cart_calculator.services.PriceCalculator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PriceCalculatorIT {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ClassLoader classLoader = getClass().getClassLoader();
    private Cart testCart;
    private List<BasePrice> testPrices;
    private Product testProduct;
    private BasePrice testPrice;

    @Before
    public void setup() throws IOException {
        File testCartFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_CART).getFile());
        List<Product> testProducts = objectMapper.readValue(testCartFile, new TypeReference<List<Product>>(){});
        testCart = new Cart(testProducts);

        File testPricesFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_PRICES).getFile());
        testPrices = objectMapper.readValue(testPricesFile, new TypeReference<List<BasePrice>>(){});
    }

    @After
    public void cleanUp() {
        testProduct = null;
        testPrice = null;
    }

    @Test
    public void countCommonOptionsCorrectly() {
        testProduct = testCart.get(0);
        testPrice = testPrices.get(3);
        int expected = 2;
        int actual = PriceCalculator.countCommonOptions(testProduct, testPrice);
        Assert.assertEquals(3, testProduct.getOptions().size());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countZeroCommonOptionsOnNoMatch() {
        testProduct = testCart.get(0);
        testPrice = testPrices.get(9);
        int expected = 0;
        int actual = PriceCalculator.countCommonOptions(testProduct, testPrice);
        Assert.assertEquals(3, testProduct.getOptions().size());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void groupPricesByProductTypeCorrectly() {
        Map<String, List<BasePrice>> groupedBasePrices = PriceCalculator.groupPricesByProductType(testPrices);
        Assert.assertEquals(3, groupedBasePrices.size());
        Assert.assertTrue(groupedBasePrices.containsKey(Constants.PRODUCT_TYPE_HOODIE));
        Assert.assertTrue(groupedBasePrices.containsKey(Constants.PRODUCT_TYPE_STICKER));
        Assert.assertTrue(groupedBasePrices.containsKey(Constants.PRODUCT_TYPE_LEGGINGS));
    }

    @Test
    public void returnNullWhenGroupingOnNullBasePriceList() {
        testPrices = null;
        Assert.assertNull(PriceCalculator.groupPricesByProductType(testPrices));
    }

    @Test
    public void returnNullWhenGroupingOnEmptyBasePriceList() {
        testPrices.removeAll(testPrices);
        Assert.assertEquals(0, testPrices.size());
        Assert.assertNull(PriceCalculator.groupPricesByProductType(testPrices));
    }
}
