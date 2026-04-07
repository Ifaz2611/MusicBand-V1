package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.DeviceModel;
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

public class SE_Goal3_ViewController {

    @FXML private TextField EventTextField;
    @FXML private TextField NextLevelSoundCheckTextField;
    @FXML private CheckBox performerCheck;
    @FXML private CheckBox equipmentCheck;

    @FXML private TableView<DeviceModel> deviceTable;
    @FXML private TableColumn<DeviceModel, String> EventNameCol;
    @FXML private TableColumn<DeviceModel, String> TeststatusCol;
    @FXML private TableColumn<DeviceModel, Integer> NewlevelCol;

    @FXML private Label confirmLabel;
    @FXML private Button adjustBtn;
    @FXML private Button testBtn;

    private final ObservableList<DeviceModel> deviceList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        EventNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TeststatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        NewlevelCol.setCellValueFactory(new PropertyValueFactory<>("level"));

        deviceTable.setItems(deviceList);
    }

    @FXML
    public void testSelectedDevice() {
        DeviceModel selected = getSelectedDevice();
        if (selected == null) return;

        if (!selected.getStatus().equalsIgnoreCase("Confirmed")) {
            showAlert("Invalid Action", "Device must be confirmed before testing.");
            return;
        }

        selected.setStatus("Tested OK");
        deviceTable.refresh();
        confirmLabel.setText(selected.getName() + " tested successfully.");
    }

    @FXML
    public void adjustLevel() {
        DeviceModel selected = getSelectedDevice();
        if (selected == null) return;

        if (!selected.getStatus().equalsIgnoreCase("Tested OK")) {
            showAlert("Invalid Action", "Test device before adjusting level.");
            return;
        }

        String text = NextLevelSoundCheckTextField.getText().trim();

        if (text.isEmpty()) {
            showAlert("Input Error", "Please enter a sound level.");
            return;
        }

        try {
            int level = Integer.parseInt(text);

            if (level < 0 || level > 100) {
                showAlert("Range Error", "Level must be between 0 and 100.");
                return;
            }

            selected.setLevel(level);
            deviceTable.refresh();

            confirmLabel.setText("Level set to " + level + " for " + selected.getName());
            NextLevelSoundCheckTextField.clear();

        } catch (NumberFormatException e) {
            showAlert("Format Error", "Enter a valid number.");
        }
    }

    @FXML
    public void ConfirmSoundCheckButtonOnAction() {

        String eventName = EventTextField.getText().trim();

        if (eventName.isEmpty()) {
            confirmLabel.setText("Enter event name.");
            return;
        }

        if (!performerCheck.isSelected() || !equipmentCheck.isSelected()) {
            confirmLabel.setText("Confirm both performer & equipment.");
            return;
        }

        boolean exists = deviceList.stream()
                .anyMatch(d -> d.getName().equalsIgnoreCase(eventName));

        if (exists) {
            confirmLabel.setText("Event already exists.");
            return;
        }

        DeviceModel newEntry = new DeviceModel(eventName, "Confirmed", 0);
        deviceList.add(newEntry);

        confirmLabel.setText("Sound check ready for " + eventName);

        clearForm();
    }
    private DeviceModel getSelectedDevice() {
        DeviceModel selected = deviceTable.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("Selection Error", "Please select a device.");
        }

        return selected;
    }
    private void clearForm() {
        EventTextField.clear();
        performerCheck.setSelected(false);
        equipmentCheck.setSelected(false);
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml")
            );
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Sound Engineer Dashboard");
        } catch (Exception e) {
            showAlert("Navigation Error", "Failed to load dashboard.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}