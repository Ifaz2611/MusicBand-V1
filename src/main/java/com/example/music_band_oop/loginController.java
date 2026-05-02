package com.example.music_band_oop;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class loginController {

    @FXML private TextField UserIdTextField;
    @FXML private ComboBox<String> UserTypeComboBox;
    @FXML private PasswordField userPasswordField;
    @FXML private Label labelHeadeing;

    @FXML
    public void initialize() {
        UserTypeComboBox.getItems().addAll(
                "Sound Engineer", "Event Coordinator"
        );
    }

    @FXML
    public void onSignInButtonClick() {
        try {
            String userId = UserIdTextField.getText();
            String userType = UserTypeComboBox.getValue();
            String userPass = userPasswordField.getText();

            // ✅ USER ID VALIDATION (must be 4 or 7 digits)
            if (userId == null || !userId.matches("\\d{4}|\\d{7}")) {
                showAlert("Error", "User ID must be 4 or 7 digits", AlertType.ERROR);
                return;
            }

            // ✅ USER TYPE VALIDATION
            if (userType == null) {
                showAlert("Error", "Please select User Type", AlertType.ERROR);
                return;
            }

//            // ✅ PASSWORD VALIDATION
//            if (!isValidPassword(userPass)) {
//                showAlert("Error",
//                        "Password must be at least 6 characters and include:\n" +
//                                "- One uppercase letter\n" +
//                                "- One number",
//                        AlertType.ERROR);
//                return;
//            }

            // ✅ ROLE-BASED PASSWORD CHECK
            if (!checkLogin(userId, userType, userPass)) {
                showAlert("Login Failed", "Invalid credentials", AlertType.ERROR);
                return;
            }

            String fxmlFile = getDashboardFileForUserType(userType);

            if (fxmlFile == null) {
                showAlert("Not Implemented",
                        "Dashboard for " + userType + " not available",
                        AlertType.WARNING);
                return;
            }

            // ✅ FIX: get stage correctly
            Stage stage = (Stage) UserIdTextField.getScene().getWindow();

            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Something went wrong: " + e.getMessage(), AlertType.ERROR);
        }
    }

//    // 🔐 PASSWORD RULE
//    private boolean isValidPassword(String password) {
//        return password != null &&
//                password.matches("^(?=.*[A-Z])(?=.*\\d).{6,}$");
//    }

    // 🔐 SIMPLE LOGIN LOGIC (you can replace with DB later)
    private boolean checkLogin(String userId, String userType, String password) {

        // Example credentials
        if (userType.equals("Sound Engineer")) {
            return userId.equals("1111") && password.equals("1111");
        }
        else if (userType.equals("Event Coordinator")) {
            return userId.equals("2222") && password.equals("2222");
        }

        return false;
    }

    private String getDashboardFileForUserType(String userType) {
        switch (userType) {
            case "Event Coordinator":
                return "DashboardOfUsers/EventCoordinatorDashbroad.fxml";
            case "Sound Engineer":
                return "DashboardOfUsers/SoundEngineerDashbroad.fxml";
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