package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.ShowRecording;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SE_Goal8_ViewController {

    @FXML private TableView<ShowRecording> RecordingTableView;
    @FXML private TableColumn<ShowRecording, String> ShowTitleCol;
    @FXML private TableColumn<ShowRecording, String> DateCol;
    @FXML private TextField FeedbackTextField;
    @FXML private Label StatusLabel;

    private ObservableList<ShowRecording> recordingList;

    @FXML
    public void initialize() {
        ShowTitleCol.setCellValueFactory(new PropertyValueFactory<>("showTitle"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        recordingList = FXCollections.observableArrayList(
                new ShowRecording("Summer Concert 2025", "2025-06-15", "recordings/summer_concert.mp3", ""),
                new ShowRecording("Jazz Night",          "2025-07-20", "recordings/jazz_night.mp3",     ""),
                new ShowRecording("Rock Festival",       "2025-08-10", "recordings/rock_festival.mp3",  "")
        );

        RecordingTableView.setItems(recordingList);
        RecordingTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private ShowRecording getSelected() {
        return RecordingTableView.getSelectionModel().getSelectedItem();
    }

    @FXML
    public void PlayRecordingButtonOnAction(ActionEvent event) {
        ShowRecording selected = getSelected();
        if (selected == null) { StatusLabel.setText("Please select a recorded show first."); return; }
        StatusLabel.setText("Playing: " + selected.getShowTitle() + " from " + selected.getFilePath());
    }

    @FXML
    public void SaveFeedbackButtonOnAction(ActionEvent event) {
        ShowRecording selected = getSelected();
        if (selected == null) { StatusLabel.setText("Please select a show to save feedback for."); return; }

        String feedback = FeedbackTextField.getText();
        if (feedback == null || feedback.trim().isEmpty()) { StatusLabel.setText("Please write some feedback before saving."); return; }

        selected.setFeedback(feedback);
        StatusLabel.setText("Feedback saved for " + selected.getShowTitle() + ": " + feedback);
        FeedbackTextField.clear();
    }

    @FXML
    public void ShareFeedbackButtonOnAction(ActionEvent event) {
        ShowRecording selected = getSelected();
        if (selected == null) { StatusLabel.setText("Please select a show to share feedback for."); return; }

        String feedback = selected.getFeedback();
        if (feedback == null || feedback.isEmpty()) { StatusLabel.setText("No feedback saved yet. Write and save feedback first."); return; }

        StatusLabel.setText("Feedback shared with team for " + selected.getShowTitle() + ": " + feedback);
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sound Engineer Dashboard");
        } catch (Exception e) {
            showAlert("Error", "Failed to load dashboard.");
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}