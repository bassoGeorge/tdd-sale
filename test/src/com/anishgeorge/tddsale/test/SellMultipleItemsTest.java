package com.anishgeorge.tddsale.test;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class SellMultipleItemsTest {

    @Test
    public void zeroItemsFound() {
        Display display = new Display();
        Sale sale = new Sale(null, display);
        sale.onTotal();
        assertEquals("No sale in progress. Try scanning a product", display.getText());
    }

    @Test
    public void oneItemFound() {
        Display display = new Display();
        Catalog catalog = new Catalog(Collections.singletonMap("12345", new Price(650)));
        Sale sale = new Sale(catalog, display);
        sale.onBarcode("12345");

        sale.onTotal();

        assertEquals("Total: $6.50", display.getText());
    }

    @Test
    public void allItemsNotFound() {
        Display display = new Display();
        Sale sale = new Sale(catalogWithoutBarcodes("missing barcode","second missing barcode"), display);

        sale.onBarcode("missing barcode");
        sale.onBarcode("second missing barcode");
        sale.onTotal();

        assertEquals("No sale in progress. Try scanning a product", display.getText());
    }

    @Test
    public void manyItemsFound() {
        Display display = new Display();
        Catalog catalog = new Catalog(
                new HashMap<String, Price>() {{
                    put("1", new Price(650));
                    put("2", new Price(760));
                }}
        );
        Sale sale = new Sale(catalog, display);
        sale.onBarcode("1");
        sale.onBarcode("2");

        sale.onTotal();

        assertEquals("Total: $14.10", display.getText());
    }

    private Catalog catalogWithoutBarcodes(String... barcodesToExclude) {
        return emptyCatalog();
    }

    private Catalog emptyCatalog() {
        return new Catalog(Collections.emptyMap());
    }
}
