package com.anishgeorge.tddsale.test;

import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SellMultipleItemsTest {

    @Test
    public void zeroItemsFound() {
        Display display = new Display();
        Sale sale = new Sale(null, display);
        sale.onTotal();
        assertEquals("No sale in progress. Try scanning a product", display.getText());
    }

    @Test
    public void oneItemFound() {
        Display display = new Display();
        Catalog catalog = new Catalog(Collections.singletonMap("12345", new Price(6.5)));
        Sale sale = new Sale(catalog, display);
        sale.onBarcode("12345");

        sale.onTotal();

        assertEquals("Total: $6.50", display.getText());
    }
}
