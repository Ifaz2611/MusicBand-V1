package com.example.music_band_oop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
        try {

                                    // Always remembar to double check name of fxml file

            String resourcePath = "/com/example/music_band_oop/SoundEngineerGoals/Goal2_SoundEngineerMonitorLiveSound.fxml";
            java.net.URL resourceUrl = getClass().getResource(resourcePath);
            if (resourceUrl == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "FXML not found.");
                alert.showAndWait();
                return;
            }
            Parent root = FXMLLoader.load(resourceUrl);
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Live Sound Monitor - Goal 2");
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load Live Monitoring view.\n" + e.getMessage());
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void soundSetupButtonOnAction(ActionEvent actionEvent) {
        try {
            String resourcePath = "/com/example/music_band_oop/SoundEngineerGoals/Goal1_SoundEngineerSetupManagement.fxml";
            java.net.URL resourceUrl = getClass().getResource(resourcePath);
            if (resourceUrl == null) {
                System.err.println("Resource not found: " + resourcePath);
                Alert alert = new Alert(Alert.AlertType.ERROR, "FXML file not found.");
                alert.showAndWait();
                return;
            }
            Parent root = FXMLLoader.load(resourceUrl);
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sound Setup - Goal 1");
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Could not load Sound Setup view.\n" + e.getMessage());
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void recordingFeedbackButtonOnAction(ActionEvent actionEvent) {
    }
}