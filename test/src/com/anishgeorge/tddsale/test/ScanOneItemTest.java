package com.anishgeorge.tddsale.test;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ScanOneItemTest {

    private Display display;
    private Sale sale;

    @Before
    public void setUp() {
        display = new Display();
        sale = new Sale(new Catalog(new HashMap<String, Price>() {{
            put("12345", new Price(799));
            put("23456", new Price(832));
        }}), display);
    }

    @Test
    public void oneProductFound() {
        sale.onBarcode("12345");
        assertEquals("$7.99", display.getText());
    }

    @Test
    public void secondProductFound() {
        sale.onBarcode("23456");
        assertEquals("$8.32", display.getText());
    }

    @Test
    public void productNotFound() {
        sale.onBarcode("99999");
        assertEquals("Product not found for barcode 99999", display.getText());
    }

    @Test
    public void emptyBarcode() {
        // SMELL: since empty barcode behaviour does not really depend on all the items in the constructor,
        // maybe that code should go somewhere else. Violation of the SRP
        // SMELL signal2: selectively modifying the fixture
        Sale sale = new Sale(new Catalog(Collections.emptyMap()), display); // can be null as well

        sale.onBarcode("");
        assertEquals("Scan error: empty barcode", display.getText());
    }
}
