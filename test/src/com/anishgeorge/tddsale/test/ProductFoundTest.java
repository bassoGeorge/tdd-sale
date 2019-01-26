package com.anishgeorge.tddsale.test;

import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class ProductFoundTest {

    @Test
    public void oneProductFound() {
        Sale sale = new Sale();
        Display display = new Display();

        sale.onBarcode("12345");
        assertEquals("$7.99", display.getText());
    }

    public static class Sale {
        public void onBarcode(String barcode) {
        }
    }

    public static class Display {
        public String getText() {
            return "$7.99";
        }
    }
}
