package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.IssueLog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SE_Goal4_ViewController implements Initializable {

    @FXML private ComboBox<String> ActionComboBox;
    @FXML private ComboBox<String> ProblemTypeComboBox;
    @FXML private TextField AffectedChannelTextField;
    @FXML private TableView<IssueLog> logTableView;
    @FXML private TableColumn<IssueLog, String> timestampCol;
    @FXML private TableColumn<IssueLog, String> channelCol;
    @FXML private TableColumn<IssueLog, String> problemCol;
    @FXML private TableColumn<IssueLog, String> actionCol;
    @FXML private TableColumn<IssueLog, String> statusCol;

    private ObservableList<IssueLog> logList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ProblemTypeComboBox.setItems(FXCollections.observableArrayList(
                "Feedback", "Noise", "Dead mic", "Distortion"));

        ActionComboBox.setItems(FXCollections.observableArrayList(
                "Adjust gain/EQ", "Replace cable", "Swap mic", "Mute frequency"));

        timestampCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        channelCol.setCellValueFactory(new PropertyValueFactory<>("channel"));
        problemCol.setCellValueFactory(new PropertyValueFactory<>("problem"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        logTableView.setItems(logList);
    }

    private String getChannel() {
        return AffectedChannelTextField.getText().trim();
    }
    private boolean isChannelEmpty() {
        if (getChannel().isEmpty()) {
            showAlert("Missing", "Enter channel first.");
            return true;
        }
        return false;
    }
    @FXML
    public void detectProblemOnAction(ActionEvent event) {
        String channel = getChannel();
        String problem = ProblemTypeComboBox.getValue();

        if (channel.isEmpty() || problem == null) {
            showAlert("Missing Data", "Enter channel and select problem.");
            return;
        }

        showAlert("Detected", problem + " on " + channel);
    }
    @FXML
    public void adjustSettingsOnAction(ActionEvent event) {
        if (isChannelEmpty()) return;
        showAlert("Adjusted", "Settings adjusted for " + getChannel());
    }

    @FXML
    public void replaceEquipmentOnAction(ActionEvent event) {
        if (isChannelEmpty()) return;
        showAlert("Replaced", "Equipment replaced on " + getChannel());
    }

    @FXML
    public void verifyResolutionOnAction(ActionEvent event) {
        if (isChannelEmpty()) return;
        showAlert("Verified", "Problem resolved on " + getChannel());
    }
    @FXML
    public void saveLogbuttonOnAction(ActionEvent event) {
        String channel = getChannel();
        String problem = ProblemTypeComboBox.getValue();

        if (channel.isEmpty() || problem == null) {
            showAlert("Missing", "Cannot save log – need channel and problem.");
            return;
        }

        String action = ActionComboBox.getValue() != null ? ActionComboBox.getValue() : "none";

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        logList.add(new IssueLog(timestamp, channel, problem, action, "Resolved"));
        AffectedChannelTextField.clear();
        ProblemTypeComboBox.getSelectionModel().clearSelection();
        ActionComboBox.getSelectionModel().clearSelection();

        showAlert("Saved", "Issue log saved.");
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