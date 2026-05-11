package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.ChannelData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SE_Goal2_ViewController {

    @FXML private TableView<ChannelData> channelTable;
    @FXML private TableColumn<ChannelData, String> ChannelColumn;
    @FXML private TableColumn<ChannelData, Number> LevelColumn;
    @FXML private ComboBox<String> AdjustChannelCombo;
    @FXML private ComboBox<String> StatusComboBox;
    @FXML private TextField levelTextField;
    @FXML private Label statusLabel;

    private final List<ChannelData> channelList = new ArrayList<>();

    private final String FILE_NAME = "MonitorLevelLog.bin";
    @FXML
    private Button saveLogsBtn;
    @FXML
    private Button applyBtn;
    @FXML
    private TableColumn StatusColumn;

    @FXML
    public void initialize() {
        ChannelColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        LevelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        AdjustChannelCombo.getItems().addAll("Kick", "Snare", "Vocal", "Guitar");
        StatusComboBox.getItems().addAll("Verified", "Unverified");

        channelTable.getItems().clear();
        levelTextField.clear();
        statusLabel.setText("");
    }

    @FXML
    public void HandleApplyAdjustmentButtonOnAction(ActionEvent event) {
        String channelName = AdjustChannelCombo.getValue();
        String levelText = levelTextField.getText();
        String status = StatusComboBox.getValue();

        if (channelName == null || levelText.isBlank() || status == null) {
            statusLabel.setText("Please fill all fields.");
            return;
        }

        double level;
        try {
            level = Double.parseDouble(levelText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid level");
            return;
        }

        ChannelData newChannel = new ChannelData(channelName, level, status);
        channelList.add(newChannel);

        channelTable.getItems().setAll(channelList);

        levelTextField.clear();
        statusLabel.setText("Added");
    }

    @FXML
    public void HandleSaveLogsButtonOnAction(ActionEvent event) {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(channelList);
            statusLabel.setText("Log Saved");

        } catch (IOException e) {
            statusLabel.setText("Save Failed");
            e.printStackTrace();
        }
    }


    @FXML
    public void HandleLoadLogsButtonOnAction(ActionEvent event) {
        try (ObjectInputStream ois =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            List<ChannelData> loadedList = (List<ChannelData>) ois.readObject();

            channelList.clear();
            channelList.addAll(loadedList);

            channelTable.getItems().setAll(channelList);

            statusLabel.setText("Loaded Successfully");

        } catch (FileNotFoundException e) {
            statusLabel.setText("No saved file found");
        } catch (IOException | ClassNotFoundException e) {
            statusLabel.setText("Load Failed");
            e.printStackTrace();
        }
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(
                    getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml")
            );

            Scene dashboardScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            currentStage.setScene(dashboardScene);
            currentStage.setTitle("Sound Engineer Dashboard");
            currentStage.show();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void handleClear(ActionEvent actionEvent) {
    }
}