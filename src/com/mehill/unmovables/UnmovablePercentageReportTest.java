package com.mehill.unmovables;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.List;

import com.mehill.unmovables.entity.AbstractUnmovable;
import com.mehill.unmovables.entity.Apartment;
import com.mehill.unmovables.entity.Client;
import com.mehill.unmovables.entity.House;

public class UnmovablePercentageReportTest {
    private UnmovableManager unmovableManager;
    private UnmovablePercentageReport unmovablePercentageReport;

    public UnmovablePercentageReportTest() {
        this.unmovableManager = new UnmovableManager();

        Client raul = new Client("Raul Gonzales", "123456789");
        Client paul = new Client("Paul Gonzales", "987654321");
        Client saul = new Client("Saul Gonzales", "456123789");

        List<AbstractUnmovable> defaultUnmovables = List.of(
                new House(null, 7901, 3, 2),
                new House(saul, 7902, 4, 4),
                new House(paul, 7903, 5, 3),
                new House(saul, 7904, 6, 10),
                new Apartment(null, 5201, 4, 2),
                new Apartment(raul, 5401, 3, 4),
                new Apartment(null, 5601, 5, 6),
                new Apartment(paul, 5801, 2, 8),
                new Apartment(paul, 71001, 3, 10),
                new Apartment(saul, 71101, 6, 11));

        unmovableManager.addAll(defaultUnmovables);
        unmovablePercentageReport = new UnmovablePercentageReport(unmovableManager);
    }

    @Test
    void testPercentageOfHousesMoreThanTwoRooms() {
        Double calculatedPercentage = unmovablePercentageReport.percentageOfHousesMoreThanTwoRooms();
        Double expectedPercentage = 3.0 / 4.0;
        assertEquals(calculatedPercentage, expectedPercentage);
    }

    @Test
    void testPercentageOfUnrentedUnmovables() {
        Double calculatedPercentage = unmovablePercentageReport.percentageOfUnrentedUnmovables();
        Double expectedPercentage = 3.0 / 10.0;
        assertEquals(calculatedPercentage, expectedPercentage);
    }
}
