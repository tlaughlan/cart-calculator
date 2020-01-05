package com.applicant.redbubble.cart_calculator.unit.models;

import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BasePriceTest {

    private final String TEST_FILE_NAME = "test-base-prices.json";

    /**
     * This test can afford to be quite simple, since the BasePrice class is a basic POJO with no logic.
     * We just need to ensure that the objects instantiate properly.
     * @throws IOException
     */
    @Test
    public void basePricesInstantiateCorrectlyFromJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClassLoader classLoader = getClass().getClassLoader();
        File testFile = new File(classLoader.getResource(TEST_FILE_NAME).getFile());
        List<BasePrice> testPrices = objectMapper.readValue(testFile, new TypeReference<List<BasePrice>>(){});

        Assert.assertTrue(testPrices.size() == 3);
    }
}
