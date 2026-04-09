package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.LogisticsItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class EC_Goal2_ViewController {

    @FXML private TableView<LogisticsItem> LogisticsTableView;
    @FXML private TableColumn<LogisticsItem, String> LogisticsAspectCol;
    @FXML private TableColumn<LogisticsItem, String> CurrentValueCol;
    @FXML private TableColumn<LogisticsItem, String> StatusCol;

    @FXML private TextField LogisticsAspectTextField;
    @FXML private TextField CurrentValueTextField;
    @FXML private ComboBox<String> StatusComboBox;

    private final List<LogisticsItem> logisticsList = new ArrayList<>();

    @FXML
    public void initialize() {
        LogisticsAspectCol.setCellValueFactory(new PropertyValueFactory<>("aspect"));
        CurrentValueCol.setCellValueFactory(new PropertyValueFactory<>("value"));
        StatusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        StatusComboBox.getItems().addAll("Not Ready", "In Progress", "Completed");

        refreshTable();
    }

    private void refreshTable() {
        LogisticsTableView.getItems().clear();
        LogisticsTableView.getItems().addAll(logisticsList);
    }

    @FXML
    public void UpdateValueButtonOnAction(ActionEvent actionEvent) {

        String aspect = LogisticsAspectTextField.getText().trim();
        String value = CurrentValueTextField.getText().trim();
        String status = StatusComboBox.getValue();

        if (aspect.isEmpty()) {
            showAlert("Missing Data", "Enter logistics aspect.");
            return;
        }

        if (value.isEmpty()) {
            showAlert("Missing Data", "Enter current value.");
            return;
        }

        if (status == null) {
            showAlert("Missing Data", "Select status.");
            return;
        }

        LogisticsItem newItem = new LogisticsItem(aspect, value, status);

        logisticsList.add(newItem);
        refreshTable();

        LogisticsAspectTextField.clear();
        CurrentValueTextField.clear();
        StatusComboBox.getSelectionModel().clearSelection();
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