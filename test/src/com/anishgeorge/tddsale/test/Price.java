package com.anishgeorge.tddsale.test;

import java.util.Objects;

public class Price {
    private Double amount;

    public Price(Double amount) {
        this.amount = amount;
    }

    public String getFormatted() {
        return String.format("$%.2f", amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(amount, price.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
