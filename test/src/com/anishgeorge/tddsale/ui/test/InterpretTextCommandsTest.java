package com.anishgeorge.tddsale.ui.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class InterpretTextCommandsTest {

    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void zero() throws IOException {
        BarcodeScannedListener barcodeScannedListener = context.mock(BarcodeScannedListener.class);
        context.checking(new Expectations() {{
            never(barcodeScannedListener);
        }});

        new TextCommandInterpreter(barcodeScannedListener).process(new StringReader(""));
    }

    @Test
    public void oneBarcode() throws IOException {
        BarcodeScannedListener barcodeScannedListener = context.mock(BarcodeScannedListener.class);
        context.checking(new Expectations() {{
            oneOf(barcodeScannedListener).onBarcode("::barcode::");
        }});

        new TextCommandInterpreter(barcodeScannedListener).process(new StringReader("::barcode::\n"));
    }

    public interface BarcodeScannedListener {
        void onBarcode(String barcode);
    }

    private class TextCommandInterpreter {
        private BarcodeScannedListener barcodeScannedListener;

        private TextCommandInterpreter(BarcodeScannedListener barcodeScannedListener) {
            this.barcodeScannedListener = barcodeScannedListener;
        }

        public void process(Reader stringReader) throws IOException {
            BufferedReader commandReader = new BufferedReader(stringReader);
            String line = commandReader.readLine();
            if (line != null) {
                barcodeScannedListener.onBarcode(line);
            }
        }
    }
}
