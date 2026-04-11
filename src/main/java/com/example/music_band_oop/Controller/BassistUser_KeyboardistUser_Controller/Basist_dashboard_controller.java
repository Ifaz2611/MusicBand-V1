package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class Basist_dashboard_controller
{
    @javafx.fxml.FXML
    private AnchorPane main_anchorpann;

    @javafx.fxml.FXML
    public void initialize() {
        loadFXML("welcome.fxml");
    }

    @javafx.fxml.FXML
    public void leave_req_btn(ActionEvent actionEvent) {
        loadFXML("goal8_leaverequest.fxml");
    }

    @javafx.fxml.FXML
    public void event_checklist_btn(ActionEvent actionEvent) {
        loadFXML("goal3_setlist.fxml");
    }

    @javafx.fxml.FXML
    public void rehersal_schedule_btn(ActionEvent actionEvent) {
        loadFXML("goal1_rehearsal.fxml");
    }

    @javafx.fxml.FXML
    public void my_feedback_btn(ActionEvent actionEvent) {
        loadFXML("goal7_feedback.fxml");
    }

    @javafx.fxml.FXML
    public void report_issue_btn(ActionEvent actionEvent) {
        loadFXML("goal5_issue.fxml");
    }

    @javafx.fxml.FXML
    public void sound_check_btn(ActionEvent actionEvent) {
        loadFXML("goal2_soundcheck.fxml");
    }

    @javafx.fxml.FXML
    public void confirm_participation_btn(ActionEvent actionEvent) {
        loadFXML("goal6_confirm.fxml");
    }

    @javafx.fxml.FXML
    public void upload_recording_btn(ActionEvent actionEvent) {
        loadFXML("goal4_upload.fxml");
    }


    private void loadFXML(String fxmlFile) {
        try {
            AnchorPane pane = FXMLLoader.load(
                    getClass().getResource("/com/example/music_band_oop/BassistUser_KeyboardistUser_FXML/" + fxmlFile)
            );

            main_anchorpann.getChildren().setAll(pane);

            pane.prefWidthProperty().bind(main_anchorpann.widthProperty());
            pane.prefHeightProperty().bind(main_anchorpann.heightProperty());

        } catch (IOException e) {
            System.err.println("Could not load " + fxmlFile);
            e.printStackTrace();
        }
    }
}