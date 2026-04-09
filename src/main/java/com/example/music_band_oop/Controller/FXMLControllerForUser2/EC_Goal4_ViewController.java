package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.CommunicationRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class EC_Goal4_ViewController {

    @FXML private ComboBox<String> EventComboBox;
    @FXML private TextField MessageTextField;
    @FXML private CheckBox BandCheckBox, SoundCheckBox, VenueCheckBox;
    @FXML private TableColumn<CommunicationRecord, String> EventCol, MessageCol, RecipientsCol;
    @FXML private TableView<CommunicationRecord> HistoryTableViw;

    private final List<CommunicationRecord> recordList = new ArrayList<>();

    @FXML
    public void initialize() {
        EventComboBox.getItems().addAll("Summer Fest 2026", "Jazz Night", "Rock Concert", "Classical Evening","Rock n Roll", "Dhaka19A2");

        EventCol.setCellValueFactory(new PropertyValueFactory<>("event"));
        MessageCol.setCellValueFactory(new PropertyValueFactory<>("message"));
        RecipientsCol.setCellValueFactory(new PropertyValueFactory<>("recipients"));
        refreshTable();
    }
    private void refreshTable() {
        HistoryTableViw.getItems().clear();
        HistoryTableViw.getItems().addAll(recordList);
    }
    @FXML
    public void SendMessageButtonOnAction(ActionEvent actionEvent) {
        String selectedEvent = EventComboBox.getValue();
        if (selectedEvent == null || selectedEvent.isEmpty()) {
            showAlert("Validation Error", "Please select an event.");
            return;
        }
        String message = MessageTextField.getText().trim();
        if (message.isEmpty()) {
            showAlert("Validation Error", "Please write a message.");
            return;
        }
        String recipients = buildRecipients();
        if (recipients.isEmpty()) {
            showAlert("Validation Error", "Select at least one recipient.");
            return;
        }
        recordList.add(new CommunicationRecord(selectedEvent, message, recipients));
        refreshTable();
        clearForm();
    }

    private String buildRecipients() {
        StringBuilder sb = new StringBuilder();
        if (BandCheckBox.isSelected())  sb.append("Band ");
        if (SoundCheckBox.isSelected()) sb.append("Sound Engineer ");
        if (VenueCheckBox.isSelected()) sb.append("Venue Staff ");
        return sb.toString().trim();
    }
    private void clearForm() {
        MessageTextField.clear();
        BandCheckBox.setSelected(false);
        SoundCheckBox.setSelected(false);
        VenueCheckBox.setSelected(false);
        EventComboBox.setValue(null);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}