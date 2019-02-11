package com.anishgeorge.tddsale;

import java.util.Collections;

public class VirtualPointOfSaleTerminal {
    public static void main(String[] args) {
        new SaleController(new InMemoryCatalog(Collections.emptyMap()), new EnglishLanguageConsoleDisplay());
    }
}
