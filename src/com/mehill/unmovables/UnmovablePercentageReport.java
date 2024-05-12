package com.mehill.unmovables;

import java.util.List;
import java.util.stream.Collectors;

import com.mehill.unmovables.entity.House;

public class UnmovablePercentageReport {
    private UnmovableManager unmovableManager;

    public UnmovablePercentageReport(UnmovableManager unmovableManager) {
        this.unmovableManager = unmovableManager;
    }

    public Double percentageOfHousesMoreThanTwoRooms() {
        List<House> allHouses = unmovableManager.stream()
                .filter(u -> u instanceof House)
                .map(u -> (House) u)
                .collect(Collectors.toList());

        long moreThanTwoRoomsHouses = allHouses.stream()
                .filter(h -> h.getRooms() > 2)
                .count();

        long totalHouses = allHouses.size();

        return (double) moreThanTwoRoomsHouses / (double) totalHouses;
    }

    public Double percentageOfUnrentedUnmovables() {
        long unrentedUnmovables = unmovableManager.stream()
                .filter(u -> u.getClient() == null)
                .count();

        int totalUnmovables = unmovableManager.size();

        return (double) unrentedUnmovables / (double) totalUnmovables;
    }
}
