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

        new EnglishLanguageConsoleDisplay().displayProductNotFoundMessage("23");

        assertEquals(
                Arrays.asList("Product not found for 23"),
                TextUtilities.lines(canvas.toString("UTF-8"))
        );
    }

    @Test
    public void emptyBarcodeMessage() throws UnsupportedEncodingException {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        new EnglishLanguageConsoleDisplay().displayEmptyBarcodeMessage();

        assertEquals(
                Arrays.asList("Scanning error: empty barcode"),
                TextUtilities.lines(canvas.toString("UTF-8"))
        );
    }

    @Test
    public void multipleMessages() throws UnsupportedEncodingException {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        EnglishLanguageConsoleDisplay englishLanguageConsoleDisplay = new EnglishLanguageConsoleDisplay();

        englishLanguageConsoleDisplay.displayProductNotFoundMessage("23");
        englishLanguageConsoleDisplay.displayEmptyBarcodeMessage();
        englishLanguageConsoleDisplay.displayProductNotFoundMessage("1234");

        assertEquals(
                Arrays.asList(
                        "Product not found for 23",
                        "Scanning error: empty barcode",
                        "Product not found for 1234"
                ),
                TextUtilities.lines(canvas.toString("UTF-8"))
        );
    }

}
