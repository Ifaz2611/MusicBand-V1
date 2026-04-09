package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.DeviceModel;
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

public class SE_Goal3_ViewController {

    @FXML private TextField EventTextField;
    @FXML private CheckBox performerCheck;
    @FXML private CheckBox equipmentCheck;
    @FXML private TableView<DeviceModel> deviceTable;
    @FXML private TableColumn<DeviceModel, String> EventNameCol;
    @FXML private TableColumn<DeviceModel, String> TeststatusCol;
    @FXML private TableColumn<DeviceModel, Integer> SoundLevelCol;
    @FXML private Label confirmLabel;
    @FXML private TextField NextLevelSoundCheckTextField;

    private final List<DeviceModel> deviceList = new ArrayList<>();

    @FXML
    public void initialize() {
        EventNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TeststatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        SoundLevelCol.setCellValueFactory(new PropertyValueFactory<>("level"));

        deviceTable.getItems().clear();
    }
    @FXML
    public void ConfirmSoundCheckButtonOnAction() {

        String eventName = EventTextField.getText();
        String soundLevelText = NextLevelSoundCheckTextField.getText();

        if (eventName.equals("")) {
            confirmLabel.setText("Enter event name.");
        }
        else if (!performerCheck.isSelected() || !equipmentCheck.isSelected()) {
            confirmLabel.setText("Confirm both performer & equipment.");
        }
        else {

            boolean exists = false;
            for (int i = 0; i < deviceList.size(); i++) {
                if (deviceList.get(i).getName().equalsIgnoreCase(eventName)) {
                    exists = true;
                    break;
                }
            }

            if (exists) {
                confirmLabel.setText("Event already exists.");
            }
            else {
                int soundLevel;

                try {
                    soundLevel = Integer.parseInt(soundLevelText);

                    if (soundLevel < 0 || soundLevel > 100) {
                        confirmLabel.setText("Sound level must be between 0 and 100.");
                    }
                    else {
                        deviceList.add(new DeviceModel(eventName, "Confirmed", soundLevel));
                        refreshTable();
                        confirmLabel.setText("Sound check ready");
                        clearForm();
                    }

                } catch (Exception e) {
                    confirmLabel.setText("Invalid sound level.");
                }
            }
        }
    }

    private void refreshTable() {
        deviceTable.getItems().clear();
        deviceTable.getItems().addAll(deviceList);
    }

    private void clearForm() {
        EventTextField.clear();
        performerCheck.setSelected(false);
        equipmentCheck.setSelected(false);
        NextLevelSoundCheckTextField.clear();
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