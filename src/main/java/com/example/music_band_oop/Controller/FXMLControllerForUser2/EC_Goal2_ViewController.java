package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.LogisticsItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class EC_Goal2_ViewController {

    @FXML private TableView<LogisticsItem> LogisticsTableView;
    @FXML private TableColumn<LogisticsItem, String> LogisticsAspectCol, ValueCol, StatusCol;
    @FXML private TextField ValueTextField;
    @FXML private TextArea logTextArea;

    private ObservableList<LogisticsItem> logisticsList;
    @FXML
    public void initialize() {
        LogisticsAspectCol.setCellValueFactory(new PropertyValueFactory<>("aspect"));
        ValueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        logisticsList = FXCollections.observableArrayList(
                new LogisticsItem("Transport", "Not assigned", "Not Ready"),
                new LogisticsItem("Stage Setup Time", "Not set", "Not Ready"),
                new LogisticsItem("Rehearsal Schedule", "Not set", "Not Ready")
        );

        LogisticsTableView.setItems(logisticsList);
    }
    @FXML
    public void UpdateValueButtonOnAction(ActionEvent event) {
        LogisticsItem selected = LogisticsTableView.getSelectionModel().getSelectedItem();

        if (selected == null) {
            showAlert("No Selection", "Please select a logistics aspect.");
            return;
        }

        String newValue = ValueTextField.getText().trim();
        if (newValue.isEmpty()) {
            showAlert("Empty Value", "Enter a value first.");
            return;
        }

        selected.setValue(newValue);
        log("Updated " + selected.getAspect() + " → " + newValue);

        ValueTextField.clear();
        LogisticsTableView.refresh();
    }

    @FXML
    public void VerifyReadinessButtonOnAction(ActionEvent event) {
        boolean allReady = logisticsList.stream()
                .allMatch(i -> !i.getValue().equals("Not assigned")
                        && !i.getValue().equals("Not set")
                        && !i.getValue().isEmpty());

        if (allReady) {
            logisticsList.forEach(i -> i.setStatus("Ready"));
            log("All logistics ready.");
            LogisticsTableView.refresh();
        } else {
            log("Some items are still incomplete.");
        }
    }

    @FXML
    public void confirmLogisticsPlanOnAction(ActionEvent event) {
        boolean allReady = logisticsList.stream()
                .allMatch(i -> "Ready".equals(i.getStatus()));

        if (allReady) {
            log("Plan confirmed.");
            showAlert("Confirmed", "Logistics plan confirmed.");
        } else {
            showAlert("Not Ready", "Verify readiness first.");
        }
    }

    @FXML
    public void notifyUsersButtonOnAction(ActionEvent event) {
        log("Users notified. Process complete.");
        showAlert("Done", "All users notified.");
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/EventCoordinatorDashbroad.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));

        } catch (Exception e) {
            showAlert("Error", "Failed to load dashboard.");
        }
    }

    private void log(String msg) {
        logTextArea.appendText(msg + "\n");
    }

    private void showAlert(String title, String msg) {
        new Alert(Alert.AlertType.INFORMATION, msg).showAndWait();
    }
}