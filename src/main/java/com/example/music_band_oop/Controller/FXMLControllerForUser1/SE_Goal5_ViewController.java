package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.Equipment;
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

public class SE_Goal5_ViewController {

    @FXML private TableColumn<Equipment, String> EquipmentCol;
    @FXML private TableColumn<Equipment, String> StatusCol;
    @FXML private TableColumn<Equipment, String> NotesCol;
    @FXML private TableView<Equipment> equipmentTable;
    @FXML private ComboBox<String> EventComboBox;
    @FXML private Label warningLabelAlert;

    private ObservableList<Equipment> equipmentList;

    @FXML
    public void initialize() {
        setupTable();
        setupComboBox();
    }

    private void setupTable() {
        EquipmentCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        NotesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));
    }

    private void setupComboBox() {
        EventComboBox.getItems().addAll(
                "Rock Concert - 2025-04-02",
                "Jazz Night - 2025-04-01",
                "Rock n Roll - 2026-02-26",
                "BanglaBhag - 2026-03-12"
        );
        EventComboBox.setOnAction(e -> loadEquipment());
    }

    private void loadEquipment() {
        equipmentList = FXCollections.observableArrayList(
                new Equipment("Main Mixer", "OK", ""),
                new Equipment("Stage Monitors", "OK", ""),
                new Equipment("Microphones", "Damaged", "Needs replacement"),
                new Equipment("Power Amplifier", "OK", ""),
                new Equipment("Speakers (Front of House)", "OK", ""),
                new Equipment("Subwoofers", "OK", ""),
                new Equipment("Wireless Microphone System", "Maintenance", "Battery issue"),
                new Equipment("Audio Interface", "OK", ""),
                new Equipment("Cables (XLR)", "Damaged", "Some cables faulty"),
                new Equipment("DI Boxes", "OK", ""),
                new Equipment("Lighting Console", "OK", ""),
                new Equipment("LED Stage Lights", "Maintenance", "Flickering issue"),
                new Equipment("Projector", "OK", ""),
                new Equipment("Screen/Backdrop", "OK", ""),
                new Equipment("Power Distribution Unit", "OK", "")
        );
        equipmentTable.setItems(equipmentList);
        setMessage("");
    }

    private void setMessage(String msg) {
        warningLabelAlert.setText(msg);
    }

    @FXML
    public void packButtonOnAction(ActionEvent event) {
        setMessage("Equipment packed.");
    }

    @FXML
    public void verifyButtonOnAction(ActionEvent event) {
        boolean hasDamage = equipmentList.stream()
                .anyMatch(e -> !"OK".equals(e.getStatus()));

        setMessage(hasDamage
                ? "Missing or damaged equipment!"
                : "✓ Verified. Status updated.");
    }

    @FXML
    public void ClearButtonOnAction(ActionEvent event) {
        setMessage("Venue cleared.");
    }

    @FXML
    public void CloseButtonOnAction(ActionEvent event) {
        setMessage("Event marked closed.");
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