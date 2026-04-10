package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class IssueController
{
    @javafx.fxml.FXML
    private Label titleLabel;
    @javafx.fxml.FXML
    private TextField equipmentTextField;
    @javafx.fxml.FXML
    private TextArea descriptionTextArea;
    @javafx.fxml.FXML
    private TableColumn<Issues, String> colDate;
    @javafx.fxml.FXML
    private TextField filter_input;
    @javafx.fxml.FXML
    private Label issueHistoryLabel;
    @javafx.fxml.FXML
    private TableColumn<Issues, String> colEvent;
    @javafx.fxml.FXML
    private Label eventLabel;
    @javafx.fxml.FXML
    private ComboBox<String> eventComboBox;
    @javafx.fxml.FXML
    private Label equipmentLabel;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableView<Issues> issueTableView;
    @javafx.fxml.FXML
    private TableColumn<Issues, String> colDescription;
    @javafx.fxml.FXML
    private TableColumn<Issues, String> colId;
    @javafx.fxml.FXML
    private TableColumn<Issues, String> colEquipment;
    @javafx.fxml.FXML
    private Label descriptionLabel;
    @javafx.fxml.FXML
    private TableColumn<Issues, String> colStatus;

    private ArrayList<Issues> issueList = new ArrayList<>();
    private int idCounter = 1;

    @javafx.fxml.FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("issueId"));
        colEvent.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colEquipment.setCellValueFactory(new PropertyValueFactory<>("equipmentName"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

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
        String equipment = equipmentTextField.getText();
        String description = descriptionTextArea.getText();

        if (event == null || equipment.isEmpty() || description.isEmpty()) {
            messageLabel.setText("Please fill all fields.");
            return;
        }

        // create demo data
        String id = "ISSUE-" + idCounter++;
        String status = "Open";
        String date = LocalDate.now().toString();

        Issues issue = new Issues(id, event, equipment, status, date, description);

        // add to list
        issueList.add(issue);
        issueTableView.getItems().add(issue);

        messageLabel.setText("Issue submitted!");

        // clear fields
        eventComboBox.setValue(null);
        equipmentTextField.clear();
        descriptionTextArea.clear();
    }

    @javafx.fxml.FXML
    public void filter_btn(ActionEvent actionEvent) {
        String keyword = filter_input.getText().toLowerCase();

        if (keyword.isEmpty()) {
            messageLabel.setText("Enter something to filter.");
            return;
        }

        issueTableView.getItems().clear();

        for (Issues i : issueList) {
            if (i.getEventName().toLowerCase().contains(keyword) ||
                    i.getEquipmentName().toLowerCase().contains(keyword)) {

                issueTableView.getItems().add(i);
            }
        }

        messageLabel.setText("Filtered result shown.");
    }
}