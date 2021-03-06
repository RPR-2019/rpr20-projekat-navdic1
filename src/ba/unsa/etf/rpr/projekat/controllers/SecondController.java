package ba.unsa.etf.rpr.projekat.controllers;

import ba.unsa.etf.rpr.projekat.Inspection;
import ba.unsa.etf.rpr.projekat.InspectionDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

import static javafx.scene.control.PopupControl.USE_COMPUTED_SIZE;


public class SecondController {
    private InspectionDAO dao;
    public TableView<Inspection> tableView;
    public TableColumn<Inspection,String> vehicleCol;
    public TableColumn<Inspection, String> ownerCol;
    public TableColumn <Inspection, String> evaluationCol;
    //public TableColumn <Inspection, String>userCol;
    private ObservableList<Inspection> inspections;

    public SecondController() {
        dao =  InspectionDAO.getInstance();
        inspections = FXCollections.observableArrayList(dao.getInspections());
    }

    @FXML
        public void initialize(){
            tableView.setItems(inspections);
            vehicleCol.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getVehicle().getBrand()));
            ownerCol.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getOwner().toString()));
            evaluationCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getInspectionEvaluation().toString()));
            //  userCol.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getUser().toString()));

            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldInspection, newInspection)->{
                tableView.refresh();
            });
    }

    public void addNewAction(ActionEvent actionEvent){
        Stage stage = new Stage();
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/formular.fxml"));
            FormularController formularController = new FormularController(null, dao.getOwners(), dao.getVehicles());
            loader.setController(formularController);
            root = loader.load();
            stage.setTitle("Formular");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            stage.setResizable(false); // formulari
            stage.show();
            stage.setOnHiding(event->{
                Inspection inspection = formularController.getInspection();
                if(inspection!=null) {
                    int idOwner = dao.addOwner(inspection.getOwner());
                    int idVehicle = dao.addVehicle(inspection.getVehicle());
                    inspection.getOwner().setOwnerId(idOwner);
                    inspection.getVehicle().setVehicleId(idVehicle);
                    dao.addInspection(inspection);
                    inspections.setAll(dao.getInspections());
                    //  inspection.setInspectionEvaluation("Visual Passed");
                    //
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyAction(ActionEvent actionEvent){
        Inspection inspection = tableView.getSelectionModel().getSelectedItem();
        if(inspection == null) return;
        Stage stage = new Stage();
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/formular.fxml"));
            FormularController formularController = new FormularController(inspection,  dao.getOwners(), dao.getVehicles());
            loader.setController(formularController);
            root = loader.load();
            stage.setTitle("Formular");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE,USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
            stage.setOnHiding(event->{
                Inspection modifiedInsp = formularController.getInspection();
                if(modifiedInsp!=null){
                    dao.modifyOwner(modifiedInsp.getOwner());
                    dao.modifyVehicle(modifiedInsp.getVehicle());
                    dao.modifyInspection(modifiedInsp);
                    inspections.setAll(dao.getInspections());
                    // modifiedInsp.setInspectionEvaluation("Visual Passed");
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void archiveAction(ActionEvent actionEvent){}
    public void deleteAction(ActionEvent actionEvent){
        Inspection inspection = tableView.getSelectionModel().getSelectedItem();
        if(inspection == null) return;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda brisanja");
        alert.setHeaderText("Brisanje naloga "+inspection.getVehicle().toString() + ", " + inspection.getOwner().toString());
        alert.setContentText("Da li ste sigurni da želite obrisati?");
        alert.setResizable(true);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dao.deleteOwner(inspection.getOwner());
            dao.deleteVehicle(inspection.getVehicle());
            dao.deleteInspection(inspection);
            inspections.remove(inspection);
        }
    }
    public void identificationAction(ActionEvent actionEvent){}
    public void inspectionAction(ActionEvent actionEvent){}
    public void archivedInspectionsAction(ActionEvent actionEvent){}
    public void archivedClientsAction(ActionEvent actionEvent){}

}
