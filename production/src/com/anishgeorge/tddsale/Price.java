package com.anishgeorge.tddsale;

public class Price {

    private final int centsValue;

    public Price(int centsValue) {
        this.centsValue = centsValue;
    }

    public static Price cents(int centsValue) {
        return new Price(centsValue);
    }

    public double dollarValue() {
        return centsValue / 100d;
    }

    @Override
    public String toString() {
        return "Price.cents(" + centsValue + ")";
    }
}
