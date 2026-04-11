package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class KeyboardistDashboardController
{
    @javafx.fxml.FXML
    private AnchorPane centerPane;

    @javafx.fxml.FXML
    public void initialize() {
        loadFXML("welcome_key.fxml");
    }

    @javafx.fxml.FXML
    public void songArrangementsBtn(ActionEvent actionEvent) {
        loadFXML("key_goal3_arrangements.fxml");
    }

    @javafx.fxml.FXML
    public void soundCheckBtn(ActionEvent actionEvent) {
        loadFXML("key_goal2_soundcheck.fxml");
    }

    @javafx.fxml.FXML
    public void rehearsalScheduleBtn(ActionEvent actionEvent) {
        loadFXML("key_goal1_rehearsal.fxml");
    }

    @javafx.fxml.FXML
    public void availabilityBtn(ActionEvent actionEvent) {
        loadFXML("key_goal7_availability.fxml");
    }

    @javafx.fxml.FXML
    public void reportIssueBtn(ActionEvent actionEvent) {
        loadFXML("key_goal6_issue.fxml");
    }

    @javafx.fxml.FXML
    public void uploadRecordingBtn(ActionEvent actionEvent) {
        loadFXML("key_goal5_upload.fxml");
    }

    @javafx.fxml.FXML
    public void feedbackBtn(ActionEvent actionEvent) {
        loadFXML("key_goal8_feedback.fxml");
    }

    @javafx.fxml.FXML
    public void soundSetupBtn(ActionEvent actionEvent) {
        loadFXML("key_goal4_soundsetup.fxml");
    }

    private void loadFXML(String fxmlFile) {
        try {
            AnchorPane pane = FXMLLoader.load(
                    getClass().getResource("/com/example/music_band_oop/BassistUser_KeyboardistUser_FXML/" + fxmlFile)
            );

            centerPane.getChildren().setAll(pane);

            pane.prefWidthProperty().bind(centerPane.widthProperty());
            pane.prefHeightProperty().bind(centerPane.heightProperty());

        } catch (IOException e) {
            System.err.println("Could not load " + fxmlFile);
            e.printStackTrace();
        }
    }
}