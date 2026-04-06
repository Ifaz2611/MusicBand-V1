package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.ScheduleItem;
import com.example.music_band_oop.Controller.mainuser.TeamMemberEC;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EC_Goal5_ViewController implements Initializable {
    @FXML private ComboBox<String> EventComboBox;
    @FXML private TableView<ScheduleItem>ScheduleTableView;
    @FXML private TableColumn<ScheduleItem, String> TimeCol, ActivityCol, StatusCol;
    @FXML private TextField UpdateTextField;
    @FXML private TableView<TeamMemberEC>TeamTableView;
    @FXML private TableColumn<TeamMemberEC, String>  NameCol, RoleCol;
    @FXML private TableColumn<TeamMemberEC, Boolean> ArrivedCol, ReadyCol;
    @FXML private Label StatusLabel;
    private final Map<String, ObservableList<ScheduleItem>> eventScheduleMap = new HashMap<>();
    private final Map<String, ObservableList<TeamMemberEC>> eventTeamMap     = new HashMap<>();
    private String currentEvent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        bindScheduleColumns();
        bindTeamColumns();
        loadDummyData();
        EventComboBox.setItems(FXCollections.observableArrayList(eventScheduleMap.keySet()));
        EventComboBox.setOnAction(e -> onEventSelected());
    }

    private void bindScheduleColumns() {
        TimeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        ActivityCol.setCellValueFactory(new PropertyValueFactory<>("activity"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void bindTeamColumns() {
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        RoleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        ArrivedCol.setCellValueFactory(new PropertyValueFactory<>("arrived"));
        ReadyCol.setCellValueFactory(new PropertyValueFactory<>("ready"));
    }

    private void loadDummyData() {
        addEvent("Summer Music Fest",
                new ScheduleItem[]{
                        new ScheduleItem("10:00", "Sound Check", "Done"),
                        new ScheduleItem("11:30", "Opening Act", "Pending"),
                        new ScheduleItem("13:00", "Main Band",   "Not Started")
                },
                new TeamMemberEC[]{
                        new TeamMemberEC("Zahin",   "Stage Manager",  true,  true),
                        new TeamMemberEC("Jhumur",     "Sound Engineer", false, false),
                        new TeamMemberEC("Rashad", "Security",       true,  false)
                });

        addEvent("Jazz Night",
                new ScheduleItem[]{
                        new ScheduleItem("19:00", "Doors Open", "Done"),
                        new ScheduleItem("20:00", "First Set",  "In Progress"),
                        new ScheduleItem("21:30", "Second Set", "Pending")
                },
                new TeamMemberEC[]{
                        new TeamMemberEC("Nibir", "Coordinator", true,  true),
                        new TeamMemberEC("Asif",   "Lighting",    true,  true),
                        new TeamMemberEC("Abir", "Bar Staff",   false, false)
                });
    }

    private void addEvent(String name, ScheduleItem[] schedule, TeamMemberEC[] team) {
        eventScheduleMap.put(name, FXCollections.observableArrayList(schedule));
        eventTeamMap.put(name,     FXCollections.observableArrayList(team));
    }

    @FXML
    private void onEventSelected() {
        currentEvent = EventComboBox.getValue();
        if (currentEvent == null) return;
        ScheduleTableView.setItems(eventScheduleMap.get(currentEvent));
        TeamTableView.setItems(eventTeamMap.get(currentEvent));
        StatusLabel.setText("Loaded: " + currentEvent);
    }

    @FXML
    public void UpdateScheduleButtonOnAction(ActionEvent event) {
        if (!requireEvent()) return;

        String newActivity = UpdateTextField.getText().trim();
        if (newActivity.isEmpty()) { showInfo("Input Required", "Enter new activity text."); return; }

        ScheduleItem selected = ScheduleTableView.getSelectionModel().getSelectedItem();
        if (selected == null) { showInfo("No Selection", "Select a schedule row to update."); return; }

        selected.setActivity(newActivity);
        ScheduleTableView.refresh();
        UpdateTextField.clear();
        StatusLabel.setText("Schedule updated (not saved yet).");
    }

    @FXML
    public void SaveUpdatesButtonOnAction(ActionEvent event) {
        if (!requireEvent()) return;
        showInfo("Saved", "All updates for " + currentEvent + " have been saved.");
        StatusLabel.setText("Changes saved.");
    }

    @FXML
    public void VerifyButtonOnAction(ActionEvent event) {
        if (!requireEvent()) return;

        StringBuilder report = new StringBuilder("Arrival & Readiness Report:\n");
        for (TeamMemberEC m : eventTeamMap.get(currentEvent)) {
            report.append(String.format("%s (%s): %s, %s%n",
                    m.getName(), m.getRole(),
                    m.isArrived() ? "Arrived"   : "Not arrived",
                    m.isReady()   ? "Ready"     : "Not ready"));
        }
        showInfoDialog("Team Verification", "Current Status", report.toString());
    }

    @FXML
    public void EmergencyButtonOnAction(ActionEvent event) {
        showInfoDialog("Emergency Contacts", "Keep this information ready",
                "Emergency Contacts:\n\n" +
                        "Fire:               999\n" +
                        "Police:             911\n" +
                        "Medical:            999\n" +
                        "Event Security:     +88012345678\n" +
                        "Coordinator Mobile: +88012345678");
    }
    @FXML
    public void NotifyTeamButtonOnAction(ActionEvent event) {
        if (!requireEvent()) return;
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Notify Team");
        confirm.setHeaderText("Send notifications?");
        confirm.setContentText("Notify all team members about updates to " + currentEvent + "?");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                StatusLabel.setText("Notification sent to team.");
                showInfo("Sent", "Team members have been notified.");
            }
        });
    }
    @FXML
    public void DashboardButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/EventCoordinatorDashbroad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Event Coordinator Dashboard");
        } catch (Exception e) {
            showInfo("Error", "Failed to load dashboard.");
        }
    }

    private boolean requireEvent() {
        if (currentEvent != null) return true;
        showInfo("No Event", "Please select an event first.");
        return false;
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoDialog(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}