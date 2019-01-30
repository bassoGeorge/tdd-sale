package com.anishgeorge.tddsale.test;

public class Sale {
    private Catalog catalog;
    private Display display;
    private Price price;

    public Sale(Catalog catalog, Display display) {
        this.catalog = catalog;
        this.display = display;
    }


    public void onBarcode(String barcode) {
        if (barcode.equals("")) {
            display.displayEmptyBarcodeMessage();
            return;
        }

        price = catalog.findPrice(barcode);
        if (price == null) {
            display.displayProductNotFoundMessage(barcode);
        } else {
            display.displayPrice(price);
        }
    }

    public void onTotal() {
        if (price == null)
            display.displayNoSaleMessage();
        else {
            display.displayTotal(price);
        }
    }

}
