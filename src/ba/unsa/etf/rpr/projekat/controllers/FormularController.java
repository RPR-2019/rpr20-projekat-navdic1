package ba.unsa.etf.rpr.projekat.controllers;

import ba.unsa.etf.rpr.projekat.*;
import ba.unsa.etf.rpr.projekat.enums.Fuel;
import ba.unsa.etf.rpr.projekat.enums.InspectionType;
import ba.unsa.etf.rpr.projekat.enums.VehicleCategory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;



public class FormularController {
    public TextField nameFld, surnameFld, serialNumberFld, motorNumberFld, registrPlateFld, brandFld;
    public ChoiceBox<VehicleCategory> categoryChoiceBox;
    public ChoiceBox<InspectionType> inspectionTypeChoiceBox;
    public ChoiceBox<Fuel>fuelChoiceBox;
    public Spinner yearSpinner;
    public ComboBox<Owner> odaberiKlijentaComboBox;
    public ComboBox<Vehicle> odaberiVoziloComboBox;

    public FormularController() { }

    @FXML
    public void initialize(){
        categoryChoiceBox.setItems(FXCollections.observableArrayList(VehicleCategory.values()));
        inspectionTypeChoiceBox.setItems(FXCollections.observableArrayList(InspectionType.values()));
        fuelChoiceBox.setItems(FXCollections.observableArrayList(Fuel.values()));
        categoryChoiceBox.setValue(VehicleCategory.LAKA);
        inspectionTypeChoiceBox.setValue(InspectionType.REGULAR);
        fuelChoiceBox.setValue(Fuel.DIESEL);
            // bindati text field sa comboboxovima??
    }
    public void cancelAction(){}
    public void saveAction(){}

}
