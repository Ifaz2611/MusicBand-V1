package com.example.music_band_oop;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.Toolkit;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.prefs.Preferences;
import java.util.regex.Pattern;

public class loginController {

    @FXML private ComboBox<String> UserTypeComboBox;
    @FXML private TextField UserIdTextField;
    @FXML private PasswordField userPasswordField;
    @FXML private TextField visiblePasswordField;
    @FXML private Button togglePasswordBtn;
    @FXML private CheckBox rememberMeCheckBox;
    @FXML private Button signInButton;
    @FXML private Label statusLabel;
    @FXML private Label capsLockLabel;
    @FXML private ProgressIndicator progressIndicator;

    private boolean passwordVisible = false;

    private final Preferences prefs = Preferences.userNodeForPackage(loginController.class);
    private static final Pattern USER_ID_PATTERN = Pattern.compile("^(\\d{4}|\\d{7})$");

    private final Map<String, Integer> failedAttempts = new HashMap<>();
    private final Map<String, LocalDateTime> lockoutExpiry = new HashMap<>();

    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCKOUT_MINUTES = 15;

    @FXML
    public void initialize() {

        UserTypeComboBox.getItems().addAll("Sound Engineer", "Event Coordinator", "Lead Guitarist" , "Drummer" , "Bassist" , "Keyboardist" , "Lead Vocalist / Frontman" , "Backing Vocalist" , "DJ / Electronic Setup Tech" );

        // Restore saved login
        String savedId = prefs.get("remembered_user_id", "");
        String savedRole = prefs.get("remembered_user_role", "");

        if (!savedId.isEmpty()) {
            UserIdTextField.setText(savedId);
            rememberMeCheckBox.setSelected(true);
        }

        if (!savedRole.isEmpty() && UserTypeComboBox.getItems().contains(savedRole)) {
            UserTypeComboBox.setValue(savedRole);
        }

        // Sync password fields (fixes toggle bug)
        visiblePasswordField.textProperty().bindBidirectional(userPasswordField.textProperty());

        // Enter key handling
        UserIdTextField.setOnKeyPressed(this::handleEnterKey);
        userPasswordField.setOnKeyPressed(this::handleEnterKey);
        visiblePasswordField.setOnKeyPressed(this::handleEnterKey);
        UserTypeComboBox.setOnKeyPressed(this::handleEnterKey);

        // Caps Lock detection (FIXED)
        Platform.runLater(() -> {
            Scene scene = UserIdTextField.getScene();
            if (scene != null) {
                scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
                    boolean capsOn = Toolkit.getDefaultToolkit()
                            .getLockingKeyState(java.awt.event.KeyEvent.VK_CAPS_LOCK);
                    capsLockLabel.setVisible(capsOn);
                });
            }
            UserIdTextField.requestFocus();
        });
    }

    private void handleEnterKey(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            onSignInButtonClick();
        }
    }

    @FXML
    public void onSignInButtonClick() {

        clearStatus();

        String userId = UserIdTextField.getText() != null ? UserIdTextField.getText().trim() : "";
        String userType = UserTypeComboBox.getValue();
        String password = getPassword();

        // Validation
        if (userType == null || userType.isEmpty()) {
            showError("Please select a User Role.");
            return;
        }

        if (!USER_ID_PATTERN.matcher(userId).matches()) {
            showError("User ID must be exactly 4 or 7 digits.");
            return;
        }

        if (password.isEmpty()) {
            showError("Password cannot be empty.");
            return;
        }

        if (isAccountLocked(userId)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            showError("Account locked until " + lockoutExpiry.get(userId).format(formatter));
            return;
        }

        setLoading(true);

        // Use JavaFX Task (FIXED threading)
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {

                Thread.sleep(700);

                if (!authenticate(userId, password, userType)) {
                    recordFailedAttempt(userId);

                    Platform.runLater(() -> {
                        showError("Invalid credentials.");
                        shakeNode(signInButton);
                    });
                    return null;
                }

                resetFailedAttempts(userId);

                Platform.runLater(() -> {

                    if (rememberMeCheckBox.isSelected()) {
                        prefs.put("remembered_user_id", userId);
                        prefs.put("remembered_user_role", userType);
                    } else {
                        prefs.remove("remembered_user_id");
                        prefs.remove("remembered_user_role");
                    }

                    showSuccess("Login successful! Redirecting...");

                    PauseTransition pause = new PauseTransition(Duration.millis(900));
                    pause.setOnFinished(e -> navigateToDashboard(userType));
                    pause.play();
                });

                return null;
            }

            @Override
            protected void succeeded() {
                setLoading(false);
            }

            @Override
            protected void failed() {
                setLoading(false);
                showError("System error occurred.");
            }
        };

        new Thread(task).start();
    }

    private boolean authenticate(String userId, String password, String userType) {

        // Demo credentials (you should NOT keep this in real apps)
        if (userType.equals("Sound Engineer")) {
            return userId.equals("1111") && password.equals("1111");
        }

        if (userType.equals("Event Coordinator")) {
            return userId.equals("2222") && password.equals("2222");
        }

        return false;
    }

    private void navigateToDashboard(String userType) {

        try {

            String fxmlFile = switch (userType) {
                case "Event Coordinator" -> "DashboardOfUsers/EventCoordinatorDashbroad.fxml";
                case "Sound Engineer" -> "DashboardOfUsers/SoundEngineerDashbroad.fxml";
                default -> null;
            };

            if (fxmlFile == null) {
                showError("Dashboard not available.");
                return;
            }

            Stage stage = (Stage) signInButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));

            stage.setScene(new Scene(root));
            stage.setTitle(userType + " Dashboard");
//            stage.setMaximized(true);
            stage.show();

        } catch (IOException e) {
            showError("Failed to load dashboard.");
        }
    }

    private void recordFailedAttempt(String userId) {

        int attempts = failedAttempts.getOrDefault(userId, 0) + 1;
        failedAttempts.put(userId, attempts);

        if (attempts >= MAX_ATTEMPTS) {
            lockoutExpiry.put(userId, LocalDateTime.now().plusMinutes(LOCKOUT_MINUTES));
        }
    }

    private void resetFailedAttempts(String userId) {
        failedAttempts.remove(userId);
        lockoutExpiry.remove(userId);
    }

    private boolean isAccountLocked(String userId) {

        if (!lockoutExpiry.containsKey(userId)) return false;

        if (LocalDateTime.now().isAfter(lockoutExpiry.get(userId))) {
            lockoutExpiry.remove(userId);
            failedAttempts.remove(userId);
            return false;
        }

        return true;
    }

    @FXML
    public void togglePasswordVisibility() {

        passwordVisible = !passwordVisible;

        visiblePasswordField.setVisible(passwordVisible);
        userPasswordField.setVisible(!passwordVisible);

        togglePasswordBtn.setText(passwordVisible ? "Hide" : "Show");
    }

    @FXML
    public void handleForgotPassword() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password Recovery");
        alert.setHeaderText("Contact System Administrator");
        alert.setContentText(
                "Please contact IT at ext. 4040 or admin@musicband.com to reset your password."
        );
        alert.showAndWait();
    }

    private String getPassword() {
        return passwordVisible ? visiblePasswordField.getText() : userPasswordField.getText();
    }

    private void setLoading(boolean loading) {

        signInButton.setDisable(loading);
        signInButton.setText(loading ? "Authenticating..." : "Sign In");

        progressIndicator.setVisible(loading);

        UserTypeComboBox.setDisable(loading);
        UserIdTextField.setDisable(loading);
        userPasswordField.setDisable(loading);
        visiblePasswordField.setDisable(loading);
        rememberMeCheckBox.setDisable(loading);
    }

    private void showError(String msg) {

        statusLabel.setText("⚠ " + msg);
        statusLabel.setTextFill(Color.web("#7f1d1d"));
        statusLabel.setStyle("-fx-background-color: #fecaca; -fx-padding: 10; -fx-background-radius: 6;");
        statusLabel.setVisible(true);
    }

    private void showSuccess(String msg) {

        statusLabel.setText("✓ " + msg);
        statusLabel.setTextFill(Color.web("#14532d"));
        statusLabel.setStyle("-fx-background-color: #bbf7d0; -fx-padding: 10; -fx-background-radius: 6;");
        statusLabel.setVisible(true);
    }

    private void clearStatus() {
        statusLabel.setVisible(false);
    }

    private void shakeNode(Node node) {

        TranslateTransition tt = new TranslateTransition(Duration.millis(60), node);
        tt.setFromX(0);
        tt.setByX(12);
        tt.setCycleCount(5);
        tt.setAutoReverse(true);
        tt.play();
    }
}