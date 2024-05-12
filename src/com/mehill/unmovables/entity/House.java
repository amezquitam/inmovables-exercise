package com.mehill.unmovables.entity;

public class House extends AbstractUnmovable {
    private Integer rooms;
    
    public House(Client client, Integer code, Integer stratum, Integer rooms) {
        super(code, stratum, client);
        validateRooms(rooms);
        this.rooms = rooms;
    }

    @Override
    public String getTypeOfUnmovable() {
        return "Casa";
    }

    @Override
    protected Double calculateRentCost() {
        Double base = 250_000.0;
        Double aditionalForRooms = (rooms - 2) * 120_000.0;
        return base + aditionalForRooms;
    }

    public Integer getRooms() {
        return rooms;
    }

    private void validateRooms(Integer rooms) {
        if (rooms < 2) {
            throw new RuntimeException("Rooms must be at least 2");
        }
    }

    public void setRooms(Integer rooms) {
        validateRooms(rooms);
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return "Casa-" + getCode() + " (" + getRooms() + " habitaciones), estrato " + getStratum();
    }

}
