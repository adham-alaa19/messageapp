
package com.iti.models;

import com.iti.database.psql.PsqlComposite;


public class Address implements PsqlComposite {

    private String governorate;
    private String district;
    private String street;
    private int building;



    public Address(String governorate,String district, String street, int building, String country, String postalCode) {
        this.district = district;
        this.street = street;
        this.governorate = governorate;
        this.building = building;

    }

    public String getGovernorate() {
        return governorate;
    }

    public String getStreet() {
        return street;
    }

    public int getBuilding() {
        return building;
    }

    public String getDistrict() {
        return district;
    }
    
     

     
}
    

