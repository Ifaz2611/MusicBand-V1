package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;

public class KeyGoal2UploadController
{
    @javafx.fxml.FXML
    private TextField titleTextField;
    @javafx.fxml.FXML
    private ComboBox<String> typeComboBox;
    @javafx.fxml.FXML
    private TextArea descriptionTextArea;
    @javafx.fxml.FXML
    private ComboBox<String> eventComboBox;
    @javafx.fxml.FXML
    private Label messageLabel;

    private File selectedFile;
    @javafx.fxml.FXML
    private Label fileNameLabel;

    private ArrayList<RecordingFile> recordingList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        typeComboBox.getItems().addAll("Demo", "Practice", "Rehearsal", "Original Composition", "Cover", "Sound Check");
        eventComboBox.getItems().addAll("Summer Fest 2025", "Jazz Night", "Rock Arena", "Blues Festival");
    }

    @javafx.fxml.FXML
    public void uploadBtn(ActionEvent actionEvent) {
        String title = titleTextField.getText();
        String description = descriptionTextArea.getText();
        String type = typeComboBox.getValue();
        String event = eventComboBox.getValue();

        if (title.isEmpty() || description.isEmpty() || type == null || selectedFile == null) {
            messageLabel.setText("Please fill all required fields and choose file.");
            return;
        }

        // create object
        RecordingFile record = new RecordingFile(
                title,
                description,
                type,
                event,
                selectedFile.getName()
        );

        // store in list
        recordingList.add(record);

        messageLabel.setText("File uploaded and saved!");

        // clear fields
        titleTextField.clear();
        descriptionTextArea.clear();
        typeComboBox.setValue(null);
        eventComboBox.setValue(null);
        fileNameLabel.setText("No file chosen");
        selectedFile = null;
    }

    @javafx.fxml.FXML
    public void chooseFileBtn(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select File");

        selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            fileNameLabel.setText(selectedFile.getName());
            messageLabel.setText("File selected.");
        } else {
            messageLabel.setText("No file selected.");
        }
    }
}