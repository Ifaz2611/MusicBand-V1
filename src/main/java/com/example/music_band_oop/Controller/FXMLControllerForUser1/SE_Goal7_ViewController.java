package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.MonitorChannel;
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

public class SE_Goal7_ViewController {

    @FXML private TableView<MonitorChannel> MonitorTableView;
    @FXML private TableColumn<MonitorChannel, String> MonitorChannelCol;
    @FXML private TableColumn<MonitorChannel, Double> CurrentLevelCol;

    @FXML private TextField MonitorChannelTextField;
    @FXML private TextField CurrentLevelTextField;
    @FXML private Label feedbackLabel;

    private final List<MonitorChannel> channelList = new ArrayList<>();

    @FXML
    public void initialize() {
        MonitorChannelCol.setCellValueFactory(new PropertyValueFactory<>("performerName"));
        CurrentLevelCol.setCellValueFactory(new PropertyValueFactory<>("currentLevel"));
        refreshTable();

        MonitorTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }


    private void refreshTable() {
        MonitorTableView.getItems().clear();
        MonitorTableView.getItems().addAll(channelList);
    }

    @FXML
    public void AddToTheTableButtonOnAction(ActionEvent event) {
        String channelName = MonitorChannelTextField.getText();
        String levelText = CurrentLevelTextField.getText();

        if (channelName.isEmpty()) {
            feedbackLabel.setText("Please enter a monitor channel name.");
            return;
        }
        if (levelText.isEmpty()) {
            feedbackLabel.setText("Please enter a current level (dB).");
            return;
        }
        double level;
        try {
            level = Double.parseDouble(levelText);
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Invalid level – enter a number");
            return;
        }

        MonitorChannel newChannel = new MonitorChannel(channelName, level);
        channelList.add(newChannel);
        refreshTable();

        MonitorChannelTextField.clear();
        CurrentLevelTextField.clear();
        feedbackLabel.setText("Added");
    }

    @FXML
    public void SaveLogButtonOnAction(ActionEvent event) {
        // Placeholder – implement later if needed
        //deal with it later
        feedbackLabel.setText("Save Log – not implemented yet.");
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

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}