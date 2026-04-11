package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.nonuser.AppendableObjectOutputStream;
import com.example.music_band_oop.Controller.mainuser.Payment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EC_Goal8_ViewController {

    @FXML private TableView<Payment> PaymentTableView;
    @FXML private TableColumn<Payment, String> VendorRecipientCol;
    @FXML private TableColumn<Payment, String> ExpenseTypeCol;
    @FXML private TableColumn<Payment, Double> AmountCol;
    @FXML private TableColumn<Payment, String> PaymentStatusCol;
    @FXML private TableColumn<Payment, Boolean> VerifiedCol;
    @FXML private TextField VendorTextField;
    @FXML private TextField AmountTextField;
    @FXML private ComboBox<String> ExpenseTypeComboBox;
    @FXML private ComboBox<String> PaymentStatusComboBox;
    @FXML private ComboBox<Boolean> VerifiedComboBox;

    private final List<Payment> paymentList = new ArrayList<>();
    private final List<Payment> tempList3 = new ArrayList<>();

    @FXML
    public void initialize() {

        VendorRecipientCol.setCellValueFactory(new PropertyValueFactory<>("vendorName"));
        ExpenseTypeCol.setCellValueFactory(new PropertyValueFactory<>("expenseType"));
        AmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        PaymentStatusCol.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));
        VerifiedCol.setCellValueFactory(new PropertyValueFactory<>("verified"));

        ExpenseTypeComboBox.getItems().addAll("Food", "Security", "Equipment", "Venue");
        PaymentStatusComboBox.getItems().addAll("Pending", "Paid");
        VerifiedComboBox.getItems().addAll(true, false);

        /// FILE READ --------------------

        File file = new File("PaymentLog.bin");
        if (!file.exists()) {
            System.out.println("File not found");
        } else {
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);

                while (true) {
                    try {
                        Payment p = (Payment) ois.readObject();
                        tempList3.add(p);
                    } catch (EOFException e) {
                        System.out.println("Bin file read!");
                        break;
                    }
                }
                ois.close();
            } catch (Exception e) {
                System.out.println("error");
            }
        }

        paymentList.addAll(tempList3);
        refreshTable();
    }

    private void refreshTable() {
        PaymentTableView.getItems().clear();
        PaymentTableView.getItems().addAll(paymentList);
    }

    @FXML
    public void LoadPaymentButtonOnAction(ActionEvent e) {

        String vendor = VendorTextField.getText();
        String expense = ExpenseTypeComboBox.getValue();
        String amountText = AmountTextField.getText();
        String status = PaymentStatusComboBox.getValue();
        Boolean verified = VerifiedComboBox.getValue();

        if (vendor.isEmpty() || expense == null || amountText.isEmpty() || status == null || verified == null) {
            showAlert("Missing Info", "Please fill all fields.");
            return;
        }

        double amount;

        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException ex) {
            showAlert("Invalid Input", "Amount must be a number.");
            return;
        }

        Payment payment = new Payment(vendor, expense, amount, status, verified);

        paymentList.add(payment);
        refreshTable();

        /// FILE WRITE ------------------------

        try {
            File file = new File("PaymentLog.bin");
            FileOutputStream fos;
            ObjectOutputStream oos;

            if (file.exists()) {
                fos = new FileOutputStream(file, true);
                oos = new AppendableObjectOutputStream(fos);
                System.out.println("appendable");
            } else {
                fos = new FileOutputStream(file);
                oos = new ObjectOutputStream(fos);
                System.out.println("new");
            }

            oos.writeObject(payment);
            oos.close();

            System.out.println("Object saved");

        } catch (Exception ex) {
            System.out.println("Not saved");
        }

        VendorTextField.clear();
        AmountTextField.clear();
        ExpenseTypeComboBox.setValue(null);
        PaymentStatusComboBox.setValue(null);
        VerifiedComboBox.setValue(null);
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