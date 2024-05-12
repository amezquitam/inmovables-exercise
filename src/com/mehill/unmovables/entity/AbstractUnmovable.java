package com.mehill.unmovables.entity;

public abstract class AbstractUnmovable {
    private Integer code;
    private Integer stratum;
    private Client client;

    public AbstractUnmovable(Integer code, Integer stratum, Client client) {
        validateStratum(stratum);
        this.code = code;
        this.stratum = stratum;
        this.client = client;
    }

    /**
     * In this method, concrete classes House, Aparment, etc. will calculate
     * the rent cost based on its properties
     */
    abstract protected Double calculateRentCost();

    abstract public String getTypeOfUnmovable();

    public Double getRentCost() {
        Double increment = stratum == 6 ? 0.1 : 0.0;
        return calculateRentCost() * (1 + increment);
    }

    private void validateStratum(Integer stratum) {
        if (stratum < 1 || stratum > 6) {
            throw new RuntimeException("Stratum must be between 1 and 6");
        }
    }

    public Integer getStratum() {
        return stratum;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setStratum(Integer stratum) {
        validateStratum(stratum);
        this.stratum = stratum;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractUnmovable other = (AbstractUnmovable) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }
}
