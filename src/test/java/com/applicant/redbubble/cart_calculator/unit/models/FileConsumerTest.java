package com.applicant.redbubble.cart_calculator.unit.models;

import com.applicant.redbubble.cart_calculator.Constants;
import com.applicant.redbubble.cart_calculator.models.Product;
import com.applicant.redbubble.cart_calculator.services.FileConsumer;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileConsumerTest {

    private final ClassLoader classLoader = getClass().getClassLoader();

    private File testCartFile;
    private File testBasePriceFile;

    @Before
    public void setup() throws IOException {
        testCartFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_CART).getFile());
        testBasePriceFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_PRICES).getFile());
    }

    @Test
    public void readCartFileSuccessfully() {
        List<Product> testProducts = FileConsumer.readCartFile(testCartFile);
        System.out.println("Hello, world.");
    }
}
