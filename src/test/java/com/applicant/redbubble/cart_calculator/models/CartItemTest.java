package com.applicant.redbubble.cart_calculator.models;

import com.applicant.redbubble.cart_calculator.Constants;
import com.applicant.redbubble.cart_calculator.services.FileConsumer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartItemTest {

    private final ClassLoader classLoader = getClass().getClassLoader();
    private List<CartItem> testCartItems;
    private File testCartFile;
    private CartItem testHoodie;

    private final Map<String, String> TEST_HOODIE_OPTIONS = new HashMap<String, String>() {{
        put("size", "large");
        put("colour", "dark");
        put("print-location", "front");
    }};
    private final int TEST_HOODIE_ARTIST_MARKUP = 20;
    private final int TEST_HODDIE_QUANTITY = 2;

    @Before
    public void setup() {
        testCartFile = new File(classLoader.getResource(Constants.FILE_NAME_TEST_CART).getFile());
        testCartItems = FileConsumer.readCartFile(testCartFile);
        testHoodie = testCartItems.get(0);
    }

    @Test
    public void cartItemsInstantiateCorrectlyFromJson() {
        Assert.assertTrue(testCartItems.size() == 2);

        Assert.assertEquals(Constants.PRODUCT_TYPE_HOODIE, testHoodie.getProductType());
        Assert.assertEquals(TEST_HOODIE_OPTIONS, testHoodie.getOptions());
        Assert.assertEquals(TEST_HOODIE_ARTIST_MARKUP, testHoodie.getArtistMarkup());
        Assert.assertEquals(TEST_HODDIE_QUANTITY, testHoodie.getQuantity());
    }
}
