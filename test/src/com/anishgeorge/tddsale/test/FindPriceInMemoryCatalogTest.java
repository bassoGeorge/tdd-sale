package com.anishgeorge.tddsale.test;

import java.util.HashMap;
import java.util.Map;

public class FindPriceInMemoryCatalogTest extends CatalogContract {

    @Override
    protected Catalog catalogWith(String barcode, Price price) {
        return new InMemoryCatalog(new HashMap<String, Price>() {{
            put("definitely not " + barcode, Price.cents(0));
            put(barcode, price);
            put("again, definitely not barcode " + barcode, Price.cents(0));
        }});
    }

    @Override
    protected Catalog catalogWithout(String barcodeToAvoid) {
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
