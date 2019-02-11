package com.anishgeorge.tddsale.test;

import com.anishgeorge.tddsale.Catalog;
import com.anishgeorge.tddsale.InMemoryCatalog;
import com.anishgeorge.tddsale.Price;

import java.util.HashMap;

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


}
