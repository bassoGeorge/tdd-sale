package com.anishgeorge.tddsale.test;

public class Display {

    private String text;

    public String getText() {
        return text;
    }

    public void displayEmptyBarcodeMessage() {
        this.text = "Scan error: empty barcode";
    }

    public void displayProductNotFoundMessage(String barcode) {
        this.text = "Product not found for barcode " + barcode;
    }

    public void displayPrice(Price price) {
        this.text = price.getFormatted();
    }
}
