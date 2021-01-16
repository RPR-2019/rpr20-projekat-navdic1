package ba.unsa.etf.rpr.projekat.controllers;




import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {

    public TextField passwordFld, usernameFld;

    @FXML
    public void initialize() {

        usernameFld.textProperty().addListener((((observableValue, oldValue, newValue) -> {
            if (!newValue.isEmpty()) { // dodati validaciju
                usernameFld.getStyleClass().removeAll("fieldNotValid");
                usernameFld.getStyleClass().add("fieldValid");
            } else {
                usernameFld.getStyleClass().removeAll("fieldValid");
                usernameFld.getStyleClass().add("fieldNotValid");
            }
        })));
        passwordFld.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.isEmpty()) { // dodati validaciju
                passwordFld.getStyleClass().removeAll("fieldNotValid");
                passwordFld.getStyleClass().add("fieldValid");
            } else {
                passwordFld.getStyleClass().removeAll("fieldValid");
                passwordFld.getStyleClass().add("fieldNotValid");
            }
        });

    }

    public void loginAction() {
        Thread thread = new Thread(() -> {
            //if() ako nema u bazi usera
            //else
            Platform.runLater(() -> {
                //zatvori login
                //otvori drugi
                Stage stage = new Stage();
                Parent root = null;
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/secondScreen.fxml"));
                    SecondController secondController = new SecondController();
                    loader.setController(secondController);
                    root = loader.load();
                    stage.setTitle("Tehnički pregled");
                    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        thread.start();

    }
    public void exitAction(ActionEvent actionEvent){
    }

}
