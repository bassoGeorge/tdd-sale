package com.anishgeorge.tddsale.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SellMultipleItemsTest {

    @Test
    public void zeroProducts() {
        Display display = new Display();
        Sale sale = new Sale(null, display);
        sale.onTotal();
        assertEquals("No sale in progress. Try scanning a product", display.getText());
    }
}
