package com.example.music_band_oop.Controller.FXMLControllerForUser1;

import com.example.music_band_oop.Controller.mainuser.IssueLog;
import com.example.music_band_oop.Controller.nonuser.AppendableObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SE_Goal4_ViewController{

    @FXML private ComboBox<String> ActionComboBox;
    @FXML private ComboBox<String> ProblemTypeComboBox;
    @FXML private TextField AffectedChannelTextField;
    @FXML private TableView<IssueLog> logTableView;
    @FXML private TableColumn<IssueLog, String> channelCol;
    @FXML private TableColumn<IssueLog, String> problemCol;
    @FXML private TableColumn<IssueLog, String> actionCol;

    private ArrayList<IssueLog> logList = new ArrayList<>();

    @FXML
    public void initialize() {
        ProblemTypeComboBox.getItems().addAll("Feedback", "Noise", "Dead mic", "Distortion");
        ActionComboBox.getItems().addAll("Adjust gain/EQ", "Replace cable", "Swap mic", "Mute frequency");

        channelCol.setCellValueFactory(new PropertyValueFactory<>("channel"));
        problemCol.setCellValueFactory(new PropertyValueFactory<>("problem"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));
    }

    @FXML
    public void saveLogbuttonOnAction(ActionEvent event) {
        String channel = AffectedChannelTextField.getText();
        String problem = ProblemTypeComboBox.getValue();

        if (channel.isEmpty() || problem == null) {
            return;
        }

        String action = ActionComboBox.getValue() != null ? ActionComboBox.getValue() : "none";
        logList.add(new IssueLog("", channel, problem, action, "Resolved"));
        logTableView.getItems().clear();
        logTableView.getItems().addAll(logList);
        AffectedChannelTextField.clear();
        ProblemTypeComboBox.getSelectionModel().clearSelection();
        ActionComboBox.getSelectionModel().clearSelection();


        /// File write --------------

        try {
            File file = new File("AudioIssuesLog.bin");
            FileOutputStream fos = null;
            ObjectOutputStream oos = null;

            if (file.exists()){
                fos = new FileOutputStream(file, true);

                oos = new AppendableObjectOutputStream(fos);
                System.out.println("appendable");
            }
            else {
                fos = new FileOutputStream(file);
                System.out.println("new");
                oos = new ObjectOutputStream(fos);
            }
            oos.writeObject(logList);
            oos.close();
            System.out.println("Object saved");
        } catch (Exception e) {
            System.out.println("Not saved");;
        }

    }

    @FXML
    public void DashboardButtonOnAction(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/music_band_oop/DashboardOfUsers/SoundEngineerDashbroad.fxml"));
            Scene dashboardScene = new Scene(fxmlLoader.load());
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(dashboardScene);
            currentStage.setTitle("Sound Engineer Dashboard");
            currentStage.show();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}