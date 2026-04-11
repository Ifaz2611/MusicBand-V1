package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class KeyGoal8FeedbackController
{
    @javafx.fxml.FXML
    private TextArea timingArea;
    @javafx.fxml.FXML
    private TextArea soundArea;
    @javafx.fxml.FXML
    private TableColumn<KeyboardFeedback, String> colTimestamp;
    @javafx.fxml.FXML
    private TextField filterTextField;
    @javafx.fxml.FXML
    private TableColumn<KeyboardFeedback, String> colDate;
    @javafx.fxml.FXML
    private TableColumn<KeyboardFeedback, String> colEvent;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableView<KeyboardFeedback> feedbackTable;
    @javafx.fxml.FXML
    private TableColumn<KeyboardFeedback, String> colStatus;
    @javafx.fxml.FXML
    private TextArea stageArea;

    private KeyboardFeedback selectedFeedback = null;
    private ArrayList<KeyboardFeedback> feedbackList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        colEvent.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colTimestamp.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        loadData();
        feedbackTable.getItems().addAll(feedbackList);

        feedbackTable.setOnMouseClicked(e -> {
            selectedFeedback = feedbackTable.getSelectionModel().getSelectedItem();

            if (selectedFeedback != null) {
                timingArea.setText(selectedFeedback.getTimingComments());
                soundArea.setText(selectedFeedback.getSoundComments());
                stageArea.setText(selectedFeedback.getStageComments());
            }
        });
    }

    private void loadData() {
        feedbackList.add(new KeyboardFeedback("Summer Fest", "2025-04-10", "Pending", "10:00 AM", "Good timing", "Clear sound", "Nice stage presence"));
        feedbackList.add(new KeyboardFeedback("Jazz Night", "2025-04-15", "Acknowledged", "8:30 PM", "Improve tempo", "Low volume", "Stay confident"));
    }

    @javafx.fxml.FXML
    public void acknowledgeBtn(ActionEvent actionEvent) {
        if (selectedFeedback == null) {
            messageLabel.setText("Select feedback first.");
            return;
        }

        selectedFeedback.setStatus("Acknowledged");

        feedbackTable.refresh();

        messageLabel.setText("Feedback acknowledged.");
    }

    @javafx.fxml.FXML
    public void resetFilterButton(ActionEvent actionEvent) {
        feedbackTable.getItems().clear();
        feedbackTable.getItems().addAll(feedbackList);

        filterTextField.clear();

        messageLabel.setText("Reset done.");
    }

    @javafx.fxml.FXML
    public void filterButton(ActionEvent actionEvent) {
        String text = filterTextField.getText().toLowerCase();

        feedbackTable.getItems().clear();

        for (KeyboardFeedback f : feedbackList) {
            if (f.getEventName().toLowerCase().contains(text)) {
                feedbackTable.getItems().add(f);
            }
        }

        messageLabel.setText("Filtered.");
    }
}