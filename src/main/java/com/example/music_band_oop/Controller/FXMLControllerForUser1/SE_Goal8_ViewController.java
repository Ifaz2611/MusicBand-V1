package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.ShowRecording;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class SE_Goal8_ViewController {

    @FXML private TableView<ShowRecording> RecordingTableView;
    @FXML private TableColumn<ShowRecording, String> TitleCol;
    @FXML private TableColumn<ShowRecording, String> FeedbackNotesCol;
    @FXML private TextField TitleTextField;
    @FXML private TextField FeedbackandNotesTextField;
    @FXML private Label StatusLabel;

    private final List<ShowRecording> recordingList = new ArrayList<>();

    @FXML
    public void initialize() {
        TitleCol.setCellValueFactory(new PropertyValueFactory<>("showTitle"));
        FeedbackNotesCol.setCellValueFactory(new PropertyValueFactory<>("feedback"));
        refreshTable();
        RecordingTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
    private void refreshTable() {
        RecordingTableView.getItems().clear();
        RecordingTableView.getItems().addAll(recordingList);
    }
    @FXML
    public void SaveToTableButtonOnAction(ActionEvent event) {
        String title = TitleTextField.getText();
        String notes = FeedbackandNotesTextField.getText();

        if (title.isEmpty()) {
            StatusLabel.setText("Please enter a title.");
            return;
        }
        if (notes.isEmpty()) {
            StatusLabel.setText("Please enter feedback/notes.");
            return;
        }

        ShowRecording newRecording = new ShowRecording(title, notes);

        recordingList.add(newRecording);
        refreshTable();

        TitleTextField.clear();
        FeedbackandNotesTextField.clear();
        StatusLabel.setText("Added: " + title);
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml"));
            Scene dashboardScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(dashboardScene);
            currentStage.setTitle("Sound Engineer Dashboard");
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}