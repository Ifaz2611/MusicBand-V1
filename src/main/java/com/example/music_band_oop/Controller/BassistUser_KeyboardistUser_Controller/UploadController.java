package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;

public class UploadController
{
    @javafx.fxml.FXML
    private Label notesLabel;
    @javafx.fxml.FXML
    private Label titleLabel;
    @javafx.fxml.FXML
    private Label uploadHistoryLabel;
    @javafx.fxml.FXML
    private TableColumn<Recording, String> colDate;
    @javafx.fxml.FXML
    private TableColumn<Recording, String> colEvent;
    @javafx.fxml.FXML
    private Label eventLabel;
    @javafx.fxml.FXML
    private ComboBox<String> eventComboBox;
    @javafx.fxml.FXML
    private Label fileLabel;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TextField titleTextField;
    @javafx.fxml.FXML
    private TextArea notesTextArea;
    @javafx.fxml.FXML
    private Label filePathLabel;
    @javafx.fxml.FXML
    private TableView<Recording> uploadTableView;
    @javafx.fxml.FXML
    private TableColumn<Recording, String> colNotes;
    @javafx.fxml.FXML
    private Label titleFieldLabel;
    @javafx.fxml.FXML
    private TableColumn<Recording, String> colTitle;

    private File selectedFile;

    @javafx.fxml.FXML
    public void initialize() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colEvent.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));

        eventComboBox.getItems().addAll(
                "Summer Fest 2025",
                "Jazz Night",
                "Rock Arena",
                "Blues Festival"
        );
    }

    @javafx.fxml.FXML
    public void uploadBtn(ActionEvent actionEvent) {
        String title = titleTextField.getText();
        String event = eventComboBox.getValue();
        String notes = notesTextArea.getText();

        if (title.isEmpty() || event == null || selectedFile == null) {
            messageLabel.setText("Please fill all fields and choose file.");
            return;
        }

        String date = LocalDate.now().toString();

        Recording recording = new Recording(title, event, date, notes);

        uploadTableView.getItems().add(recording);

        messageLabel.setText("Upload successful!");

        // Clear fields
        titleTextField.clear();
        notesTextArea.clear();
        eventComboBox.setValue(null);
        filePathLabel.setText("");
        selectedFile = null;
    }

    @javafx.fxml.FXML
    public void chooseFileBtn(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Recording File");

        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            filePathLabel.setText(selectedFile.getAbsolutePath());
            messageLabel.setText("File selected.");
        } else {
            messageLabel.setText("No file selected.");
        }
    }
}