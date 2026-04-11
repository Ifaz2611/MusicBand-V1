package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.ChannelData;
import com.example.music_band_oop.Controller.nonuser.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SE_Goal2_ViewController {

    @FXML private TableView<ChannelData> channelTable;
    @FXML private TableColumn<ChannelData, String> ChannelColumn;
    @FXML private TableColumn<ChannelData, Number> LevelColumn;
    @FXML private TableColumn<ChannelData, String> StatusCloumn;
    @FXML private ComboBox<String> AdjustChannelCombo;
    @FXML private ComboBox<String> StatusComboBox;
    @FXML private TextField levelTextField;
    @FXML private Label statusLabel;

    private final List<ChannelData> channelList = new ArrayList<>();

    @FXML
    public void initialize() {

        ChannelColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        LevelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));
        StatusCloumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        channelTable.getItems().clear();
        AdjustChannelCombo.getItems().addAll("Kick", "Snare", "Vocal", "Guitar");

        StatusComboBox.getItems().addAll("Verified", "Unverified");

        levelTextField.clear();

        statusLabel.setText("");
    }
    @FXML
    public void HandleApplyAdjustmentButtonOnAction(ActionEvent event) {
        String channelName = AdjustChannelCombo.getValue();
        String levelText = levelTextField.getText();
        String status = StatusComboBox.getValue();

        if (channelName == null || levelText.isBlank() || status == null) {
            statusLabel.setText("Please fill channel, level, and status.");
            return;
        }
        double level;
        try {
            level = Double.parseDouble(levelText);
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid level – enter a number");
            return;
        }
        ChannelData newChannel = new ChannelData(channelName, level, status);
        channelList.add(newChannel);

        channelTable.getItems().clear();
        channelTable.getItems().addAll(channelList);

        levelTextField.clear();
        statusLabel.setText("Added");
    }

    /// File Write---------------------------------------

    @FXML
    public void HandleSaveLogsButtonOnAction(ActionEvent event) {

        try {
            File file = new File("MonitorLevelLog.bin");
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;

            if (file.exists()){
                fos = new FileOutputStream(file, true);

                oos = new AppendableObjectOutputStream(fos);
                System.out.println("appendable");
            }
            else {
                fos = new FileOutputStream(file);
                System.out.println("new");
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(channelList);
            oos.close();
            System.out.println("Object saved");
        } catch (Exception e) {
            System.out.println("Not saved");;
        }
        statusLabel.setText("Log Saved");

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