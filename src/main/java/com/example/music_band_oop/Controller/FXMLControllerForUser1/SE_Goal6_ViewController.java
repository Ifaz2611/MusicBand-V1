package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.EquipmentTrack;
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
import java.time.LocalDate;

public class SE_Goal6_ViewController {

    @FXML private TableView<EquipmentTrack> equipmentTableView;
    @FXML private TableColumn<EquipmentTrack, Integer> IdCol;
    @FXML private TableColumn<EquipmentTrack, String> NameCol;
    @FXML private TableColumn<EquipmentTrack, String> TypeCol;
    @FXML private TableColumn<EquipmentTrack, LocalDate> DueDateCol;
    @FXML private TableColumn<EquipmentTrack, String> RepairStatusCol;
    @FXML private Label selectedEquipmentLabel;
    @FXML private DatePicker MaintenanceDueDatePicker;
    @FXML private ComboBox<String> RepairStatusComboBox;
    @FXML private TextField RecordNotesTextField;

    private final ObservableList<EquipmentTrack> equipmentList = FXCollections.observableArrayList();
    private EquipmentTrack selectedEquipment;

    @FXML
    public void initialize() {
        IdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        DueDateCol.setCellValueFactory(new PropertyValueFactory<>("maintenanceDueDate"));
        RepairStatusCol.setCellValueFactory(new PropertyValueFactory<>("repairStatus"));

        RepairStatusComboBox.setItems(FXCollections.observableArrayList("Operational", "Under Repair", "Pending Parts", "Awaiting Technician", "Completed Repair"
        ));

        equipmentList.addAll(
                new EquipmentTrack(1, "Mixing Console", "Audio",  LocalDate.now().plusMonths(2), "Operational", "Last serviced Jan 2025"),
                new EquipmentTrack(2, "Amplifier",      "Power",  LocalDate.now().plusDays(5),   "Operational", "Check monthly"),
                new EquipmentTrack(3, "Microphone",     "Input",  LocalDate.now().minusDays(10), "Under Repair", "Needs new capsule"),
                new EquipmentTrack(4, "Speaker Array",  "Output", LocalDate.now().plusWeeks(3),  "Operational", "Firmware update due")
        );
        equipmentTableView.setItems(equipmentList);

        equipmentTableView.getSelectionModel().selectedItemProperty().addListener((obs, old, next) -> {
            selectedEquipment = next;
            selectedEquipmentLabel.setText(next != null ? next.getName() : "");
            MaintenanceDueDatePicker.setValue(next != null ? next.getMaintenanceDueDate() : null);
            RepairStatusComboBox.setValue(next != null ? next.getRepairStatus() : null);
            RecordNotesTextField.setText(next != null ? next.getNotes() : "");
        });
    }

    @FXML
    public void HandleScheduleMaintenanceButtonOnAction(ActionEvent event) {
        if (!checkSelected()) return;
        LocalDate newDate = MaintenanceDueDatePicker.getValue();
        if (newDate == null) { showAlert("Warning", "Pick a due date."); return; }
        selectedEquipment.setMaintenanceDueDate(newDate);
        equipmentTableView.refresh();
        showAlert("Success", "Maintenance scheduled for " + selectedEquipment.getName() + " on " + newDate);
    }

    @FXML
    public void HandleUpdateRepairStatusButtonOnAction(ActionEvent event) {
        if (!checkSelected()) return;
        String newStatus = RepairStatusComboBox.getValue();
        if (newStatus == null) { showAlert("Warning", "Select a repair status."); return; }
        selectedEquipment.setRepairStatus(newStatus);
        equipmentTableView.refresh();
        showAlert("Success", "Repair status updated to: " + newStatus);
    }

    @FXML
    public void HandleSaveRecordButtonOnAction(ActionEvent event) {
        if (!checkSelected()) return;
        selectedEquipment.setNotes(RecordNotesTextField.getText());
        equipmentTableView.refresh();
        showAlert("Saved", "Maintenance record saved for " + selectedEquipment.getName());
    }

    @FXML
    public void HandleConfirmUpdateButtonOnAction(ActionEvent event) {
        if (!checkSelected()) return;
        showAlert("Confirmed", "All updates confirmed for " + selectedEquipment.getName());
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

    private boolean checkSelected() {
        if (selectedEquipment != null) return true;
        showAlert("Warning", "No equipment selected.");
        return false;
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}