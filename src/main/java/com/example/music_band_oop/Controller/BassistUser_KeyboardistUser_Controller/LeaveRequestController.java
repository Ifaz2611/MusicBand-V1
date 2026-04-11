package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LeaveRequestController
{
    @javafx.fxml.FXML
    private TableColumn<LeaveRequest, String> colReason;
    @javafx.fxml.FXML
    private Label titleLabel;
    @javafx.fxml.FXML
    private RadioButton examRadio;
    @javafx.fxml.FXML
    private DatePicker startDatePicker;
    @javafx.fxml.FXML
    private TableColumn<LeaveRequest, String> colStartDate;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableColumn<LeaveRequest, String> colDays;
    @javafx.fxml.FXML
    private DatePicker endDatePicker;
    @javafx.fxml.FXML
    private RadioButton sickRadio;
    @javafx.fxml.FXML
    private TableColumn<LeaveRequest, String> colType;
    @javafx.fxml.FXML
    private TextField reasonTextField;
    @javafx.fxml.FXML
    private TableView<LeaveRequest> leaveTableView;
    @javafx.fxml.FXML
    private RadioButton personalRadio;
    @javafx.fxml.FXML
    private TableColumn<LeaveRequest, String> colEndDate;
    @javafx.fxml.FXML
    private TableColumn<LeaveRequest, String> colStatus;

    private ToggleGroup typeGroup;

    @javafx.fxml.FXML
    public void initialize() {

        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("totalDays"));

        typeGroup = new ToggleGroup();
        sickRadio.setToggleGroup(typeGroup);
        personalRadio.setToggleGroup(typeGroup);
        examRadio.setToggleGroup(typeGroup);
        sickRadio.setSelected(true);  // default

        typeGroup = new ToggleGroup();
        sickRadio.setToggleGroup(typeGroup);
        personalRadio.setToggleGroup(typeGroup);
        examRadio.setToggleGroup(typeGroup);
        sickRadio.setSelected(true);  // default
    }

    @javafx.fxml.FXML
    public void submitBtn(ActionEvent actionEvent) {
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();
        String reason = reasonTextField.getText();

        if (start == null || end == null || reason.isEmpty()) {
            messageLabel.setText("Please fill all fields.");
            return;
        }

        if (end.isBefore(start)) {
            messageLabel.setText("End date must be after start date.");
            return;
        }

        // get selected type
        RadioButton selectedRadio = (RadioButton) typeGroup.getSelectedToggle();
        String type = selectedRadio.getText();

        // calculate total days
        long days = ChronoUnit.DAYS.between(start, end) + 1;

        // default status
        String status = "Pending";

        // create object
        LeaveRequest leave = new LeaveRequest(
                start.toString(),
                end.toString(),
                reason,
                type,
                status,
                days
        );

        // add to table
        leaveTableView.getItems().add(leave);

        messageLabel.setText("Leave request submitted!");

        // clear fields
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        reasonTextField.clear();
    }
}