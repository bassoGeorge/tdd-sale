package com.anishgeorge.tddsale.test;

public class ConsoleDisplay {

    public void displayProductNotFoundMessage(String barcodeNotFound) {
        displayMessage("Product not found for %s", barcodeNotFound);
    }

    public void displayEmptyBarcodeMessage() {
        displayMessage("Scanning error: empty barcode");
    }

    public void displayPrice(Price price) {
        displayMessage("$%,.2f", price.dollarValue());
    }

    private void displayMessage(String format, Object... placeholderValues) {
        render(mergeTemplate(format, placeholderValues));
    }

    private void render(String merged) {
        System.out.println(merged);
    }

    private String mergeTemplate(String format, Object[] placeholderValues) {
        return String.format(format, placeholderValues);
    }

}
