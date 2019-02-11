package com.anishgeorge.tddsale;

import java.util.HashMap;

public class VirtualPointOfSaleTerminal {
    public static void main(String[] args) {
        InMemoryCatalog catalog = new InMemoryCatalog(new HashMap<String, Price>() {{
            put("12345", Price.cents(795));
        }});

        EnglishLanguageConsoleDisplay display = new EnglishLanguageConsoleDisplay();

        SaleController saleController = new SaleController(catalog, display);

        saleController.onBarcode("12345");
        saleController.onBarcode("23456");
        saleController.onBarcode("");

    }
}
