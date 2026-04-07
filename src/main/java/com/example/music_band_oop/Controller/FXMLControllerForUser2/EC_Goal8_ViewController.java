package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.Payment;
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

public class EC_Goal8_ViewController {

    @FXML private ComboBox<String> EventComboBox;
    @FXML private TableView<Payment> PaymentTableView;
    @FXML private TableColumn<Payment, String> VendorNameCol, ExpenseTypeCol, PaymentCol;
    @FXML private TableColumn<Payment, Double> AmountCol;
    @FXML private TableColumn<Payment, Boolean> VerifiedCol;
    @FXML private TextArea NotesTextArea;

    private final ObservableList<Payment> paymentList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        EventComboBox.setItems(FXCollections.observableArrayList("Annual Conference 2025", "Summer Music Fest", "Corporate Gala Night"));
        VendorNameCol.setCellValueFactory(new PropertyValueFactory<>("vendorName"));
        ExpenseTypeCol.setCellValueFactory(new PropertyValueFactory<>("expenseType"));
        AmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        PaymentCol.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        VerifiedCol.setCellValueFactory(new PropertyValueFactory<>("verified"));
        VerifiedCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : (item ? "Yes" : "No"));
            }
        });
        PaymentTableView.setItems(paymentList);
    }

    @FXML
    public void LoadPaymentButtonOnAction(ActionEvent e) {
        String event = getSelectedEvent(); if (event == null) return;
        paymentList.setAll(
                new Payment("ABC Catering",     "Food & Beverage", 2500.00, "Pending", false),
                new Payment("SecureGuard",      "Security",        1200.00, "Paid",    false),
                new Payment("Pro AV Equipment", "A/V Rental",       800.00, "Pending", false),
                new Payment("Venue Hall",       "Venue Rental",    3000.00, "Paid",    false)
        );
        showAlert("Payments Loaded", "Loaded payment records for " + event);
    }

    @FXML
    public void VerifyPaymentButtonOnAction(ActionEvent e) {
        Payment p = getSelectedPayment(); if (p == null) return;
        p.setVerified(true);
        PaymentTableView.refresh();
        showAlert("Verified", "Payment for " + p.getVendorName() + " has been verified.");
    }

    @FXML
    public void UpdatePaymentRecordButtonOnAction(ActionEvent e) {
        Payment p = getSelectedPayment(); if (p == null) return;
        ChoiceDialog<String> dialog = new ChoiceDialog<>(p.getPaymentStatus(), "Pending", "Paid", "Verified");
        dialog.setTitle("Update Payment Status");
        dialog.setHeaderText("Update status for " + p.getVendorName());
        dialog.setContentText("New status:");
        dialog.showAndWait().ifPresent(status -> {
            p.setPaymentStatus(status);
            PaymentTableView.refresh();
            showAlert("Updated", "Payment status changed to " + status);
        });
    }

    @FXML
    public void WriteEventNotesButtonOnAction(ActionEvent e) {
        if (NotesTextArea.getText().trim().isEmpty())
            showAlert("No Notes", "Please write some notes or lessons learned.");
        else
            showAlert("Notes Saved", "Event notes saved successfully.");
    }

    @FXML
    public void SendThankYouMessagesButtonOnAction(ActionEvent e) {
        showAlert("Thank-You Messages", "Thank-you messages sent to all team members and vendors.");
    }

    @FXML
    public void GenerateReportButtonOnAction(ActionEvent e) {


//        String event = getSelectedEvent(); if (event == null) return;
//        StringBuilder report = new StringBuilder("Event Closing Report for: ").append(event).append("\n\nPayment Summary:\n");
//        double total = 0;
//        for (Payment p : paymentList) {
//            report.append("- ").append(p.getVendorName()).append(": $").append(p.getAmount())
//                    .append(" (").append(p.getPaymentStatus()).append(")\n");
//            total += p.getAmount();
//        }
//        report.append("\nTotal Expenses: $").append(total)
//                .append("\n\nNotes: ").append(NotesTextArea.getText().isEmpty() ? "None" : NotesTextArea.getText());
//        showAlert("Event Closing Report", report.toString());
    }

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
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private String getSelectedEvent() {
        String event = EventComboBox.getValue();
        if (event == null || event.isEmpty()) { showAlert("No Event", "Please select a completed event."); return null; }
        return event;
    }

    private Payment getSelectedPayment() {
        Payment p = PaymentTableView.getSelectionModel().getSelectedItem();
        if (p == null) showAlert("No Selection", "Select a payment record first.");
        return p;
    }

}