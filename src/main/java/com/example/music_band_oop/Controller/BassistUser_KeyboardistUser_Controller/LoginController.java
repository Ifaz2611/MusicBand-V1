package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController
{
    @javafx.fxml.FXML
    private TextField usernameField;
    @javafx.fxml.FXML
    private ComboBox roleCombo;
    @javafx.fxml.FXML
    private PasswordField passwordField;
    @javafx.fxml.FXML
    private Label messageLabel;

    @javafx.fxml.FXML
    public void initialize() {
        roleCombo.getItems().addAll("Bassist", "Keyboardist");
        roleCombo.getSelectionModel().selectFirst();
    }

    @javafx.fxml.FXML
    public void loginBtn(ActionEvent actionEvent) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = (String) roleCombo.getValue();

        try {
            Stage stage = (Stage) usernameField.getScene().getWindow();

            // Bassist
            if (username.equals("user") && password.equals("123") && role.equals("Bassist")) {
                Parent root = FXMLLoader.load(getClass().getResource("bassist_dashboard.fxml"));
                stage.setScene(new Scene(root));
            }

            // Keyboardist
            else if (username.equals("user") && password.equals("123") && role.equals("Keyboardist")) {
                Parent root = FXMLLoader.load(getClass().getResource("keyboardist_dashboard.fxml"));
                stage.setScene(new Scene(root));
            }

            // Wrong login
            else {
                messageLabel.setText("Wrong username/password!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}