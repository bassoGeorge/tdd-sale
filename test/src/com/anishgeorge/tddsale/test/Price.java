package com.anishgeorge.tddsale.test;

public class Price {
    private Double amount;

    public Price(Double amount) {
        this.amount = amount;
    }

    public String getFormatted() {
        return String.format("$%.2f", amount);
    }
}
