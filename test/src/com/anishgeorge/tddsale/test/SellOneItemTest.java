package com.anishgeorge.tddsale.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SellOneItemTest {

    @Test
    public void oneProductFound() {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "$7.99");
            put("23456", "$8.32");
        }});

        sale.onBarcode("12345");
        assertEquals("$7.99", display.getText());
    }

    @Test
    public void secondProductFound() {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "$7.99");
            put("23456", "$8.32");
        }});

        sale.onBarcode("23456");
        assertEquals("$8.32", display.getText());
    }

    @Test
    public void productNotFound() {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "$7.99");
            put("23456", "$8.32");
        }});

        sale.onBarcode("99999");
        assertEquals("Product not found for barcode 99999", display.getText());
    }

    @Test
    public void emptyBarcode() {
        Display display = new Display();
        Sale sale = new Sale(display, new HashMap<String, String>() {{
            put("12345", "$7.99");
            put("23456", "$8.32");
        }});

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
            } else {
                if (pricesByBarcode.containsKey(barcode))
                    display.setText(pricesByBarcode.get(barcode));
                else
                    display.setText("Product not found for barcode " + barcode);
            }
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
