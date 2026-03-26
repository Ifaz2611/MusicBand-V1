package com.example.music_band_oop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EventCoordinatorDashbroadController
{
    @javafx.fxml.FXML
    private Label EventCordinatorDashboradFxId;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void communicationButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void vendorManagementButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void eventLogisticsButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logoutButtonOnAction(ActionEvent actionEvent) {
        try {
            // Load the login FXML
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/login.fxml"));

            // Get the current stage
            Stage stage = (Stage) EventCordinatorDashboradFxId.getScene().getWindow();

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
    public void entryFeedbackButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void createEventButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void eventClosingButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void eventDayButtonOnAction(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void budgetManagementButtonOnAction(ActionEvent actionEvent) {
    }
}