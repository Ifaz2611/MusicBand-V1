package com.example.music_band_oop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class loginController {

    @FXML
    private Button SignInButtonOnAction;
    @FXML
    private TextField UserIdTextField;
    @FXML
    private TextField UserPasswordTextField;
    @FXML
    private ComboBox<String> UserTypeComboBox;

    @FXML
    public void initialize() {
        UserTypeComboBox.getItems().addAll(
                "Sound Engineer", "Event Coordinator", "Lead Guitarist",
                "Drummer", "Bassist", "Keyboardist", "Band Manager", "Lead Vocalist"
        );
    }

    @FXML
    public void onSignInButtonClick() throws Exception {
        String userId = UserIdTextField.getText();
        String userType = UserTypeComboBox.getValue();

        if (userId.isEmpty()) {
            showAlert("Error", "Please enter User ID!", AlertType.ERROR);
        } else if (userType == null) {
            showAlert("Error", "Please select User Type!", AlertType.ERROR);
        } else {
            // Switch to Dashboard Scene
            Stage stage = (Stage) SignInButtonOnAction.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    private void showAlert(String title, String message, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}