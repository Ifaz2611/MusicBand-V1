package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.ChannelData;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class SE_Goal2_ViewController {

    @FXML private TableView<ChannelData> channelTable;
    @FXML private TableColumn<ChannelData, String> ChannelColumn;
    @FXML private TableColumn<ChannelData, Number> LevelColumn;
    @FXML private TableColumn<ChannelData, String> StatusCloumn;
    @FXML private ComboBox<String> AdjustChannelCombo;
    @FXML private TextField levelTextField;
    @FXML private Label issueLabel, statusLabel;
    @FXML private Button saveLogsBtn, applyBtn;

    private final ObservableList<ChannelData> channelList = FXCollections.observableArrayList(
            new ChannelData("Kick",   -3.0, "OK"),
            new ChannelData("Snare",  -2.5, "OK"),
            new ChannelData("Vocal",   0.5, "Distortion"),
            new ChannelData("Guitar", -6.0, "OK")
    );

    @FXML
    public void initialize() {
        ChannelColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        LevelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        StatusCloumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        channelTable.setItems(channelList);

        AdjustChannelCombo.setItems(FXCollections.observableArrayList("Kick", "Snare", "Vocal", "Guitar"));
        AdjustChannelCombo.getSelectionModel().selectFirst();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            channelList.forEach(ch -> ch.setLevel(Math.round((-10 + Math.random() * 15) * 10) / 10.0));
            detectIssues();
            channelTable.refresh();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void detectIssues() {
        boolean distortion = channelList.stream().anyMatch(ch -> ch.getLevel() > 0);
        issueLabel.setText(distortion ? "Distortion detected" : "No issues");
        issueLabel.setStyle("-fx-text-fill: " + (distortion ? "red" : "green") + ";");
    }
    @FXML
    public void HandleApplyAdjustmentButtonOnAction(ActionEvent event) {
        String selected = AdjustChannelCombo.getValue();
        String input = levelTextField.getText();

        if (selected == null || input.isBlank()) {
            statusLabel.setText("Select a channel and enter a level.");
            return;
        }

        try {
            double newLevel = Double.parseDouble(input);
            channelList.stream()
                    .filter(ch -> ch.getName().equals(selected))
                    .findFirst()
                    .ifPresent(ch -> ch.setLevel(newLevel));
            statusLabel.setText("Adjusted " + selected + " to " + newLevel + " dB");
            channelTable.refresh();
            detectIssues();
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid number. Use e.g. -5.0 or 2.5");
        }
    }

    @FXML
    public void HandleSaveLogsButtonOnAction(ActionEvent event) {
//        try (PrintWriter out = new PrintWriter(new FileWriter("sound_logs.txt", true))) {
//            out.println(LocalDateTime.now());
//            channelList.forEach(ch -> out.println(ch.getName() + " | " + ch.getLevel() + " dB | " + ch.getStatus()));
//            out.println("-------------------");
//            statusLabel.setText("Logs saved to sound_logs.txt");
//        } catch (IOException e) {
//            statusLabel.setText("Error saving logs.");
//        }
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sound Engineer Dashboard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}