package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class RehearsalController
{
    @javafx.fxml.FXML
    private TableColumn<Rehearsal, String> time_column;
    @javafx.fxml.FXML
    private TableColumn<Rehearsal, String> status_column;
    @javafx.fxml.FXML
    private TableColumn<Rehearsal, String> date_column;
    @javafx.fxml.FXML
    private TableView<Rehearsal> rehearsal_schedule_tableView;
    @javafx.fxml.FXML
    private TableColumn<Rehearsal, String> vanue_column;
    private ArrayList<Rehearsal> rehearsalList = new ArrayList<>();
    @javafx.fxml.FXML
    private Label message_label;

    @javafx.fxml.FXML
    public void initialize() {
        date_column.setCellValueFactory(new PropertyValueFactory<>("date"));
        time_column.setCellValueFactory(new PropertyValueFactory<>("time"));
        vanue_column.setCellValueFactory(new PropertyValueFactory<>("venue"));
        status_column.setCellValueFactory(new PropertyValueFactory<>("status"));

        loadSampleData();

        rehearsal_schedule_tableView.getItems().addAll(rehearsalList);
    }

    private void loadSampleData() {
        // Add rehearsals using constructor
        rehearsalList.add(new Rehearsal("2025-04-15", "6:00 PM", "Studio A", "Pending"));
        rehearsalList.add(new Rehearsal("2025-04-18", "4:00 PM", "Main Hall", "Confirmed"));
        rehearsalList.add(new Rehearsal("2025-04-22", "7:00 PM", "Studio B", "Pending"));
        rehearsalList.add(new Rehearsal("2025-04-25", "5:30 PM", "Studio A", "Pending"));
        rehearsalList.add(new Rehearsal("2025-04-28", "8:00 PM", "Outdoor Stage", "Confirmed"));
    }




    @javafx.fxml.FXML
    public void confirmSchdule_btn(ActionEvent actionEvent) {
        Rehearsal selected = rehearsal_schedule_tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            message_label.setText("Please select a rehearsal first.");
            return;
        }

        if (selected.getStatus().equals("Confirmed")) {
            message_label.setText("This rehearsal is already confirmed.");
            return;
        }

        selected.setStatus("Confirmed");

        rehearsal_schedule_tableView.refresh();

        message_label.setText("Confirmed: " + selected.getDate() + " at " + selected.getVenue());
    }

    @javafx.fxml.FXML
    public void cancel_scedule_btn(ActionEvent actionEvent) {
        Rehearsal selected = rehearsal_schedule_tableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            message_label.setText("Please select a rehearsal first.");
            return;
        }

        if (selected.getStatus().equals("Cancelled")) {
            message_label.setText("This rehearsal is already cancelled.");
            return;
        }

        selected.setStatus("Cancelled");

        rehearsal_schedule_tableView.refresh();

        message_label.setText("Cancelled: " + selected.getDate() + " at " + selected.getVenue());
    }
}