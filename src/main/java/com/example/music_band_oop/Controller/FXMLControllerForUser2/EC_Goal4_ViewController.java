package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.CommunicationRecord;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EC_Goal4_ViewController {

    @FXML private ComboBox<String> EventComboBox;
    @FXML private TextField MessageTextField;
    @FXML private CheckBox BandCheckBox, SoundCheckBox, VenueCheckBox;
    @FXML private TableView<CommunicationRecord> HistoryTableViw;
    @FXML private TableColumn<CommunicationRecord, String> EventCol, MessageCol, RecipientsCol, TimestampCol;

    private final ObservableList<CommunicationRecord> recordList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        EventComboBox.getItems().addAll("Summer Fest 2025", "Jazz Night", "Rock Concert", "Classical Evening");
        EventCol.setCellValueFactory(new PropertyValueFactory<>("event"));
        MessageCol.setCellValueFactory(new PropertyValueFactory<>("message"));
        RecipientsCol.setCellValueFactory(new PropertyValueFactory<>("recipients"));
        TimestampCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));
        HistoryTableViw.setItems(recordList);
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

        if (!sendCommunication(selectedEvent, message, recipients)) {
            showAlert("Error", "Failed to send message.");
            return;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        recordList.add(0, new CommunicationRecord(selectedEvent, message, recipients, timestamp));
        clearForm();
        showAlert("Success", "Message sent and saved to history.");
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

    private boolean sendCommunication(String event, String message, String recipients) {
        System.out.println("SENDING -> Event: " + event + " | To: " + recipients + " | Msg: " + message);
        return true;
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/EventCoordinatorDashbroad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Event Coordinator Dashboard");
        } catch (Exception e) {
            showAlert("Error", "Failed to load dashboard: " + e.getMessage());
        }
    }
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}