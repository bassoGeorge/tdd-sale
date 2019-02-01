package com.anishgeorge.tddsale.test;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CatalogTest {

    @Test
    public void catalogReturnsPriceObject() {
        Price p1 = new Price(799);
        Price p2 = new Price(899);
        Catalog catalog = new Catalog(new HashMap<String, Price>() {{
            put("12345", p1);
            put("23456", p2);
        }});

        assertEquals(p1, catalog.findPrice("12345"));
        assertEquals(p2, catalog.findPrice("23456"));
    }
}
