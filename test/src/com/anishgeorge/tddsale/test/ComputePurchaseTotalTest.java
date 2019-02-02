package com.anishgeorge.tddsale.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ComputePurchaseTotalTest {
    @Test
    public void zeroItemsTest() {
        assertEquals(new Price(0), computePurchaseTotal(Collections.emptyList()));
    }

    @Test
    public void oneItemTest() {
        assertEquals(new Price(5), computePurchaseTotal(Collections.singletonList(new Price(5))));
    }

    @Test
    public void manyItemTest() {
        assertEquals(new Price(10), computePurchaseTotal(Arrays.asList(new Price(2), new Price(6), new Price(2))));
    }

    private Price computePurchaseTotal(List<Price> purchaseItems) {
        return Sale.computePurchaseTotal(purchaseItems);
    }
}
