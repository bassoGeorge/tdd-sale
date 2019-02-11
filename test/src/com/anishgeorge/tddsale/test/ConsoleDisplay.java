package com.anishgeorge.tddsale.test;

public class ConsoleDisplay {

    public static String formatPrice(Price price) {
        return String.format("$%,.2f", price.dollarValue());
    }

    public void displayProductNotFoundMessage(String barcodeNotFound) {
        System.out.println(String.format("Product not found for %s", barcodeNotFound));
    }

    public void displayEmptyBarcodeMessage() {
        System.out.println("Scanning error: empty barcode");
    }

    public void displayPrice(Price price) {
        System.out.println(formatPrice(price));
    }
}
