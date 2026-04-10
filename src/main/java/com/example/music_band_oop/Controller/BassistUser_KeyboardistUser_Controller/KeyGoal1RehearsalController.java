package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class KeyGoal1RehearsalController
{
    @javafx.fxml.FXML
    private Label durationValueLabel;
    @javafx.fxml.FXML
    private TextArea equipmentTextArea;
    @javafx.fxml.FXML
    private TableColumn<KeyboardRehearsal, String> colTime;
    @javafx.fxml.FXML
    private TableColumn<KeyboardRehearsal, String> colVenue;
    @javafx.fxml.FXML
    private TextArea bandManagerNotesTextArea;
    @javafx.fxml.FXML
    private TableColumn<KeyboardRehearsal, String> colDuration;
    @javafx.fxml.FXML
    private TableView<KeyboardRehearsal> rehearsalTableView;
    @javafx.fxml.FXML
    private TableColumn<KeyboardRehearsal, String> colDate;
    @javafx.fxml.FXML
    private TableColumn<KeyboardRehearsal, String> colKeyboardNotes;
    @javafx.fxml.FXML
    private TextArea reasonTextArea;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableColumn<KeyboardRehearsal, String> colStatus;

    private ArrayList<KeyboardRehearsal> rehearsalList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colVenue.setCellValueFactory(new PropertyValueFactory<>("venue"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colKeyboardNotes.setCellValueFactory(new PropertyValueFactory<>("keyboardNotes"));

        loadData();
        rehearsalTableView.getItems().addAll(rehearsalList);

        // show details when row selected
        rehearsalTableView.setOnMouseClicked(e -> {
            KeyboardRehearsal r = rehearsalTableView.getSelectionModel().getSelectedItem();
            if (r != null) {
                durationValueLabel.setText(r.getDuration());
                bandManagerNotesTextArea.setText(r.getBandManagerNotes());
                equipmentTextArea.setText(r.getRequiredEquipment());
            }
        });
    }

    private void loadData() {
        rehearsalList.add(new KeyboardRehearsal("2025-06-10", "6:00 PM", "2h", "Studio A", "Pending", "Practice chords", "Be on time", "Keyboard, Stand"));
        rehearsalList.add(new KeyboardRehearsal("2025-06-15", "5:00 PM", "1.5h", "Main Hall", "Confirmed", "Focus on melody", "Good progress", "Keyboard, Amp"));
    }

    @javafx.fxml.FXML
    public void confirmBtn(ActionEvent actionEvent) {
        KeyboardRehearsal r = rehearsalTableView.getSelectionModel().getSelectedItem();

        if (r == null) {
            messageLabel.setText("Select a rehearsal first.");
            return;
        }

        r.setStatus("Confirmed");

        rehearsalTableView.refresh();

        messageLabel.setText("Rehearsal confirmed.");
    }

    @javafx.fxml.FXML
    public void rescheduleBtn(ActionEvent actionEvent) {
        KeyboardRehearsal r = rehearsalTableView.getSelectionModel().getSelectedItem();

        if (r == null) {
            messageLabel.setText("Select a rehearsal first.");
            return;
        }

        String reason = reasonTextArea.getText();

        if (reason.isEmpty()) {
            messageLabel.setText("Enter reason for reschedule.");
            return;
        }

        r.setStatus("Rescheduled");

        rehearsalTableView.refresh();

        messageLabel.setText("Rehearsal rescheduled.");
    }
}