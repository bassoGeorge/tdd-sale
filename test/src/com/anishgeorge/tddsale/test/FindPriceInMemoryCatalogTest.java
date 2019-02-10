package com.anishgeorge.tddsale.test;

import org.junit.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FindPriceInMemoryCatalogTest {
    @Test
    public void productFound() {

        Price foundPrice = Price.cents(1250);

        Catalog catalog = catalogWith("12345", foundPrice);

        assertEquals(foundPrice, catalog.findPrice("12345"));
    }

    private Catalog catalogWith(String barcode, Price price) {
        return new InMemoryCatalog(Collections.singletonMap(barcode, price));
    }

    @Test
    public void productNotFound() {
        Catalog catalog = catalogWithout("12345");
        assertEquals(null, catalog.findPrice("12345"));
    }

    private Catalog catalogWithout(String barcodeToAvoid) {
        return catalogWith("anything but " + barcodeToAvoid, Price.cents(0));
    }


    public static class InMemoryCatalog implements Catalog {

        private Map<String, Price> pricesByBarcode;

        public InMemoryCatalog(Map<String, Price> pricesByBarcode) {
            this.pricesByBarcode = pricesByBarcode;
        }

        public Price findPrice(String barcode) {
            return pricesByBarcode.get(barcode);
        }

    }
}
