/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package registrasi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import login.Database;
import javafx.scene.Node;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private Button RegistrasiButton;

    private Label registerLabel;

    private Alert alert;
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML
    public void regBtn(ActionEvent event) {
        if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            String regData = "INSERT INTO users (username, password, role) VALUES (?, ?, 'pembeli')";
            connect = Database.connectDB();

            try {
                // CHECK IF THE USERNAME IS ALREADY RECORDED
                String checkUsername = "SELECT username FROM users WHERE username = ?";
                prepare = connect.prepareStatement(checkUsername);
                prepare.setString(1, usernameField.getText());
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(usernameField.getText() + " is already taken");
                    alert.showAndWait();
                } else if (passwordField.getText().length() < 8) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Password, at least 8 characters are needed");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(regData);
                    prepare.setString(1, usernameField.getText());
                    prepare.setString(2, passwordField.getText());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    // It seems you were trying to set a value to the 5th parameter, but there are only 2 parameters
                    // I removed the incorrect line prepare.setString(5, String.valueOf(sqlDate));
                    
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully registered Account!");
                    alert.showAndWait();

                    usernameField.setText("");
                    passwordField.setText("");
                    
                    // Close the registration window
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();

                    // Open the login window
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/login.fxml"));
                    Parent root = loader.load();
                    Stage loginStage = new Stage();
                    Scene scene = new Scene(root);
                    loginStage.setTitle("Login Page");
                    loginStage.setScene(scene);
                    loginStage.show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleLoginHyperlinkAction(ActionEvent event) {
        try {
            // Load the login page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/login.fxml"));
            Parent root = loader.load();

            // Create a new stage for the login page
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            // Set the stage properties
            stage.setTitle("Login Page");
            stage.setScene(scene);
            stage.show();

            // Close the current window (optional)
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as needed
        }
    }
}
