package com.anishgeorge.tddsale.test;

public class Sale {
    private Catalog catalog;
    private Display display;
    private Price scannedPrice;

    public Sale(Catalog catalog, Display display) {
        this.catalog = catalog;
        this.display = display;
    }


    public void onBarcode(String barcode) {
        if (barcode.equals("")) {
            display.displayEmptyBarcodeMessage();
            return;
        }

        scannedPrice = catalog.findPrice(barcode);
        if (scannedPrice == null) {
            display.displayProductNotFoundMessage(barcode);
        } else {
            display.displayPrice(scannedPrice);
        }
    }

    public void onTotal() {
        boolean saleInProgress = scannedPrice != null;
        if (saleInProgress) {
            display.displayTotal(scannedPrice);
        } else {
            display.displayNoSaleMessage();
        }
    }

}
