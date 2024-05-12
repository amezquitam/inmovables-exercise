package com.mehill.unmovables.entity;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class AbstractUnmovableTest {
    private final class MockUnmovable extends AbstractUnmovable {
        private MockUnmovable(Integer code, Integer stratum, Client client) {
            super(code, stratum, client);
        }

        @Override
        protected Double calculateRentCost() {
            return 500_000.0;
        }

        @Override
        public String getTypeOfUnmovable() {
            return "mock unmovable";
        }
    }

    @Test
    void testGetRentCostStratumFive() {
        AbstractUnmovable unmovable = new MockUnmovable(101, 5, null);

        Double rentCost = unmovable.getRentCost();
        Double expectedRentCost = 500_000.0;

        assertEquals(rentCost, expectedRentCost);
    }

    
    @Test
    void testGetRentCostStratumSix() {
        AbstractUnmovable unmovable = new MockUnmovable(101, 6, null);

        Double rentCost = unmovable.getRentCost();
        Double expectedRentCost = 550_000.0;

        assertEquals(rentCost, expectedRentCost);
    }
}
