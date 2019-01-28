package com.anishgeorge.tddsale.test;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SellOneItemTest {

    private Display display;
    private Sale sale;

    @Before
    public void setUp() {
        display = new Display();
        sale = new Sale(new Catalog(new HashMap<String, Price>() {{
            put("12345", new Price(7.99));
            put("23456", new Price(8.32));
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


    public static class Sale {
        private Catalog catalog;
        private Display display;

        public Sale(Catalog catalog, Display display) {
            this.catalog = catalog;
            this.display = display;
        }


        public void onBarcode(String barcode) {
            if (barcode.equals("")) {
                display.displayEmptyBarcodeMessage();
                return;
            }

            Price price = catalog.findPrice(barcode);
            if (price == null) {
                display.displayProductNotFoundMessage(barcode);
            } else {
                display.displayPrice(price);
            }
        }

    }

    public static class Display {

        private String text;

        public String getText() {
            return text;
        }

        public void displayEmptyBarcodeMessage() {
            this.text = "Scan error: empty barcode";
        }

        public void displayProductNotFoundMessage(String barcode) {
            this.text = "Product not found for barcode " + barcode;
        }

        public void displayPrice(Price price) {
            this.text = price.getFormatted();
        }
    }

    public static class Catalog {
        private Map<String, Price> priceObjByBarcode;

        public Catalog(Map<String, Price> stringPriceHashMap) {
            priceObjByBarcode = stringPriceHashMap;
        }

        public Price findPrice(String barcode) {
            return priceObjByBarcode.get(barcode);
        }
    }
}
