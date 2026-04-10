package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.Vendor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class EC_Goal7_ViewController {

    @FXML private TableView<Vendor> VendorTableView;
    @FXML private TableColumn<Vendor, String> VendorNameCol;
    @FXML private TableColumn<Vendor, String> TypeCol;
    @FXML private TableColumn<Vendor, String> ContractStatusCol;
    @FXML private TableColumn<Vendor, String> DeliveryStatusCol;
    @FXML private TextField VendorNameTextField;
    @FXML private TextField ContractStatusTextField;
    @FXML private ComboBox<String> TypeComboBox;
    @FXML private ComboBox<String> DeliveryStatusComboBox;

    private final List<Vendor> vendorList = new ArrayList<>();

    @FXML
    public void initialize() {
        VendorNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        ContractStatusCol.setCellValueFactory(new PropertyValueFactory<>("contractStatus"));
        DeliveryStatusCol.setCellValueFactory(new PropertyValueFactory<>("deliveryStatus"));

        TypeComboBox.getItems().addAll("Catering", "Security", "Equipment", "Logistics");
        DeliveryStatusComboBox.getItems().addAll("Pending", "In Progress", "Completed");
    }

    private void refreshTable() {
        VendorTableView.getItems().clear();
        VendorTableView.getItems().addAll(vendorList);
    }

    @FXML
    public void LoadVendorButtonOnAction(ActionEvent e) {

        String name = VendorNameTextField.getText().trim();
        String type = TypeComboBox.getValue();
        String contract = ContractStatusTextField.getText().trim();
        String delivery = DeliveryStatusComboBox.getValue();

        if (name.isEmpty() || type == null || contract.isEmpty() || delivery == null) {
            showAlert("Missing Info", "Please fill all fields.");
            return;
        }

        Vendor vendor = new Vendor(name, type, contract, delivery);
        vendorList.add(vendor);

        refreshTable();

        VendorNameTextField.clear();
        ContractStatusTextField.clear();
        TypeComboBox.setValue(null);
        DeliveryStatusComboBox.setValue(null);
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