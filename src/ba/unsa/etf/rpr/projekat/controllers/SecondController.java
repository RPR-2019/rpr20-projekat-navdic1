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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

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
            //  controllorCol.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().getUser().toString()));

            tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldInspection, newInspection)->{
                tableView.refresh();
            });
    }

    public void addNewAction(ActionEvent actionEvent){
        Stage stage = new Stage();
        Parent root = null;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/formular.fxml"));
            FormularController formularController = new FormularController();
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
                    //  technicalInspection.setInspectionEvaluation("Visual Passed");
                    //
                }
                });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void archiveAction(ActionEvent actionEvent){}
    public void deleteAction(ActionEvent actionEvent){}
    public void modifyAction(ActionEvent actionEvent){}
    public void identificationAction(ActionEvent actionEvent){}
    public void inspectionAction(ActionEvent actionEvent){}
    public void archivedInspectionsAction(ActionEvent actionEvent){}
    public void archivedClientsAction(ActionEvent actionEvent){}

}
