package ba.unsa.etf.rpr.projekat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Scanner;

public class InspectionDAO {
    private static InspectionDAO instance;
    private static Connection connection;

    private PreparedStatement getInspectionsQuery;

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
}
