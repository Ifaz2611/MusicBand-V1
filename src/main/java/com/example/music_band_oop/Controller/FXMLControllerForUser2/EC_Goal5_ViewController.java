package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.TeamMemberEC;
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


public class EC_Goal5_ViewController {

    @FXML private ComboBox<String> EventComboBox;
    @FXML private ComboBox<String> RoleComboBox;
    @FXML private ComboBox<Boolean> ArrivedComboBox;
    @FXML private ComboBox<String> StatusComboBox;

    @FXML private TableView<TeamMemberEC> TeamTableView;
    @FXML private TableColumn<TeamMemberEC, String> RoleCol;
    @FXML private TableColumn<TeamMemberEC, Boolean> ArrivedCol;
    @FXML private TableColumn<TeamMemberEC, Boolean> StatusCol;

    @FXML private Label StatusLabel;

    private final List<TeamMemberEC> teamList = new ArrayList<>();
    @FXML
    private TableColumn EventCol;

    @FXML
    public void initialize() {

        RoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        ArrivedCol.setCellValueFactory(new PropertyValueFactory<>("arrived"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("ready"));
        EventCol.setCellValueFactory(new PropertyValueFactory<>("event"));

        EventComboBox.getItems().addAll("Summer Fest", "Jazz Night", "Rock Concert","Rock n Roll","Dhaka19A124");
        RoleComboBox.getItems().addAll("Stage Manager", "Sound Engineer", "Security", "Coordinator", "Lighting", "Bar Staff");
        ArrivedComboBox.getItems().addAll(true, false);
        StatusComboBox.getItems().addAll("Ready", "Not Ready");
        refreshTable();
    }

    private void refreshTable() {
        TeamTableView.getItems().clear();
        TeamTableView.getItems().addAll(teamList);
    }

    @FXML
    public void UpdateButtonOnAction(ActionEvent event) {

        String role = RoleComboBox.getValue();
        Boolean arrived = ArrivedComboBox.getValue();
        String statusText = StatusComboBox.getValue();
        String eventName = EventComboBox.getValue();

        if (role == null || arrived == null || statusText == null || eventName == null) {
            StatusLabel.setText("Please fill all fields.");
            return;
        }

        boolean ready = statusText.equals("Ready");
        String name = "Member " + (teamList);
        TeamMemberEC newMember = new TeamMemberEC(eventName, name, role, arrived, ready);
        teamList.add(newMember);
        refreshTable();

        RoleComboBox.setValue(null);
        ArrivedComboBox.setValue(null);
        StatusComboBox.setValue(null);
        EventComboBox.setValue(null);

        StatusLabel.setText("Member added successfully.");
    }

    @FXML
    public void EmergencyButtonOnAction(ActionEvent event) {
        showAlert("Emergency Contacts", "Fire: 999\nPolice: 999\nMedical: 999");
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/EventCoordinatorDashbroad.fxml"));
            Scene dashboardScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(dashboardScene);
            currentStage.setTitle("Event Coordinator Dashboard");
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showAlert(String title, String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}