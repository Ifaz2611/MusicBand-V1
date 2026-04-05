package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.BudgetItem;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class EC_Goal3_ViewController {

    @FXML private ComboBox<String> EventComboBox;
    @FXML private TableView<BudgetItem> BudgetTableView;
    @FXML private TableColumn<BudgetItem, String> CostTypeCol;
    @FXML private TableColumn<BudgetItem, Double> AmountCol;
    @FXML private TableColumn<BudgetItem, Boolean> PaidCol;
    @FXML private TextField CostTypeTextField, AmountTextField;
    @FXML private TextArea LogTextArea;

    private final ObservableList<BudgetItem> budgetList = FXCollections.observableArrayList();
    private static final double AVAILABLE_FUNDS = 10000.0;
    private boolean budgetSaved = false;

    @FXML
    public void initialize() {
        CostTypeCol.setCellValueFactory(new PropertyValueFactory<>("costType"));
        AmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        PaidCol.setCellValueFactory(new PropertyValueFactory<>("paid"));

        EventComboBox.setItems(FXCollections.observableArrayList("Summer Music Fest", "Jazz Night", "Rock Concert"));
        EventComboBox.setOnAction(e -> loadBudgetForSelectedEvent());
        BudgetTableView.setItems(budgetList);
    }

    private void loadBudgetForSelectedEvent() {
        String selected = EventComboBox.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        budgetList.setAll(
                new BudgetItem("Band payment", 5000.0, false),
                new BudgetItem("Venue cost", 2000.0, false),
                new BudgetItem("Equipment cost", 1500.0, false)
        );
        log("Event selected: " + selected + ". Budget details loaded.");
        budgetSaved = false;
    }

    @FXML
    public void AddOrUpdateBudgetButtonOnAction(ActionEvent event) {
        String type = CostTypeTextField.getText().trim();
        String amountText = AmountTextField.getText().trim();

        if (type.isEmpty() || amountText.isEmpty()) { showAlert("Input Error", "Please enter both cost type and amount."); return; }

        double amount;
        try { amount = Double.parseDouble(amountText); }
        catch (NumberFormatException e) { showAlert("Invalid Amount", "Please enter a numeric amount."); return; }

        for (BudgetItem item : budgetList) {
            if (item.getCostType().equalsIgnoreCase(type)) {
                item.setAmount(amount);
                BudgetTableView.refresh();
                log("Updated " + type + " amount to $" + amount);
                clearInputFields();
                return;
            }
        }
        budgetList.add(new BudgetItem(type, amount, false));
        log("Added new cost: " + type + " = $" + amount);
        clearInputFields();
    }
    @FXML
    public void VerifyBudgetButtonOnAction(ActionEvent event) {
        if (budgetList.isEmpty()) { log("No budget items to verify."); return; }

        double total = budgetList.stream().mapToDouble(BudgetItem::getAmount).sum();
        log("Total budget required: $" + total + ". Available funds: $" + AVAILABLE_FUNDS);
        log(total <= AVAILABLE_FUNDS ? "Budget is approved." : "Budget warning: Required amount exceeds available funds!");
    }

    @FXML
    public void SaveBudgetRecordButtonOnAction(ActionEvent event) {
        if (budgetList.isEmpty()) { showAlert("No Data", "No budget items to save."); return; }

        double total = budgetList.stream().mapToDouble(BudgetItem::getAmount).sum();
        if (total <= AVAILABLE_FUNDS) {
            budgetSaved = true;
            log("Budget record saved successfully.");
        } else {
            showAlert("Budget Warning", "Budget exceeds available funds. Cannot save.");
        }
    }

    @FXML
    public void ProcessPaymentsButtonOnAction(ActionEvent event) {
        if (!budgetSaved) { showAlert("Not Saved", "Please save the budget record before processing payments."); return; }

        budgetList.forEach(item -> item.setPaid(true));
        BudgetTableView.refresh();
        log("All payments processed successfully.");
    }

    //File read and write --------------------------------------------------------------------------------------


    @FXML
    public void GenerateReportButtonOnAction(ActionEvent event) {
//        if (budgetList.isEmpty()) { log("No budget data to generate report."); return; }
//
//        StringBuilder report = new StringBuilder("\n--- Payment Summary Report ---\n");
//        double totalPaid = 0;
//        for (BudgetItem item : budgetList) {
//            report.append(item.getCostType()).append(": $").append(item.getAmount())
//                    .append(item.isPaid() ? " (PAID)\n" : " (UNPAID)\n");
//            if (item.isPaid()) totalPaid += item.getAmount();
//        }
//        report.append("Total Paid: $").append(totalPaid).append("\n");
//        LogTextArea.appendText(report.toString());
//
//        try (PrintWriter out = new PrintWriter(new FileWriter("budget_report.txt", true))) {
//            out.println(LocalDateTime.now());
//            budgetList.forEach(item -> out.println(item.getCostType() + " | $" + item.getAmount() + " | " + (item.isPaid() ? "PAID" : "UNPAID")));
//            out.println("Total Paid: $" + totalPaid);
//            out.println("-------------------");
//            log("Report saved to budget_report.txt");
//        } catch (IOException e) {
//            log("Error saving report.");
//        }
    }

    @FXML
    public void NotifyUsersButtonOnAction(ActionEvent event) {
        log("Notification sent to: Band, Venue Manager, Equipment Supplier.");
        log("Payment status: All payments completed.");
        showAlert("Notified", "All concerned parties have been notified.");
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

    private void log(String msg) { LogTextArea.appendText(msg + "\n"); }
    private void clearInputFields() { CostTypeTextField.clear(); AmountTextField.clear(); }
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}