package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;

public class Vehicle {
    private SimpleStringProperty ownerName, ownerSurname, ownerIdNum;
    private String type, brand, fuel, colour;
    // marka i tip, jacina motora, tip goriva, kategorija vozila, broj vrata, godište,
    // boja , kubikaža motora, broj sjedista, broj motora, broj šasije


    public Vehicle(String ownerName, String ownerSurname, String ownerIdNum, String type, String brand, String fuel, String colour) {
        this.ownerName = new SimpleStringProperty(ownerName);
        this.ownerSurname = new SimpleStringProperty(ownerSurname);
        this.ownerIdNum = new SimpleStringProperty(ownerIdNum);
        this.type = type;
        this.brand = brand;
        this.fuel = fuel;
        this.colour = colour;
    }

    public String getOwnerName() {
        return ownerName.get();
    }

    public SimpleStringProperty ownerNameProperty() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName.set(ownerName);
    }

    public String getOwnerSurname() {
        return ownerSurname.get();
    }

    public SimpleStringProperty ownerSurnameProperty() {
        return ownerSurname;
    }

    public void setOwnerSurname(String ownerSurname) {
        this.ownerSurname.set(ownerSurname);
    }

    public String getOwnerIdNum() {
        return ownerIdNum.get();
    }

    public SimpleStringProperty ownerIdNumProperty() {
        return ownerIdNum;
    }

    public void setOwnerIdNum(String ownerIdNum) {
        this.ownerIdNum.set(ownerIdNum);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
