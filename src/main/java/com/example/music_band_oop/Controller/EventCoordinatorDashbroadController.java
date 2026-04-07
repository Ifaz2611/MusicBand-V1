package com.example.music_band_oop.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EventCoordinatorDashbroadController
{
    @javafx.fxml.FXML
    private Label EventCordinatorDashboradFxId;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void communicationButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/EventCoordinatorGoals/Goal4_EventCoordinatorCommunicateUpdates.fxml"));
            Scene goal4Scene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(goal4Scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void vendorManagementButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/EventCoordinatorGoals/Goal7_EventCoordinatorTrackVendorContracts.fxml"));
            Scene goal7Scene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(goal7Scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void eventLogisticsButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/EventCoordinatorGoals/Goal2_EventCoordinatorManageLogistics.fxml"));
            Scene goal2Scene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(goal2Scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @javafx.fxml.FXML
    public void logoutButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/login.fxml"));
            Scene loginScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) EventCordinatorDashboradFxId.getScene().getWindow();
            currentStage.setScene(loginScene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void entryFeedbackButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/EventCoordinatorGoals/Goal6_EventCoordinatorManageAudienceFeedback.fxml"));
            Scene goal6Scene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(goal6Scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @javafx.fxml.FXML
    public void createEventButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/EventCoordinatorGoals/Goal1_EventCoordinatorCreateScheduleEvent.fxml"));
            Scene goal1Scene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(goal1Scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void eventClosingButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/EventCoordinatorGoals/Goal8_EventCoordinatorEventClosing.fxml"));
            Scene goal8Scene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(goal8Scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void eventDayButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/EventCoordinatorGoals/Goal5_EventCoordinatorMonitorEventDay.fxml"));
            Scene goal5Scene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(goal5Scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void budgetManagementButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/EventCoordinatorGoals/Goal3_EventCoordinatorManageBudgetPayments.fxml"));
            Scene goal3Scene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(goal3Scene);
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}