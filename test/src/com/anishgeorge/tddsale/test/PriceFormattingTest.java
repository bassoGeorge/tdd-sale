package com.anishgeorge.tddsale.test;

import com.anishgeorge.tddsale.EnglishLanguageConsoleDisplay;
import com.anishgeorge.tddsale.Price;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PriceFormattingTest {
    private final Integer priceInCents;
    private final String expectedFormattedPrice;
    public PrintStream prodSystemOut;

    public PriceFormattingTest(Integer priceInCents, String expectedFormattedPrice) {
        this.priceInCents = priceInCents;
        this.expectedFormattedPrice = expectedFormattedPrice;
    }

    @Before
    public void rememberSystemOut() throws Exception {
        prodSystemOut = System.out;
    }

    @After
    public void restoreSystemOut() throws Exception {
        System.setOut(prodSystemOut);
    }

    @Parameterized.Parameters(name = "{index} Monetary amount {0} formats to {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {3, "$0.03"},
                {40, "$0.40"},
                {600, "$6.00"},
                {799, "$7.99"},
                {8099, "$80.99"},
                {10099, "$100.99"},
                {300045, "$3,000.45"},
                {125034199, "$1,250,341.99"},
        });
    }

    @Test
    public void formatShouldWork() throws UnsupportedEncodingException {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new EnglishLanguageConsoleDisplay().displayPrice(Price.cents(priceInCents));

        assertEquals(
                Arrays.asList(expectedFormattedPrice),
                TextUtilities.lines(canvas.toString("UTF-8"))
        );
    }

}
