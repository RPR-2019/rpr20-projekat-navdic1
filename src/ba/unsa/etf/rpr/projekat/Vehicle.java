package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vehicle {
    public enum VehicleCategory {
        TESKA, LAKA, SREDNJA;
    }
    public enum Fuel {
        BENSINE, DIESEL;
    }
    //owner??
    private int vehicleId;
    private VehicleCategory vehicleCategory;
    private SimpleStringProperty brand, motorNumber, registrationPlate;
    private Fuel fuel;
    private SimpleIntegerProperty year;

    // marka i tip, jacina motora, tip goriva, kategorija vozila, broj vrata, godište,
    // boja , kubikaža motora, broj sjedista, broj motora, broj šasije


    public Vehicle(int vehicleId, String vehicleCategory, String brand, String motorNumber, String registrationPlate, String fuel, int year) {
        this.vehicleId = vehicleId;
        this.vehicleCategory = VehicleCategory.valueOf(vehicleCategory);
        this.brand = new SimpleStringProperty(brand);
        this.motorNumber = new SimpleStringProperty(motorNumber);
        this.registrationPlate = new SimpleStringProperty(registrationPlate);
        this.fuel = Fuel.valueOf(fuel);
        this.year = new SimpleIntegerProperty(year);
    }

    public Vehicle() {
        this.brand = new SimpleStringProperty();
        this.motorNumber = new SimpleStringProperty();
        this.registrationPlate = new SimpleStringProperty();
        this.year = new SimpleIntegerProperty();
    }

    @Override
    public String toString() {
        return   getBrand() ;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleCategory getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(VehicleCategory vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }

    public String getMotorNumber() {
        return motorNumber.get();
    }

    public SimpleStringProperty motorNumberProperty() {
        return motorNumber;
    }

    public void setMotorNumber(String motorNumber) {
        this.motorNumber.set(motorNumber);
    }

    public String getRegistrationPlate() {
        return registrationPlate.get();
    }

    public SimpleStringProperty registrationPlateProperty() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate.set(registrationPlate);
    }

    public int getYear() {
        return year.get();
    }

    public SimpleIntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }
}
