package com.anishgeorge.tddsale;

public interface Display {
    void displayPrice(Price price);

    void displayProductNotFoundMessage(String barcodeNotFound);

    void displayEmptyBarcodeMessage();
}
