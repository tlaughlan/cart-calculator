package com.applicant.redbubble.cart_calculator.unit.models;

import com.applicant.redbubble.cart_calculator.Constants;
import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class BasePriceTest {

    private final Map<String, List<String>> TEST_PRICE_OPTIONS = new HashMap<String, List<String>>() {{
        put("colour", Arrays.asList("white", "dark"));
        put("size", Arrays.asList("small", "medium"));
    }};
    private final int TEST_PRICE_BASE_PRICE = 3800;

    @Test
    public void basePricesInstantiateCorrectlyFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File testFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_PRICES).getFile());
        List<BasePrice> testPrices = objectMapper.readValue(testFile, new TypeReference<List<BasePrice>>(){});

        Assert.assertTrue(testPrices.size() == 10);

        BasePrice spotcheckPrice = testPrices.get(0);
        Assert.assertEquals(Constants.PRODUCT_TYPE_HOODIE, spotcheckPrice.getProductType());
        Assert.assertEquals(TEST_PRICE_OPTIONS, spotcheckPrice.getOptions());
        Assert.assertEquals(TEST_PRICE_BASE_PRICE, spotcheckPrice.getBasePrice());
    }
}
