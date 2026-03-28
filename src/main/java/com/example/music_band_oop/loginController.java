package com.example.music_band_oop;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private Label labelHeadeing;


    @FXML
    public void initialize() {
        // Populate the ComboBox with all possible user roles.
        // These can be extended later if new roles are added.
        UserTypeComboBox.getItems().addAll(
                "Sound Engineer", "Event Coordinator", "Lead Guitarist",
                "Drummer", "Bassist", "Keyboardist", "Band Manager", "Lead Vocalist"
        );
    }

    @FXML
    public void onSignInButtonClick() {
        try {
            String userId = UserIdTextField.getText();
            String userType = UserTypeComboBox.getValue();
            String userPass = UserPasswordTextField.getText();

            // Basic validation
            if (userId.isEmpty()) {
                showAlert("Error", "Please enter User ID!", AlertType.ERROR);
                return;
            }
            if (userType.isEmpty()) {
                showAlert("Error", "Please select User Type!", AlertType.ERROR);
                return;
            }
            if (userPass.isEmpty()) {
                showAlert("Error", "Please select User Password!", AlertType.ERROR);
                return;
            }

            // Determine which dashboard to load based on the user type.
            // This logic can be easily extended by adding new cases.
            String fxmlFile = getDashboardFileForUserType(userType);

            if (fxmlFile == null) {
                // No dashboard yet for this role – show a friendly message
                showAlert("Not Implemented",
                        "The dashboard for '" + userType + "' is not yet available.\n" +
                                "Please check back later or select a different role.",
                        AlertType.WARNING);
                return;
            }

            // Debug output (optional, can be removed)
            System.out.println("Looking for: " + fxmlFile);
            System.out.println("Resource URL: " + getClass().getResource(fxmlFile));

            // Switch to the dashboard scene
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

    /**
     * Returns the FXML file path for a given user type, or null if not implemented.
     * Add new user types here as dashboards become available.
     */

    private String getDashboardFileForUserType(String userType) {
        switch (userType) {
            case "Event Coordinator":
                return "DashboardOfUsers/EventCoordinatorDashbroad.fxml";
            case "Sound Engineer":
                return "DashboardOfUsers/SoundEngineerDashbroad.fxml";
            // Future user types can be added below:
            // case "Band Manager":
            //     return "DashboardOfUsers/BandManagerDashboard.fxml";
            // case "Lead Vocalist":
            //     return "DashboardOfUsers/LeadVocalistDashboard.fxml";
            default:
                return null; // Dashboard not implemented yet
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