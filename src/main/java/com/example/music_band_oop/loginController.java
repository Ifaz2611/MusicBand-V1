package com.example.music_band_oop;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class loginController {

    @FXML private Button SignInButtonOnAction;
    @FXML private TextField UserIdTextField;
    @FXML private TextField UserPasswordTextField;
    @FXML private ComboBox<String> UserTypeComboBox;


    @FXML
    public void initialize() {
        UserTypeComboBox.getItems().addAll(
                "Sound Engineer", "Event Coordinator", "Bassist", "Keyboardist","Lead Guitarist", "Drummer");
    }

    @FXML
    public void onSignInButtonClick() {
        try {
            String userId = UserIdTextField.getText();
            String userType = UserTypeComboBox.getValue();
            String userPass = UserPasswordTextField.getText();
            if (userId.isEmpty()) {
                showAlert("Error", "Please enter User ID", AlertType.ERROR);
                return;
            }
            if (userType.isEmpty()) {
                showAlert("Error", "Please select User Type", AlertType.ERROR);
                return;
            }
            if (userPass.isEmpty()) {
                showAlert("Error", "Please select User Password", AlertType.ERROR);
                return;
            }

            String fxmlFile = getDashboardFileForUserType(userType);

            if (fxmlFile == null) {
                showAlert("Not Implemented",
                        "The dashboard for '" + userType + "' is not yet available.\n" +
                                "Please check back later or select a different role.",
                        AlertType.WARNING);
                return;
            }

            System.out.println("Looking for: " + fxmlFile);
            System.out.println("Resource URL: " + getClass().getResource(fxmlFile));

            Stage stage = (Stage) SignInButtonOnAction.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Something went wrong: " + e.getMessage(), AlertType.ERROR);
        }
    }


    private String getDashboardFileForUserType(String userType) {
        switch (userType) {
            case "Event Coordinator":
                return "DashboardOfUsers/EventCoordinatorDashbroad.fxml";
            case "Sound Engineer":
                return "DashboardOfUsers/SoundEngineerDashbroad.fxml";
            case "Bassist":
                return "BassistUser_KeyboardistUser_FXML/bassist_dashboard.fxml";

            case "Keyboardist":
                return "BassistUser_KeyboardistUser_FXML/keyboardist_dashboard.fxml";

                default:
                return null;
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