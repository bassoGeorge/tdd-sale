package com.anishgeorge.tddsale.test;

import java.util.ArrayList;
import java.util.Collection;

public class Sale {
    private Catalog catalog;
    private Display display;
    private Collection<Price> scannedPrices = new ArrayList<>();

    public Sale(Catalog catalog, Display display) {
        this.catalog = catalog;
        this.display = display;
    }


    public void onBarcode(String barcode) {
        if (barcode.equals("")) {
            display.displayEmptyBarcodeMessage();
            return;
        }

        Price price = catalog.findPrice(barcode);
        scannedPrices.add(price);

        if (price == null) {
            display.displayProductNotFoundMessage(barcode);
        } else {
            display.displayPrice(price);
        }
    }

    public void onTotal() {
        boolean saleInProgress = !scannedPrices.isEmpty();
        if (saleInProgress) {
            Integer total = scannedPrices.stream().mapToInt(Price::getAmountInCents).sum();
            display.displayTotal(new Price(total));
        } else {
            display.displayNoSaleMessage();
        }
    }

}
