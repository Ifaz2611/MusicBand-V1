package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class ConfirmController
{
    @javafx.fxml.FXML
    private TableColumn<ConfirmationRecord, String> colConfirmation;
    @javafx.fxml.FXML
    private Label titleLabel;
    @javafx.fxml.FXML
    private TableColumn<ConfirmationRecord, String> colDate;
    @javafx.fxml.FXML
    private TableColumn<ConfirmationRecord, String> colEvent;
    @javafx.fxml.FXML
    private Label eventLabel;
    @javafx.fxml.FXML
    private ComboBox<String> eventComboBox;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private Label venueLabel;
    @javafx.fxml.FXML
    private RadioButton tentativeRadio;
    @javafx.fxml.FXML
    private Label dateLabel;
    @javafx.fxml.FXML
    private TextArea notesTextArea;
    @javafx.fxml.FXML
    private Label venueValueLabel;
    @javafx.fxml.FXML
    private Label timeValueLabel;
    @javafx.fxml.FXML
    private TableColumn<ConfirmationRecord, String> colNotes;
    @javafx.fxml.FXML
    private Label dateValueLabel;
    @javafx.fxml.FXML
    private Label historyLabel;
    @javafx.fxml.FXML
    private RadioButton confirmRadio;
    @javafx.fxml.FXML
    private RadioButton cannotRadio;
    @javafx.fxml.FXML
    private Label timeLabel;
    @javafx.fxml.FXML
    private TableView<ConfirmationRecord> historyTableView;

    private ToggleGroup confirmationGroup;

    @javafx.fxml.FXML
    public void initialize() {

        colEvent.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colConfirmation.setCellValueFactory(new PropertyValueFactory<>("confirmation"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        confirmationGroup = new ToggleGroup();
        confirmRadio.setToggleGroup(confirmationGroup);
        cannotRadio.setToggleGroup(confirmationGroup);
        tentativeRadio.setToggleGroup(confirmationGroup);
        confirmRadio.setSelected(true);  // default

        eventComboBox.getItems().addAll(
                "Summer Fest 2025",
                "Jazz Night",
                "Rock Arena",
                "Blues Festival"
        );
    }

    @javafx.fxml.FXML
    public void submitBtn(ActionEvent actionEvent) {
        String event = eventComboBox.getValue();
        String notes = notesTextArea.getText();

        if (event == null) {
            messageLabel.setText("Please select an event.");
            return;
        }

        // get selected radio button
        RadioButton selectedRadio = (RadioButton) confirmationGroup.getSelectedToggle();
        String confirmation = selectedRadio.getText();

        // use selected event date (or current date)
        String date = dateValueLabel.getText();
        if (date.isEmpty()) {
            date = LocalDate.now().toString();
        }

        // create object
        ConfirmationRecord record = new ConfirmationRecord(event, date, confirmation, notes);

        // add to table
        historyTableView.getItems().add(record);

        messageLabel.setText("Submitted successfully!");

        // clear inputs
        eventComboBox.setValue(null);
        notesTextArea.clear();
    }

    @javafx.fxml.FXML
    public void selected_eventComboBox(ActionEvent actionEvent) {

        String event = eventComboBox.getValue();

        if (event == null) return;

        // adding dummy data
        if (event.equals("Summer Fest 2025")) {
            venueValueLabel.setText("Outdoor Stage");
            dateValueLabel.setText("2025-06-10");
            timeValueLabel.setText("6:00 PM");
        }
        else if (event.equals("Jazz Night")) {
            venueValueLabel.setText("Main Hall");
            dateValueLabel.setText("2025-06-15");
            timeValueLabel.setText("7:00 PM");
        }
        else if (event.equals("Rock Arena")) {
            venueValueLabel.setText("Arena Stage");
            dateValueLabel.setText("2025-06-20");
            timeValueLabel.setText("8:00 PM");
        }
        else if (event.equals("Blues Festival")) {
            venueValueLabel.setText("City Park");
            dateValueLabel.setText("2025-06-25");
            timeValueLabel.setText("5:30 PM");
        }
    }
}