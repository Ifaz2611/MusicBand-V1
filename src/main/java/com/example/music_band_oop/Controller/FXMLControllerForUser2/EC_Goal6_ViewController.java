package com.example.music_band_oop.Controller.FXMLControllerForUser2;

import com.example.music_band_oop.Controller.mainuser.Feedback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class EC_Goal6_ViewController {

    @FXML private TableView<Feedback> FeedbackTableView;
    @FXML private TableColumn<Feedback, String> NameCol;
    @FXML private TableColumn<Feedback, Integer> RatingCol;
    @FXML private TableColumn<Feedback, String> CommentCol;
    @FXML private TextField NameTextField;
    @FXML private TextField CommentTextField;
    @FXML private ComboBox<Integer> RatingComboBox;

    @FXML private Label statusLabel;

    private final List<Feedback> feedbackList = new ArrayList<>();

    @FXML
    public void initialize() {
        NameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        RatingCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        CommentCol.setCellValueFactory(new PropertyValueFactory<>("comment"));

        RatingComboBox.getItems().addAll(1, 2, 3, 4, 5);
        refreshTable();
    }

    private void refreshTable() {
        FeedbackTableView.getItems().clear();
        FeedbackTableView.getItems().addAll(feedbackList);
    }

    @FXML
    public void CollectFeedbackButtonOnAction(ActionEvent event) {

        String name = NameTextField.getText();
        String comment = CommentTextField.getText();
        Integer rating = RatingComboBox.getValue();

        if (name.isEmpty() || comment.isEmpty() || rating == null) {
            statusLabel.setText("Please fill all fields.");
            return;
        }
        Feedback newFeedback = new Feedback(name, rating, comment);
        feedbackList.add(newFeedback);
        refreshTable();
        statusLabel.setText("Feedback added successfully.");
        NameTextField.clear();
        CommentTextField.clear();
        RatingComboBox.setValue(null);
    }

    @FXML
    public void GenerateReportButtonOnAction(ActionEvent event) {
        //Deal with it later
        statusLabel.setText("Report generation not implemented.");
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

}