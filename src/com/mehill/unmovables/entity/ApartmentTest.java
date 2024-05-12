package com.mehill.unmovables.entity;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class ApartmentTest {
    @Test
    void testCalculateRentCostFloorOne() {
        Apartment apartment = new Apartment(null, 141, 5, 1);

        Double restCost = apartment.calculateRentCost();
        Double expectedRentCost = 500_000.0;

        assertEquals(restCost, expectedRentCost);

    }

    @Test
    void testCalculateRentCostFloorSeven() {
        Apartment apartment = new Apartment(null, 741, 5, 7);

        Double restCost = apartment.calculateRentCost();
        Double expectedRentCost = 700_000.0;

        assertEquals(restCost, expectedRentCost);
    }

    @Test
    void testCalculateRentCostFloorEleven() {
        Apartment apartment = new Apartment(null, 1141, 5, 11);

        Double restCost = apartment.calculateRentCost();
        Double expectedRentCost = 1_000_000.0;

        assertEquals(restCost, expectedRentCost);
    }
}
