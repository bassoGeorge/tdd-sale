package com.anishgeorge.tddsale.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class PriceFormattingTest {
    private final Integer priceInCents;
    private final String expectedFormattedPrice;

    public PriceFormattingTest(Integer priceInCents, String expectedFormattedPrice) {
        this.priceInCents = priceInCents;
        this.expectedFormattedPrice = expectedFormattedPrice;
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
    public void formatShouldWork() {
        assertEquals(expectedFormattedPrice, format(Price.cents(priceInCents)));
    }

    public static String format(Price price) {
        return String.format("$%,.2f", price.dollarValue());
    }

}
