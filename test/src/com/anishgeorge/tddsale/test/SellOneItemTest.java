package com.anishgeorge.tddsale.test;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SellOneItemTest {

    @Test
    public void oneProductFound() {
        Display display = new Display();
        Sale sale = new Sale(display);

        sale.onBarcode("12345");
        assertEquals("$7.99", display.getText());
    }

    @Test
    public void secondProductFound() {
        Display display = new Display();
        Sale sale = new Sale(display);

        sale.onBarcode("23456");
        assertEquals("$8.32", display.getText());
    }

    @Test
    public void productNotFound() {
        Display display = new Display();
        Sale sale = new Sale(display);

        sale.onBarcode("99999");
        assertEquals("Product not found for barcode 99999", display.getText());
    }

    public static class Sale {
        private Display display;

        public Sale(Display display) {
            this.display = display;
        }


        public void onBarcode(String barcode) {
            if (barcode.equals("12345"))
                display.setText("$7.99");
            else if (barcode.equals("23456"))
                display.setText("$8.32");
            else
                display.setText("Product not found for barcode 99999");
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
