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
        sale = new Sale(new Catalog(new HashMap<String, String>() {{
            put("12345", "$7.99");
            put("23456", "$8.32");
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
        private Display display;
        private Map<String, String> pricesByBarcode;

        public Sale(Catalog catalog, Display display) {
            this.display = display;
            this.pricesByBarcode = catalog.getPricesByBarcode();
        }


        public void onBarcode(String barcode) {
            if (barcode.equals("")) {
                display.displayEmptyBarcodeMessage();
                return;
            }

            String priceAsText = findPrice(barcode);
            if (priceAsText == null) {
                display.displayProductNotFoundMessage(barcode);
            } else {
                display.displayPrice(priceAsText);
            }
        }

        private String findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }

    }

    public static class Display {

        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void displayPrice(String priceAsText) {
            setText(priceAsText);
        }

        public void displayEmptyBarcodeMessage() {
            setText("Scan error: empty barcode");
        }

        public void displayProductNotFoundMessage(String barcode) {
            setText("Product not found for barcode " + barcode);
        }
    }

    public static class Catalog {
        private final Map<String, String> pricesByBarcode;

        public Catalog(Map<String, String> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Map<String, String> getPricesByBarcode() {
            return pricesByBarcode;
        }
    }
}
