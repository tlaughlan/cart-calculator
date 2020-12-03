package com.applicant.redbubble.cart_calculator.unit.models;

import com.applicant.redbubble.cart_calculator.Constants;
import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductTest {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ClassLoader classLoader = getClass().getClassLoader();
    private List<Product> testCart;
    private Product testHoodie;

    private final Map<String, String> TEST_HOODIE_OPTIONS = new HashMap<String, String>() {{
        put("size", "large");
        put("colour", "dark");
        put("print-location", "front");
    }};
    private final int TEST_HOODIE_ARTIST_MARKUP = 20;
    private final int TEST_HODDIE_QUANTITY = 2;
    private final int TEST_HOODIE_BASE_PRICE = 4212;
    private final int TEST_HOODIE_TOTAL_COST = 10108;

    @Before
    public void setup() throws IOException {
        File testFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_CART).getFile());
        testCart = objectMapper.readValue(testFile, new TypeReference<List<Product>>(){});
        testHoodie = testCart.get(0);
    }

    @Test
    public void cartInstantiatesCorrectlyFromJson() {
        Assert.assertTrue(testCart.size() == 2);

        Assert.assertEquals(Constants.PRODUCT_TYPE_HOODIE, testHoodie.getProductType());
        Assert.assertEquals(TEST_HOODIE_OPTIONS, testHoodie.getOptions());
        Assert.assertEquals(TEST_HOODIE_ARTIST_MARKUP, testHoodie.getArtistMarkup());
        Assert.assertEquals(TEST_HODDIE_QUANTITY, testHoodie.getQuantity());
        Assert.assertNull(testHoodie.getBasePrice());
        Assert.assertNull(testHoodie.getTotalCost());
    }

    @Test
    public void findsBasePriceCorrectly() throws IOException {
        File testPriceFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_PRICES).getFile());
        List<BasePrice> testPrices = objectMapper.readValue(testPriceFile, new TypeReference<List<BasePrice>>(){});

        Assert.assertEquals(TEST_HOODIE_BASE_PRICE, testHoodie.findBasePrice(testPrices).intValue());
    }

    @Test
    public void calculateTotalCostCorrectly() {
        testHoodie.setBasePrice(TEST_HOODIE_BASE_PRICE);
        int exampleTotalCost = testHoodie.calculateTotalCost();
        Assert.assertEquals(TEST_HOODIE_TOTAL_COST, exampleTotalCost);
    }
}
