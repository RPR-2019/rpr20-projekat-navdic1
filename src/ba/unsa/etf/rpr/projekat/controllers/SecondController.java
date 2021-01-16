package ba.unsa.etf.rpr.projekat.controllers;

import ba.unsa.etf.rpr.projekat.Inspection;
import ba.unsa.etf.rpr.projekat.InspectionDAO;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;





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
    }
    public void archiveAction(ActionEvent actionEvent){}
    public void deleteAction(ActionEvent actionEvent){}
    public void modifyAction(ActionEvent actionEvent){}
    public void identificationAction(ActionEvent actionEvent){}
    public void inspectionAction(ActionEvent actionEvent){}
    public void archivedInspectionsAction(ActionEvent actionEvent){}
    public void archivedClientsAction(ActionEvent actionEvent){}

}
