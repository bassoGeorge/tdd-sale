package com.anishgeorge.tddsale.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public abstract class CatalogContract {

    protected abstract Catalog catalogWith(String barcode, Price price);
    protected abstract Catalog catalogWithout(String barcodeToAvoid);

    @Test
    public void productFound() {

        Price foundPrice = Price.cents(1250);

        Catalog catalog = catalogWith("12345", foundPrice);

        assertEquals(foundPrice, catalog.findPrice("12345"));
    }

    @Test
    public void productNotFound() {
        Catalog catalog = catalogWithout("12345");
        assertEquals(null, catalog.findPrice("12345"));
    }
}
