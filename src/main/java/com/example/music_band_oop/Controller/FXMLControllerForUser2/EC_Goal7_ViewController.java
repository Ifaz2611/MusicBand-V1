package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.Vendor;
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

public class EC_Goal7_ViewController {

    @FXML private TextField EventTextField;
    @FXML private TableView<Vendor> VendorTableView;
    @FXML private TableColumn<Vendor, String> VendorCol, TypeCol, ContractCol, DeliveryCol;

    private final ObservableList<Vendor> vendorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        VendorCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ContractCol.setCellValueFactory(new PropertyValueFactory<>("contractStatus"));
        DeliveryCol.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));
        VendorTableView.setItems(vendorList);
    }

    @FXML
    public void LoadVendorButtonOnAction(ActionEvent e) {
        String eventName = EventTextField.getText().trim();
        if (eventName.isEmpty()) { showAlert("No Event", "Please enter an event name first."); return; }

        vendorList.setAll(
                new Vendor("ABC Catering", "catering", "Signed", "Pending"),
                new Vendor("SecureGuard", "security", "Signed", "Completed"),
                new Vendor("Pro AV Equipment", "equipment", "Draft", "Pending")
        );
        showAlert("Vendors Loaded", "Loaded vendors for event: " + eventName);
    }

    @FXML
    public void ViewContractDetailButtonOnAction(ActionEvent e) {
        Vendor v = getSelected(); if (v == null) return;
        showAlert("Contract Details", v.getName() + "\nContract Status: " + v.getContractStatus()
                + "\nSigned on: Jan 1, 2025\nExpiry: Dec 31, 2025");
    }

    @FXML
    public void ConfirmDeliveryButtonOnAction(ActionEvent e) {
        Vendor v = getSelected(); if (v == null) return;
        v.setDeliveryStatus("Confirmed");
        VendorTableView.refresh();
        showAlert("Success", "Delivery confirmed for " + v.getName());
    }

    @FXML
    public void UpdateRecordButtonOnAction(ActionEvent e) {
        Vendor v = getSelected(); if (v == null) return;
        TextInputDialog dialog = new TextInputDialog(v.getDeliveryStatus());
        dialog.setTitle("Update Delivery Record");
        dialog.setHeaderText("Update delivery status for " + v.getName());
        dialog.setContentText("New delivery status:");
        dialog.showAndWait().ifPresent(status -> {
            if (!status.trim().isEmpty()) {
                v.setDeliveryStatus(status);
                VendorTableView.refresh();
                showAlert("Updated", "Delivery status changed to: " + status);
            }
        });
    }

    @FXML
    public void NotifyTeamButtonOnAction(ActionEvent e) {
        Vendor v = getSelected(); if (v == null) return;
        showAlert("Team Notified", "Notification sent to the team regarding " + v.getName()
                + "\nCurrent delivery status: " + v.getDeliveryStatus());
    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/EventCoordinatorDashbroad.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Event Coordinator Dashboard");
        } catch (Exception e) {
            showAlert("Error", "Failed to load dashboard.");
        }
    }

    private Vendor getSelected() {
        Vendor v = VendorTableView.getSelectionModel().getSelectedItem();
        if (v == null) showAlert("No Selection", "Please select a vendor first.");
        return v;
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}