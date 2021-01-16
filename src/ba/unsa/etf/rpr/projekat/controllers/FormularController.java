package ba.unsa.etf.rpr.projekat.controllers;

import ba.unsa.etf.rpr.projekat.*;
import ba.unsa.etf.rpr.projekat.enums.Fuel;
import ba.unsa.etf.rpr.projekat.enums.InspectionEvaluation;
import ba.unsa.etf.rpr.projekat.enums.InspectionType;
import ba.unsa.etf.rpr.projekat.enums.VehicleCategory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class FormularController {
    public TextField nameFld, surnameFld, serialNumberFld, motorNumberFld, registrPlateFld, brandFld;
    public ChoiceBox<VehicleCategory> categoryChoiceBox;
    public ChoiceBox<InspectionType> inspectionTypeChoiceBox;
    public ChoiceBox<Fuel>fuelChoiceBox;
    public Spinner yearSpinner;
    public ComboBox<Owner> odaberiKlijentaComboBox;
    public ComboBox<Vehicle> odaberiVoziloComboBox;
    private Inspection inspection;
    public FormularController() {
        inspection = new Inspection();
    }

    @FXML
    public void initialize(){
        categoryChoiceBox.setItems(FXCollections.observableArrayList(VehicleCategory.values()));
        inspectionTypeChoiceBox.setItems(FXCollections.observableArrayList(InspectionType.values()));
        fuelChoiceBox.setItems(FXCollections.observableArrayList(Fuel.values()));
        categoryChoiceBox.setValue(VehicleCategory.LAKA);
        inspectionTypeChoiceBox.setValue(InspectionType.REGULAR);
        fuelChoiceBox.setValue(Fuel.DIESEL);
            // bindati text field sa comboboxovima??
        nameFld.textProperty().addListener(obs->emptyField(nameFld));
        surnameFld.textProperty().addListener(obs->emptyField(surnameFld));
        serialNumberFld.textProperty().addListener(obs-> emptyField(serialNumberFld));
        brandFld.textProperty().addListener(obs-> emptyField(brandFld));
        motorNumberFld.textProperty().addListener(obs-> emptyField(motorNumberFld));
        registrPlateFld.textProperty().addListener(obs-> emptyField(registrPlateFld));
    }

    public void saveAction(){

        if(emptyField(nameFld) || emptyField(surnameFld) || emptyField(serialNumberFld)
                || emptyField(brandFld) || emptyField(motorNumberFld) || emptyField(registrPlateFld) )
            return;
        // dodati za ostale boxove

        inspection.setVehicle(new Vehicle(0, categoryChoiceBox.getValue().name(), brandFld.getText(), motorNumberFld.getText(), registrPlateFld.getText(), fuelChoiceBox.getValue().name(), (Integer) yearSpinner.getValue()));
        inspection.setOwner(new Owner(0, nameFld.getText(), surnameFld.getText(), serialNumberFld.getText()));

        inspection.setInspectionType(inspectionTypeChoiceBox.getValue());
        inspection.setInspectionEvaluation(InspectionEvaluation.NO_DATA);
        /// user nevalja
        //inspection.setUser(new User(1, "t", "t"));
        Stage stage =(Stage) brandFld.getScene().getWindow();
        stage.close();
    }
    public void cancelAction(){
        inspection = null;
        Stage stage = (Stage) brandFld.getScene().getWindow();
        stage.close();
    }

    private boolean emptyField(TextField textField){
        if(textField.getText().trim().isEmpty()){
            textField.getStyleClass().removeAll("fieldValid");
            textField.getStyleClass().add("fieldNotValid");
            return true;
        }else{
            textField.getStyleClass().removeAll("fieldNotValid");
            textField.getStyleClass().add("fieldValid");
        }
        return false;
    }

    public Inspection getInspection() {
        return inspection;
    }
}
