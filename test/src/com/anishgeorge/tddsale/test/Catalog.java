package com.anishgeorge.tddsale.test;

import java.util.Map;

public class Catalog {
    private Map<String, Price> priceObjByBarcode;

    public Catalog(Map<String, Price> stringPriceHashMap) {
        priceObjByBarcode = stringPriceHashMap;
    }

    public Price findPrice(String barcode) {
        return priceObjByBarcode.get(barcode);
    }
}
