package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.BudgetItem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class EC_Goal3_ViewController {

    @FXML private TableView<BudgetItem> BudgetTableView;
    @FXML private TableColumn<BudgetItem, String> CostTypeCol;
    @FXML private TableColumn<BudgetItem, Double> AmountCol;
    @FXML private TableColumn<BudgetItem, Boolean> PaidStatusCol;

    @FXML private TextField AmountTextField;
    @FXML private ComboBox<String> PaidStatusComboBox;
    @FXML private ComboBox<String> CostTypeComboBox;

    private final List<BudgetItem> budgetList = new ArrayList<>();

    @FXML
    public void initialize() {
        CostTypeCol.setCellValueFactory(new PropertyValueFactory<>("costType"));
        AmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        PaidStatusCol.setCellValueFactory(new PropertyValueFactory<>("paid"));

        PaidStatusComboBox.getItems().addAll("Paid", "Unpaid");
        CostTypeComboBox.getItems().addAll("Personal", "Company", "Travel", "Food", "Utilities");

        refreshTable();
    }

    private void refreshTable() {
        BudgetTableView.getItems().clear();
        BudgetTableView.getItems().addAll(budgetList);
    }

    @FXML
    public void UpdateBudgetButtonOnAction(ActionEvent actionEvent) {

        String costType = CostTypeComboBox.getValue();
        String amountText = AmountTextField.getText();
        String paidStatus = PaidStatusComboBox.getValue();

        if (costType == null || amountText.isEmpty() || paidStatus == null) {
            showAlert("Error", "Please fill all fields.");
            return;
        }
        try {
            double amount = Double.parseDouble(amountText);
            boolean isPaid = paidStatus.equals("Paid");
            BudgetItem newItem = new BudgetItem(costType, amount, isPaid);
            budgetList.add(newItem);
            refreshTable();
            clearFields();
        } catch (NumberFormatException e) {
            showAlert("Error", "Enter a valid number.");
        }
    }
    private void clearFields() {
        AmountTextField.clear();
        CostTypeComboBox.setValue(null);
        PaidStatusComboBox.setValue(null);
    }


    @FXML
    public void GenerateReportButtonOnAction(ActionEvent event) {
        showAlert("Info", "Report generation is disabled.");
        //I will deal with it later
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