package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class KeyGoal6IssueController
{
    @javafx.fxml.FXML
    private ComboBox<String> issueTypeCombo;
    @javafx.fxml.FXML
    private TableColumn<KeyboardIssue, String> colName;
    @javafx.fxml.FXML
    private TextField nameTextField;
    @javafx.fxml.FXML
    private TableColumn<KeyboardIssue, String> colDate;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableColumn<KeyboardIssue, String> colPriority;
    @javafx.fxml.FXML
    private ComboBox<String> priorityCombo;
    @javafx.fxml.FXML
    private TableView<KeyboardIssue> issueTable;
    @javafx.fxml.FXML
    private TextArea descriptionArea;
    @javafx.fxml.FXML
    private TableColumn<KeyboardIssue, String> colType;
    @javafx.fxml.FXML
    private TextField filterTextField;
    @javafx.fxml.FXML
    private TableColumn<KeyboardIssue, String> colDescription;
    @javafx.fxml.FXML
    private TableColumn<KeyboardIssue, String> colId;
    @javafx.fxml.FXML
    private TableColumn<KeyboardIssue, String> colStatus;

    private ArrayList<KeyboardIssue> issueList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        issueTypeCombo.getItems().addAll("Hardware", "Software", "Connectivity", "Other");
        priorityCombo.getItems().addAll("Low", "Medium", "High", "Critical");

        loadData();
        issueTable.getItems().addAll(issueList);

    }

    private void loadData() {
        issueList.add(new KeyboardIssue("1", "2025-06-01", "Hardware", "Keyboard X", "High", "Pending", "Key not working"));
        issueList.add(new KeyboardIssue("2", "2025-06-03", "Software", "Synth App", "Medium", "Resolved", "App crash"));
        issueList.add(new KeyboardIssue("3", "2025-04-01", "Hardware", "Keyboard Key A", "Medium", "In Progress", "Key A sticks intermittently"));
        issueList.add(new KeyboardIssue("4", "2025-04-08", "Hardware", "Sustain Pedal", "Medium", "Pending", "Pedal not sustaining notes"));
    }


    @javafx.fxml.FXML
    public void sortByDateBtn(ActionEvent actionEvent) {
        issueList.sort((a, b) -> a.getDate().compareTo(b.getDate()));

        issueTable.getItems().clear();
        issueTable.getItems().addAll(issueList);

        messageLabel.setText("Sorted by date.");
    }

    @javafx.fxml.FXML
    public void resetFilterButton(ActionEvent actionEvent) {
        issueTable.getItems().clear();
        issueTable.getItems().addAll(issueList);

        filterTextField.clear();

        messageLabel.setText("Reset done.");
    }

    @javafx.fxml.FXML
    public void filterButton(ActionEvent actionEvent) {
        String text = filterTextField.getText().toLowerCase();

        issueTable.getItems().clear();

        for (KeyboardIssue i : issueList) {
            if (i.getName().toLowerCase().contains(text)) {
                issueTable.getItems().add(i);
            }
        }

        messageLabel.setText("Filtered.");
    }

    @javafx.fxml.FXML
    public void submitBtn(ActionEvent actionEvent) {
        String id = String.valueOf(issueList.size() + 1);
        String date = LocalDate.now().toString();
        String type = issueTypeCombo.getValue();
        String name = nameTextField.getText();
        String priority = priorityCombo.getValue();
        String description = descriptionArea.getText();

        if (type == null || name.isEmpty() || priority == null || description.isEmpty()) {
            messageLabel.setText("Fill all fields.");
            return;
        }

        KeyboardIssue issue = new KeyboardIssue(
                id, date, type, name, priority, "Pending", description
        );

        issueList.add(issue);
        issueTable.getItems().add(issue);

        messageLabel.setText("Issue submitted.");

        // clear fields
        nameTextField.clear();
        descriptionArea.clear();
        issueTypeCombo.setValue(null);
        priorityCombo.setValue(null);
    }
}