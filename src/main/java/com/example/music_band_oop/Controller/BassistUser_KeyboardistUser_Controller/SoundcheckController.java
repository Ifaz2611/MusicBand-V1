package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

public class SoundcheckController
{
    @javafx.fxml.FXML
    private Label sound_eng_label;
    @javafx.fxml.FXML
    private Label success_msg_label;
    @javafx.fxml.FXML
    private Label curr_status_label;
    @javafx.fxml.FXML
    private TextArea issue_text_area;
    @javafx.fxml.FXML
    private ComboBox<String> select_event_com;

    private String selectedEvent;
    private boolean equipmentReady = false;
    private boolean soundCheckDone = false;

    @javafx.fxml.FXML
    public void initialize() {
        select_event_com.getItems().addAll(
                "Summer Fest 2025 - June 15",
                "Jazz Night - June 22",
                "Rock Arena - July 5",
                "Blues Festival - July 12"
        );
    }

    @javafx.fxml.FXML
    public void select_event_comboBox(ActionEvent actionEvent) {
        selectedEvent = select_event_com.getValue();
        if (selectedEvent == null) return;

        // Add a damo name
        sound_eng_label.setText("Fahmida Islam (Sound Engineer)");
        sound_eng_label.setTextFill(Color.BLACK);

        equipmentReady = false;
        soundCheckDone = false;
        curr_status_label.setText("Equipment not ready");
        curr_status_label.setTextFill(Color.RED);
        success_msg_label.setText("");
        issue_text_area.clear();
    }

    @javafx.fxml.FXML
    public void confirm_equip_btn(ActionEvent actionEvent) {
        if (selectedEvent == null) {
            success_msg_label.setText("Please select an event first.");
            success_msg_label.setTextFill(Color.RED);
            return;
        }
        if (equipmentReady) {
            success_msg_label.setText("Equipment already confirmed for this event.");
            success_msg_label.setTextFill(Color.ORANGE);
            return;
        }
        equipmentReady = true;
        curr_status_label.setText("Equipment ready – pending sound check");
        curr_status_label.setTextFill(Color.ORANGE);
        success_msg_label.setText("Equipment confirmed ready. Sound Engineer notified.");
        success_msg_label.setTextFill(Color.GREEN);
    }

    @javafx.fxml.FXML
    public void confirm_sound_check_btn(ActionEvent actionEvent) {
        if (selectedEvent == null) {
            success_msg_label.setText("Please select an event first.");
            success_msg_label.setTextFill(Color.RED);
            return;
        }
        if (!equipmentReady) {
            success_msg_label.setText("Please confirm equipment ready first.");
            success_msg_label.setTextFill(Color.RED);
            return;
        }
        if (soundCheckDone) {
            success_msg_label.setText("Sound check already completed for this event.");
            success_msg_label.setTextFill(Color.ORANGE);
            return;
        }

        soundCheckDone = true;
        curr_status_label.setText("Sound check successful – ready for performance");
        curr_status_label.setTextFill(Color.GREEN);
        success_msg_label.setText("Sound check successful! All systems ready.");
        success_msg_label.setTextFill(Color.GREEN);

        if (!issue_text_area.getText().trim().isEmpty()) {
            success_msg_label.setText(success_msg_label.getText() + "\nIssues reported: " + issue_text_area.getText());
        }
    }


}