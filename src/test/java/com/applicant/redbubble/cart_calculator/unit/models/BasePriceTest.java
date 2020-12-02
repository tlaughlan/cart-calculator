package com.applicant.redbubble.cart_calculator.unit.models;

import com.applicant.redbubble.cart_calculator.Constants;
import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.services.FileConsumer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasePriceTest {

    private final ClassLoader classLoader = getClass().getClassLoader();

    private List<BasePrice> testPrices;
    private File testBasePriceFile;

    private Map<String, String> dummyProductOptionsTrue = new HashMap<>();
    private Map<String, String> dummyProductOptionsFalse = new HashMap<>();

    @Before
    public void setup() throws IOException {
        testBasePriceFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_PRICES).getFile());
        testPrices = FileConsumer.readBasePriceFile(testBasePriceFile);

        dummyProductOptionsTrue.put("size", "small");
        dummyProductOptionsTrue.put("colour", "dark");

        dummyProductOptionsFalse.put("colour", "dark");
        dummyProductOptionsFalse.put("size", "large");
    }

    @Test
    public void groupPricesByProductTypeCorrectly() {
        Map<String, List<BasePrice>> groupedBasePrices = BasePrice.groupPricesByProductType(testPrices);
        Assert.assertEquals(3, groupedBasePrices.size());
        Assert.assertTrue(groupedBasePrices.containsKey(Constants.PRODUCT_TYPE_HOODIE));
        Assert.assertTrue(groupedBasePrices.containsKey(Constants.PRODUCT_TYPE_STICKER));
        Assert.assertTrue(groupedBasePrices.containsKey(Constants.PRODUCT_TYPE_LEGGINGS));
    }

    @Test
    public void returnNullWhenGroupingOnNullBasePriceList() {
        testPrices = null;
        Assert.assertNull(BasePrice.groupPricesByProductType(testPrices));
    }

    @Test
    public void returnNullWhenGroupingOnEmptyBasePriceList() {
        testPrices.removeAll(testPrices);
        Assert.assertEquals(0, testPrices.size());
        Assert.assertNull(BasePrice.groupPricesByProductType(testPrices));
    }

    @Test
    public void testContainsOptionsTrue() {
        Assert.assertTrue(testPrices.get(0).containsOptions(dummyProductOptionsTrue));
    }

    @Test
    public void testContainsOptionsFalse() {
        Assert.assertFalse(testPrices.get(0).containsOptions(dummyProductOptionsFalse));
    }
}
