package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.IssueLog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ArrayList;

public class SE_Goal4_ViewController{

    @FXML private ComboBox<String> ActionComboBox;
    @FXML private ComboBox<String> ProblemTypeComboBox;
    @FXML private TextField AffectedChannelTextField;
    @FXML private TableView<IssueLog> logTableView;
    @FXML private TableColumn<IssueLog, String> channelCol;
    @FXML private TableColumn<IssueLog, String> problemCol;
    @FXML private TableColumn<IssueLog, String> actionCol;

    private ArrayList<IssueLog> logList = new ArrayList<>();

    @FXML
    public void initialize() {
        ProblemTypeComboBox.getItems().addAll("Feedback", "Noise", "Dead mic", "Distortion");
        ActionComboBox.getItems().addAll("Adjust gain/EQ", "Replace cable", "Swap mic", "Mute frequency");

        channelCol.setCellValueFactory(new PropertyValueFactory<>("channel"));
        problemCol.setCellValueFactory(new PropertyValueFactory<>("problem"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));
    }

    @FXML
    public void saveLogbuttonOnAction(ActionEvent event) {
        String channel = AffectedChannelTextField.getText().trim();
        String problem = ProblemTypeComboBox.getValue();

        if (channel.isEmpty() || problem == null) {
            return;
        }

        String action = ActionComboBox.getValue() != null ? ActionComboBox.getValue() : "none";
        logList.add(new IssueLog("", channel, problem, action, "Resolved"));
        logTableView.getItems().clear();
        logTableView.getItems().addAll(logList);
        AffectedChannelTextField.clear();
        ProblemTypeComboBox.getSelectionModel().clearSelection();
        ActionComboBox.getSelectionModel().clearSelection();


        //adding bin file
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sound Engineer Dashboard");
        } catch (Exception e) {
        }
    }
}