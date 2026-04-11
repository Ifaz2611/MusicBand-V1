package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.EquipmentTrack;
import com.example.music_band_oop.Controller.nonuser.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SE_Goal6_ViewController {

    @FXML private TableView<EquipmentTrack> equipmentTableView;
    @FXML private TableColumn<EquipmentTrack, String> NameCol;
    @FXML private TableColumn<EquipmentTrack, String> NotesCol;
    @FXML private TableColumn<EquipmentTrack, LocalDate> MaintenanceDueDateCol;
    @FXML private TableColumn<EquipmentTrack, String> RepairStatusCol;
    @FXML private TextField NameTextField;
    @FXML private DatePicker MaintenanceDueDatePicker;
    @FXML private ComboBox<String> RepairStatusComboBox;
    @FXML private TextField RecordNotesTextField;
    @FXML private Label msgLabel;

    private final List<EquipmentTrack> equipmentList = new ArrayList<>();

    @FXML
    public void initialize() {
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        NotesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));
        MaintenanceDueDateCol.setCellValueFactory(new PropertyValueFactory<>("maintenanceDueDate"));
        RepairStatusCol.setCellValueFactory(new PropertyValueFactory<>("repairStatus"));

        RepairStatusComboBox.getItems().addAll("Operational", "Under Repair", "Pending Parts","Awaiting Technician", "Completed Repair");
        refreshTable();

    }

    private void refreshTable() {
        equipmentTableView.getItems().clear();
        equipmentTableView.getItems().addAll(equipmentList);
    }

    @FXML
    public void ConfirmUpdateButtonOnAction(ActionEvent event) {
        String name = NameTextField.getText();
        LocalDate dueDate = MaintenanceDueDatePicker.getValue();
        String repairStatus = RepairStatusComboBox.getValue();
        String notes = RecordNotesTextField.getText();

        if (name.isEmpty()) {
            showAlert("Missing Data", "Please enter equipment name.");
            return;
        }
        if (dueDate == null) {
            showAlert("Missing Data", "Please select a maintenance due date.");
            return;
        }
        if (repairStatus == null || repairStatus.isEmpty()) {
            showAlert("Missing Data", "Please select a repair status.");
            return;
        }

        int newId = equipmentList.size();

        String defaultType = "General";
        EquipmentTrack newEquipment = new EquipmentTrack(newId, name, defaultType, dueDate, repairStatus, notes);
        equipmentList.add(newEquipment);
        refreshTable();

        NameTextField.clear();
        MaintenanceDueDatePicker.setValue(null);
        RepairStatusComboBox.getSelectionModel().clearSelection();
        RecordNotesTextField.clear();

        msgLabel.setText("Equipment added");
    }


    /// file write----------------

    @FXML
    public void SaveRecordFileButtonOnAction(ActionEvent event) {
        try {
            File file = new File("EquipmentLog.bin");
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
            oos.writeObject(equipmentList);
            oos.close();
            System.out.println("Object saved");
        } catch (Exception e) {
            System.out.println("Not saved");;
        }
        msgLabel.setText("Save Record");
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