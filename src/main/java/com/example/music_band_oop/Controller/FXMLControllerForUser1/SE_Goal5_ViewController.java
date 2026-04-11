package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.Equipment;
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


public class SE_Goal5_ViewController {

    @FXML private TableColumn<Equipment, String> EquipmentCol;
    @FXML private TableColumn<Equipment, String> StatusCol;
    @FXML private TableColumn<Equipment, String> NotesCol;
    @FXML private TableView<Equipment> equipmentTable;
    @FXML private ComboBox<String> EventComboBox;

    private final List<Equipment> equipmentList = new ArrayList<>();

    @FXML
    public void initialize() {
        EquipmentCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        NotesCol.setCellValueFactory(new PropertyValueFactory<>("notes"));

        EventComboBox.getItems().addAll("Rock Concert", "Jazz Night", "Rock n Roll", "BanglaBhag");

        EventComboBox.setOnAction(e -> loadEquipment());

        refreshTable();
    }


    private void refreshTable() {
        equipmentTable.getItems().clear();
        equipmentTable.getItems().addAll(equipmentList);
    }

    private void loadEquipment() {
        equipmentList.clear();
        equipmentList.add(new Equipment("Main Mixer", "OK", ""));
        equipmentList.add(new Equipment("Stage Monitors", "OK", ""));
        equipmentList.add(new Equipment("Microphones", "Damaged", "Needs replacement"));
        equipmentList.add(new Equipment("Power Amplifier", "OK", ""));
        equipmentList.add(new Equipment("Speakers (Front of House)", "OK", ""));
        refreshTable();
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