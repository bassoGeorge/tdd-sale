package com.anishgeorge.tddsale.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class DisplayMessagesToConsoleTest {

    public PrintStream prodSystemOut;

    @Before
    public void rememberSystemOut() throws Exception {
        prodSystemOut = System.out;
    }

    @After
    public void restoreSystemOut() throws Exception {
        System.setOut(prodSystemOut);
    }

    @Test
    public void productNotFoundMessage() throws UnsupportedEncodingException {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new ConsoleDisplay().displayProductNotFoundMessage("23");

        assertEquals(
                Arrays.asList("Product not found for 23"),
                TextUtilities.lines(canvas.toString("UTF-8"))
        );
    }

    @Test
    public void emptyBarcodeMessage() throws UnsupportedEncodingException {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new ConsoleDisplay().displayEmptyBarcodeMessage();

        assertEquals(
                Arrays.asList("Scanning error: empty barcode"),
                TextUtilities.lines(canvas.toString("UTF-8"))
        );
    }

    @Test
    public void multipleMessages() throws UnsupportedEncodingException {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        ConsoleDisplay consoleDisplay = new ConsoleDisplay();

        consoleDisplay.displayProductNotFoundMessage("23");
        consoleDisplay.displayEmptyBarcodeMessage();
        consoleDisplay.displayProductNotFoundMessage("1234");

        assertEquals(
                Arrays.asList(
                        "Product not found for 23",
                        "Scanning error: empty barcode",
                        "Product not found for 1234"
                ),
                TextUtilities.lines(canvas.toString("UTF-8"))
        );
    }

    public static class ConsoleDisplay {

        public void displayProductNotFoundMessage(String barcodeNotFound) {
            System.out.println(String.format("Product not found for %s", barcodeNotFound));
        }

        public void displayEmptyBarcodeMessage() {
            System.out.println("Scanning error: empty barcode");
        }
    }
}
