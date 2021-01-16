package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class InspectionDAO {
    private static InspectionDAO instance;
    private static Connection connection;

    private PreparedStatement getInspectionsQuery, getOwnerQuery, getVehicleQuery,
    getVehicleIdQuery, getOwnerIdQuery, getInspectionIdQuery, addOwnerQuery, addVehicleQuery,
    addInspectionQuery;

    private InspectionDAO() {
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:inspectionDatabase.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            getInspectionsQuery = connection.prepareStatement("SELECT * FROM inspection");
        } catch (SQLException throwables) {
            regenerateBase();
            try {
                getInspectionsQuery = connection.prepareStatement("SELECT * FROM inspection");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            getOwnerQuery = connection.prepareStatement("SELECT * FROM owner WHERE owner_id=?");
            getVehicleQuery = connection.prepareStatement("SELECT * FROM vehicle WHERE vehicle_id=?");
            getOwnerIdQuery = connection.prepareStatement("SELECT MAX(owner_id)+1 FROM owner");
            getVehicleIdQuery = connection.prepareStatement("SELECT MAX(vehicle_id)+1 FROM vehicle");
            getInspectionIdQuery = connection.prepareStatement("SELECT MAX(inspection_id)+1 FROM inspection");
            addOwnerQuery = connection.prepareStatement("INSERT INTO owner VALUES (?,?,?,?)");
            addVehicleQuery = connection.prepareStatement("INSERT INTO vehicle VALUES (?,?,?,?,?,?,?)");
            addInspectionQuery = connection.prepareStatement("INSERT INTO inspection VALUES (?,?,?,?,?)");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static InspectionDAO getInstance(){
        if(instance==null) instance = new InspectionDAO();
        return instance;
    }
    public static void removeInstance(){
        if(instance==null) return;
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        instance = null;
    }
    private void regenerateBase(){
        Scanner entry = null;
        try {
            entry = new Scanner(new FileInputStream("inspectionDatabase.db.sql"));
            String query = "";
            while (entry.hasNext()) {
                query += entry.nextLine();
                if (query.length() > 1 && query.charAt(query.length() - 1) == ';') {
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(query);
                        query = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            entry.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Inspection> getInspections() {
        ObservableList<Inspection> inspections = FXCollections.observableArrayList();
        try {
            ResultSet rs = getInspectionsQuery.executeQuery();
            while (rs.next()){
                inspections.add(getInspectionFromResultSet(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return inspections;
    }

    private Inspection getInspectionFromResultSet(ResultSet rs) throws SQLException {
        Inspection inspection = new Inspection(rs.getInt(1),null, null, rs.getString(4), rs.getString(5));
        inspection.setVehicle(getVehicle(rs.getInt(2)));
        inspection.setOwner(getOwner(rs.getInt(3)));
        //  inspection.setUser(getUser(rs.getInt(6)));
        return inspection;
    }

    private Owner getOwner(int anInt) {
        try{
            getOwnerQuery.setInt(1, anInt);
            ResultSet rs = getOwnerQuery.executeQuery();
            if(!rs.next()) return null;
            return getOwnerFromResultSet(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    private  Vehicle getVehicle(int anInt) {
        try {
            getVehicleQuery.setInt(1,anInt);
            ResultSet rs = getVehicleQuery.executeQuery();
            if(!rs.next()) return null;
            return getVehicleFromResultSet(rs);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
    private Vehicle getVehicleFromResultSet(ResultSet rs) throws SQLException {
        return new Vehicle(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
    }
    private Owner getOwnerFromResultSet(ResultSet rs) throws SQLException {
        return new Owner(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
    }
    public int addOwner(Owner owner){
        try{
            ResultSet rs = getOwnerIdQuery.executeQuery();
            int id = 1;
            while (rs.next()){
                id  = rs.getInt(1);
            }
            addOwnerQuery.setInt(1,id);
            addOwnerQuery.setString(2, owner.getName());
            addOwnerQuery.setString(3, owner.getSurname());
            addOwnerQuery.setString(4, owner.getSerialNumber());
            addOwnerQuery.executeUpdate();
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public int addVehicle(Vehicle vehicle){
        try{
            ResultSet rs = getVehicleIdQuery.executeQuery();
            int id = 1;
            while (rs.next()){
                id  = rs.getInt(1);
            }
            addVehicleQuery.setInt(1,id);
            addVehicleQuery.setString(2, vehicle.getVehicleCategory().name());
            addVehicleQuery.setString(3, vehicle.getBrand());
            addVehicleQuery.setString(4, vehicle.getMotorNumber());
            addVehicleQuery.setString(5, vehicle.getRegistrationPlate());
            addVehicleQuery.setString(6, vehicle.getFuel().name());
            addVehicleQuery.setInt(7, vehicle.getYear());
            addVehicleQuery.executeUpdate();
            return id;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }
    public void addInspection(Inspection inspection){

        try{
            ResultSet rs = getInspectionIdQuery.executeQuery();
            int id = 1;
            while (rs.next()){
                id  = rs.getInt(1);
            }
            addInspectionQuery.setInt(1,id);
            addInspectionQuery.setInt(2, inspection.getOwner().getOwnerId());
            addInspectionQuery.setInt(3, inspection.getVehicle().getVehicleId());
            addInspectionQuery.setString(4, inspection.getInspectionType().name());
            addInspectionQuery.setString(5, inspection.getInspectionEvaluation().name());
            addInspectionQuery.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
