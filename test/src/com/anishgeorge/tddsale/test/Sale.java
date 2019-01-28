package com.anishgeorge.tddsale.test;

public class Sale {
    private Catalog catalog;
    private Display display;

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
            display.displayPrice(price);
        }
    }

}
