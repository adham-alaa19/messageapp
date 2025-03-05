 package com.iti.models;

import com.iti.database.psql.PSQLComposite;

public class Address implements PSQLComposite {

    private String governorate;
    private String district;
    private String street;
    private int building;

    public Address(String governorate, String district, String street, int building) {
        this.district = district;
        this.street = street;
        this.governorate = governorate;
        this.building = building;
    }

    public Address() {
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }
}
