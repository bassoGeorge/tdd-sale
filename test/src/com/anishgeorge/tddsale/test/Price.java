package com.anishgeorge.tddsale.test;

import java.util.Objects;

public class Price {
    private Integer amountInCents;

    public Price(Integer amountInCents) {
        this.amountInCents = amountInCents;
    }

    public String getFormatted() {
        return String.format("$%,.2f", amountInCents / 100.0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(amountInCents, price.amountInCents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amountInCents);
    }
}
