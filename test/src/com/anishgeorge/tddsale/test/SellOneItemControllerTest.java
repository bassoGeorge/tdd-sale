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
        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);
        Price irrelavantPrice = Price.cents(795); // So that we don't have to worry about value semantics just right now

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with("12345"));
            will(returnValue(irrelavantPrice));

            oneOf(display).displayPrice(with(irrelavantPrice));
        }});

        final SaleController saleController = new SaleController(catalog, display);
        saleController.onBarcode("12345");
    }

    @Test
    public void productNotFound() {
        final Catalog catalog = context.mock(Catalog.class);
        final Display display = context.mock(Display.class);
        final String productNotFound = "::product not found::";

        context.checking(new Expectations() {{
            allowing(catalog).findPrice(with(productNotFound));
            will(returnValue(null));

            oneOf(display).displayProductNotFoundMessage(with(productNotFound));
        }});

        final SaleController saleController = new SaleController(catalog, display);
        saleController.onBarcode(productNotFound);
    }

    public interface Catalog {
        Price findPrice(String barcode);
    }

    public interface Display {
        void displayPrice(Price price);

        void displayProductNotFoundMessage(String barcodeNotFound);
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
            Price price = catalog.findPrice(barcode);
            if (price == null) display.displayProductNotFoundMessage(barcode);
            else display.displayPrice(price);
        }
    }
}
