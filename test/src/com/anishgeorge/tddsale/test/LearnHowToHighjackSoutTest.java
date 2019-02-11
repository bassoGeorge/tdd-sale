package com.anishgeorge.tddsale.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class LearnHowToHighjackSoutTest {

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
    public void singleLineOfText() throws UnsupportedEncodingException {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));
        System.out.println("Hello World!");

        assertEquals(
                Collections.singletonList("Hello World!"),
                TextUtilities.lines(canvas.toString("UTF-8"))
        );
    }

    @Test
    public void severalLinesOfText() throws UnsupportedEncodingException {
        ByteArrayOutputStream canvas = new ByteArrayOutputStream();
        System.setOut(new PrintStream(canvas));

        System.out.println("Hello World!");
        System.out.println("This is George");

        assertEquals(
                Arrays.asList("Hello World!", "This is George"),
                TextUtilities.lines(canvas.toString("UTF-8"))
        );
    }

}
