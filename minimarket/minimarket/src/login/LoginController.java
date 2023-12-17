/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */

package login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Button loginButton;

    private Label registerLabel;



    private Alert alert;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    // Metode lainnya dapat ditambahkan sesuai kebutuhan
    @FXML
    private void handleRegisterHyperlinkAction(ActionEvent event) {
        try {
            // Load the registration page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/registrasi/register.fxml"));
            Parent root = loader.load();

            // Create a new stage for the registration page
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            // Set the stage properties
            stage.setTitle("Registration Page");
            stage.setScene(scene);
            stage.show();

            // Close the current window (optional)
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }

    @FXML
    public void handleLoginAction(ActionEvent event) {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Username/Password");
            alert.showAndWait();
        } else {
            String selectData = "SELECT username, password FROM users WHERE username = ? and password = ?";

            connect = Database.connectDB();

            try {
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, usernameField.getText());
                prepare.setString(2, passwordField.getText());

                result = prepare.executeQuery();

                // IF SUCCESSFULLY LOGIN, THEN PROCEED TO ANOTHER FORM WHICH IS OUR MAIN FORM
                if (result.next()) {
                    // TO GET THE USERNAME THAT USER USED
                    Data.username = usernameField.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    // LINK YOUR MAIN FORM
                    Parent root = FXMLLoader.load(getClass().getResource("/userview/inventory/mainForm.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setTitle("MINIMARKET");
                    stage.setMinWidth(1100);
                    stage.setMinHeight(600);

                    stage.setScene(scene);
                    stage.show();

                    // Close the current window (optional)
                    ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
                } else { // IF NOT, THEN THE ERROR MESSAGE WILL APPEAR
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }
                    ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
}
