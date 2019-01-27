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
        sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "$7.99");
            put("23456", "$8.32");
        }});
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
        Sale sale = new Sale(display, Collections.emptyMap()); // can be null as well

        sale.onBarcode("");
        assertEquals("Scan error: empty barcode", display.getText());
    }

    public static class Sale {
        private Display display;
        private Map<String, String> pricesByBarcode;

        public Sale(Display display, Map<String, String> pricesByBarcode) {
            this.display = display;
            this.pricesByBarcode = pricesByBarcode;
        }


        public void onBarcode(String barcode) {
            if (barcode.equals("")) {
                display.setText("Scan error: empty barcode");
                return;
            }

            if (pricesByBarcode.containsKey(barcode))
                display.setText(pricesByBarcode.get(barcode));
            else
                display.setText("Product not found for barcode " + barcode);
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
    }
}
