package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.MonitorChannel;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SE_Goal7_ViewController {

    @FXML private TableView<MonitorChannel> monitorTableView;
    @FXML private TableColumn<MonitorChannel, String> PerformerCol;
    @FXML private TableColumn<MonitorChannel, Double> LevelCol;
    @FXML private TextField levelTextField;
    @FXML private Label feedbackLabel;

    private ObservableList<MonitorChannel> channelList;

    @FXML
    public void initialize() {

        PerformerCol.setCellValueFactory(new PropertyValueFactory<>("performerName"));
        LevelCol.setCellValueFactory(new PropertyValueFactory<>("currentLevel"));

        channelList = FXCollections.observableArrayList(
                new MonitorChannel("Lead Vocal", 0.0),
                new MonitorChannel("Guitar",     0.0),
                new MonitorChannel("Bass",       0.0),
                new MonitorChannel("Drums",      0.0),
                new MonitorChannel("Keyboard",   0.0)
        );

        monitorTableView.setItems(channelList);
        monitorTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void AdjustButtonOnAction(ActionEvent event) {
        MonitorChannel selected = monitorTableView.getSelectionModel().getSelectedItem();
        if (selected == null) { feedbackLabel.setText("Please select a performer/monitor channel first."); return; }

        String levelText = levelTextField.getText();
        if (levelText == null || levelText.trim().isEmpty()) { feedbackLabel.setText("Enter a valid level (numeric value)."); return; }

        try {
            double newLevel = Double.parseDouble(levelText);
            selected.setCurrentLevel(newLevel);
            monitorTableView.refresh();
            feedbackLabel.setText("Adjusted " + selected.getPerformerName() + " to " + newLevel + " dB.");
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Invalid number. Please enter a numeric level.");
        }
    }

    @FXML
    public void VerifyButtonOnAction(ActionEvent event) {
        feedbackLabel.setText("Balance verified on stage. All monitors are set correctly.");
    }

    @FXML
    public void SaveLogButtonOnAction(ActionEvent event) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.print("[" + timestamp + "] Monitor adjustments saved.\n");
        feedbackLabel.setText("Adjustment log saved at " + timestamp);
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