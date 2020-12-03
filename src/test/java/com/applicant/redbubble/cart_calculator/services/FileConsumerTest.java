package com.applicant.redbubble.cart_calculator.services;

import com.applicant.redbubble.cart_calculator.Constants;
import com.applicant.redbubble.cart_calculator.models.BasePrice;
import com.applicant.redbubble.cart_calculator.models.CartItem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class FileConsumerTest {

    private final ClassLoader classLoader = getClass().getClassLoader();

    private File testCartFile;
    private File testBasePriceFile;

    @Before
    public void setup() {
        testCartFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_CART).getFile());
        testBasePriceFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_PRICES).getFile());
    }

    @Test
    public void readCartFileSuccessfully() {
        List<CartItem> testCartItems = FileConsumer.readCartFile(testCartFile);
        Assert.assertEquals(2, testCartItems.size());
    }

    @Test
    public void returnNullWhenCartFileReadFails() {
        List<CartItem> testCartItems = FileConsumer.readCartFile(new File("my_imaginary_file.txt"));
        Assert.assertNull(testCartItems);
    }

    @Test
    public void readBasePriceFileSuccessfully() {
        List<BasePrice> testBasePrices = FileConsumer.readBasePriceFile(testBasePriceFile);
        Assert.assertEquals(10, testBasePrices.size());
    }

    @Test
    public void returnNullWhenBasePriceFileReadFails() {
        List<BasePrice> testBasePrices = FileConsumer.readBasePriceFile(new File("my_imaginary_file.txt"));
        Assert.assertNull(testBasePrices);
    }
}
