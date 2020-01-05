package com.applicant.redbubble.cart_calculator.unit.models;

import com.applicant.redbubble.cart_calculator.models.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductTest {

    private final String TEST_FILE_NAME = "test-cart.json";
    private final String TEST_HOODIE_PRODUCT_TYPE = "hoodie";
    private final Map<String, String> TEST_HOODIE_OPTIONS = new HashMap<String, String>() {{
        put("size", "small");
        put("colour", "dark");
        put("print-location", "front");
    }};
    private final int TEST_HOODIE_ARTIST_MARKUP = 20;
    private final int TEST_HODDIE_QUANTITY = 2;

    @Test
    public void cartInstantiatesCorrectlyFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File testFile = new File(classLoader.getResource(TEST_FILE_NAME).getFile());
        List<Product> testCart = objectMapper.readValue(testFile, new TypeReference<List<Product>>(){});

        Assert.assertTrue(testCart.size() == 2);

        Product spotcheckHoodie = testCart.get(0);
        Assert.assertEquals(TEST_HOODIE_PRODUCT_TYPE, spotcheckHoodie.getProductType());
        Assert.assertEquals(TEST_HOODIE_OPTIONS, spotcheckHoodie.getOptions());
        Assert.assertEquals(TEST_HOODIE_ARTIST_MARKUP, spotcheckHoodie.getArtistMarkup());
        Assert.assertEquals(TEST_HODDIE_QUANTITY, spotcheckHoodie.getQuantity());
    }
}
