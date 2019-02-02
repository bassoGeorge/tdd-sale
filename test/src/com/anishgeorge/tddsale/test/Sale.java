package com.anishgeorge.tddsale.test;

import java.util.ArrayList;
import java.util.Collection;

public class Sale {
    private Catalog catalog;
    private Display display;
    private Collection<Price> pendingPurchaseItemPrices = new ArrayList<>();

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

        if (price == null) {
            display.displayProductNotFoundMessage(barcode);
        } else {
            // Eventually some shopping cart?
            pendingPurchaseItemPrices.add(price);
            display.displayPrice(price);
        }
    }

    public void onTotal() {
        if (pendingPurchaseItemPrices.isEmpty()) {
            display.displayNoSaleMessage();
        } else {
            display.displayTotal(pendingPurchaseTotal());
        }
    }

    // Looks like model behaviour
    private Price pendingPurchaseTotal() {
        return computePurchaseTotal(pendingPurchaseItemPrices);
    }

    public static Price computePurchaseTotal(Collection<Price> purchaseItems) {
        return new Price(purchaseItems.stream().mapToInt(Price::getAmountInCents).sum());
    }

}
