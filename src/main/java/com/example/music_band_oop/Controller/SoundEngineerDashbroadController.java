package com.example.music_band_oop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class SoundEngineerDashbroadController
{
    @javafx.fxml.FXML
    private Label SoundEngineerDashboardFxId;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void soundCheckButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void postShowButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void audioProblemButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logoutButtonOnAction(ActionEvent actionEvent) {

        try {
            // Load the login FXML
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/login.fxml"));

            // Get the current stage
            Stage stage = (Stage) SoundEngineerDashboardFxId.getScene().getWindow();

            // Create new scene with login page
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading login: " + e.getMessage());
        }

    }

    @javafx.fxml.FXML
    public void monitorControlButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void maintenanceButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void liveMonitoringButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void soundSetupButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void recordingFeedbackButtonOnAction(ActionEvent actionEvent) {
    }
}