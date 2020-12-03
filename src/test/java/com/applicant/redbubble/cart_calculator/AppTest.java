package com.applicant.redbubble.cart_calculator;

import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.services.FileConsumer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

public class AppTest {

    private final ClassLoader classLoader = getClass().getClassLoader();

    private List<BasePrice> testPrices;
    private File testBasePriceFile;

    @Before
    public void setup() {
        testBasePriceFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_PRICES).getFile());
        testPrices = FileConsumer.readBasePriceFile(testBasePriceFile);
    }

    @Test
    public void groupPricesByProductTypeCorrectly() {
        Map<String, List<BasePrice>> groupedBasePrices = App.groupPricesByProductType(testPrices);
        Assert.assertEquals(3, groupedBasePrices.size());
        Assert.assertTrue(groupedBasePrices.containsKey(Constants.PRODUCT_TYPE_HOODIE));
        Assert.assertTrue(groupedBasePrices.containsKey(Constants.PRODUCT_TYPE_STICKER));
        Assert.assertTrue(groupedBasePrices.containsKey(Constants.PRODUCT_TYPE_LEGGINGS));
    }

    @Test
    public void returnNullWhenGroupingOnNullBasePriceList() {
        testPrices = null;
        Assert.assertNull(App.groupPricesByProductType(testPrices));
    }

    @Test
    public void returnNullWhenGroupingOnEmptyBasePriceList() {
        testPrices.removeAll(testPrices);
        Assert.assertEquals(0, testPrices.size());
        Assert.assertNull(App.groupPricesByProductType(testPrices));
    }
}
