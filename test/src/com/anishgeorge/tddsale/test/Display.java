package com.anishgeorge.tddsale.test;

public interface Display {
    void displayPrice(Price price);

    void displayProductNotFoundMessage(String barcodeNotFound);

    void displayEmptyBarcodeMessage();
}
