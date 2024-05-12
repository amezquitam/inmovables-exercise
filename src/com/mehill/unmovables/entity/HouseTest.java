package com.mehill.unmovables.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

public class HouseTest {
    @Test
    void testCalculateRentCostWithTwoRooms() {
        House house = new House(null, 17542, 5, 2);

        Double rentCost = house.calculateRentCost();
        Double expectedRentCost = 250_000.0;

        assertEquals(rentCost, expectedRentCost);
    }

    @Test
    void testCalculateRentCostWithMoreThanTwoRooms() {
        House house = new House(null, 17542, 6, 5);

        Double rentCost = house.calculateRentCost();
        Double expectedRentCost = 250_000.0 + 120_000.0 * 3;

        assertEquals(rentCost, expectedRentCost);
    }

    @Test
    void testRoomsLowerThanTwoThrows() {
        assertThrowsExactly(RuntimeException.class, () -> {
            new House(null, 17542, 6, -5);
        });

        assertThrowsExactly(RuntimeException.class, () -> {
            new House(null, 17542, 6, 1);
        });
    }
}
