package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class FeedbackController
{
    @javafx.fxml.FXML
    private Label titleLabel;
    @javafx.fxml.FXML
    private TableView<Feedback> feedbackTableView;
    @javafx.fxml.FXML
    private TableColumn<Feedback, String> colDate;
    @javafx.fxml.FXML
    private TextArea feedbackDetailsTextArea;
    @javafx.fxml.FXML
    private TableColumn<Feedback, String> colEvent;
    @javafx.fxml.FXML
    private TableColumn<Feedback, String> colComments;
    @javafx.fxml.FXML
    private TableColumn<Feedback, String> colReadCheck;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableColumn<Feedback, String> colStatus;

    @javafx.fxml.FXML
    public void initialize() {

        colEvent.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colComments.setCellValueFactory(new PropertyValueFactory<>("comments"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colReadCheck.setCellValueFactory(new PropertyValueFactory<>("status")); // simple

        // add dummy data
        feedbackTableView.getItems().addAll(
                new Feedback("Summer Fest 2025", "2025-06-16", "Great bass groove! Solid timing on 'Another One Bites the Dust'.", "Unread", false),
                new Feedback("Jazz Night", "2025-06-23", "Walking bass line was excellent. Keep working on syncopation.", "Unread", false),
                new Feedback("Rock Arena", "2025-07-06", "Awesome energy! The fuzz effect on 'Seven Nation Army' was perfect.", "Read", true)
        );

        // show details when row is selected
        feedbackTableView.setOnMouseClicked(e -> {
            Feedback selected = feedbackTableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                feedbackDetailsTextArea.setText(selected.getComments());
            }
        });

    }

    @javafx.fxml.FXML
    public void markReadBtn(ActionEvent actionEvent) {
        Feedback selected = feedbackTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            messageLabel.setText("Please select feedback.");
            return;
        }

        if (selected.getStatus().equals("Read")) {
            messageLabel.setText("Already marked as read.");
            return;
        }

        // update values
        selected.setStatus("Read");
        selected.setRead(true);

        feedbackTableView.refresh();

        messageLabel.setText("Marked as read.");
    }
}