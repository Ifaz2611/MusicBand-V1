package com.example.music_band_oop.Controller.BassistUser_KeyboardistUser_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class KeyGoal7AvailabilityController
{
    @javafx.fxml.FXML
    private TableColumn<KeyboardLeaveRequest, String> colReason;
    @javafx.fxml.FXML
    private TableColumn<KeyboardLeaveRequest, String> colDays;
    @javafx.fxml.FXML
    private DatePicker endDatePicker;
    @javafx.fxml.FXML
    private DatePicker startDatePicker;
    @javafx.fxml.FXML
    private TextField filterTextField;
    @javafx.fxml.FXML
    private TableColumn<KeyboardLeaveRequest, String> colStartDate;
    @javafx.fxml.FXML
    private TextField reasonTextField;
    @javafx.fxml.FXML
    private Label messageLabel;
    @javafx.fxml.FXML
    private TableView<KeyboardLeaveRequest> leaveTableView;
    @javafx.fxml.FXML
    private TableColumn<KeyboardLeaveRequest, String> colEndDate;
    @javafx.fxml.FXML
    private TableColumn<KeyboardLeaveRequest, String> colStatus;

    private ArrayList<KeyboardLeaveRequest> leaveList = new ArrayList<>();

    @javafx.fxml.FXML
    public void initialize() {
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        colEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        colReason.setCellValueFactory(new PropertyValueFactory<>("reason"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDays.setCellValueFactory(new PropertyValueFactory<>("totalDays"));

        loadData();
        leaveTableView.getItems().addAll(leaveList);
    }

    private void loadData() {
        leaveList.add(new KeyboardLeaveRequest("2025-06-10", "2025-06-12", "Personal", "Pending", 3));

        leaveList.add(new KeyboardLeaveRequest("2025-04-10", "2025-04-12", "Midterm exams", "Approved", 3));
        leaveList.add(new KeyboardLeaveRequest("2025-04-20", "2025-04-20", "Doctor appointment", "Pending", 1));
        leaveList.add(new KeyboardLeaveRequest("2025-05-01", "2025-05-03", "Family event", "Approved", 3));
    }

    @javafx.fxml.FXML
    public void submitBtn(ActionEvent actionEvent) {
        LocalDate start = startDatePicker.getValue();
        LocalDate end = endDatePicker.getValue();
        String reason = reasonTextField.getText();

        if (start == null || end == null || reason.isEmpty()) {
            messageLabel.setText("Fill all fields.");
            return;
        }

        if (end.isBefore(start)) {
            messageLabel.setText("End date must be after start date.");
            return;
        }

        long days = ChronoUnit.DAYS.between(start, end) + 1;

        KeyboardLeaveRequest leave = new KeyboardLeaveRequest(
                start.toString(),
                end.toString(),
                reason,
                "Pending",
                days
        );

        leaveList.add(leave);
        leaveTableView.getItems().add(leave);

        messageLabel.setText("Leave request submitted.");

        // clear fields
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        reasonTextField.clear();
    }

    @javafx.fxml.FXML
    public void resetFilterButton(ActionEvent actionEvent) {
        leaveTableView.getItems().clear();
        leaveTableView.getItems().addAll(leaveList);

        filterTextField.clear();

        messageLabel.setText("Reset done.");
    }

    @javafx.fxml.FXML
    public void filterButton(ActionEvent actionEvent) {
        String text = filterTextField.getText().toLowerCase();

        leaveTableView.getItems().clear();

        for (KeyboardLeaveRequest l : leaveList) {
            if (l.getReason().toLowerCase().contains(text)) {
                leaveTableView.getItems().add(l);
            }
        }

        messageLabel.setText("Filtered.");
    }
}