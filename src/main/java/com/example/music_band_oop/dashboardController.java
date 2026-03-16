package com.example.music_band_oop;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

public class dashboardController {

    @FXML
    private Text welcomeText;
    @FXML
    private Button LogoutButton;

    @FXML
    public void initialize() {
        // You can customize welcome message here
        welcomeText.setText("Welcome to Music Band Simulation!");
    }

    @FXML
    public void onLogoutClick() throws Exception {
        // Switch back to Login Scene
        Stage stage = (Stage) LogoutButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}