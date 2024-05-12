package com.mehill.unmovables.entity;

public class Apartment extends AbstractUnmovable {
    private Integer floor;

    public Apartment(Client client, Integer code, Integer stratum, Integer floor) {
        super(code, stratum, client);
        validateFloor(floor);
        this.floor = floor;
    }

    @Override
    protected Double calculateRentCost() {
        if (1 <= floor && floor <= 5) {
            return 500_000.0;
        }
        else if (6 <= floor && floor <= 10) {
            return 700_000.0;
        }
        else {
            return 1_000_000.0;
        }
    }

    @Override
    public String getTypeOfUnmovable() {
        return "Apartamento";
    }

    private void validateFloor(Integer floor) {
        if (floor < 1 || floor > 11) {
            throw new RuntimeException("Floor must be between 1 and 11");
        }
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        validateFloor(floor);
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Apartamento-" + getCode() + " (Piso " + getFloor() + "), estrato " + getStratum();
    }
}
