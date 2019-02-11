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

    public static class ConsoleDisplay {

        public void displayProductNotFoundMessage(String barcodeNotFound) {
            System.out.println(String.format("Product not found for %s", barcodeNotFound));
        }
    }
}
