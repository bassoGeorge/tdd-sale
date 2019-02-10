package com.anishgeorge.tddsale.test;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SellOneItemControllerTest {

    // adding the context as a @Rule, it runs context.assertIsSatisfied(); at the end of every test
    @Rule
    public final JUnitRuleMockery context = new JUnitRuleMockery();

    @Test
    public void productFound() {
        Catalog catalog = context.mock(Catalog.class);
        Display display = context.mock(Display.class);
        Price irrelavantPrice = Price.cents(795); // So that we don't have to worry about value semantics just right now

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("12345"));
            will(returnValue(irrelavantPrice));

            oneOf(display).displayPrice(with(irrelavantPrice));
        }});

        SaleController saleController = new SaleController(catalog, display);
        saleController.onBarcode("12345");

    }

    public interface Catalog {
        Price findPrice(String barcode);
    }

    public interface Display {
        void displayPrice(Price price);
    }

    public static class Price {

        public static Price cents(int centsValue) {
            return new Price();
        }

        @Override
        public String toString() {
            return "a price";
        }
    }

    public static class SaleController {
        private Display display;
        private Catalog catalog;

        public SaleController(Catalog catalog, Display display) {
            this.display = display;
            this.catalog = catalog;
        }

        public void onBarcode(String barcode) {
            display.displayPrice(catalog.findPrice(barcode));
        }
    }
}
