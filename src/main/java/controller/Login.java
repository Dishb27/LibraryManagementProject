package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.User;

public class Login {
    @FXML
    private JFXTextField txtpassword;

    @FXML
    private JFXTextField txtusername;

    @FXML
    private JFXComboBox<String> usertypeselection;

    @FXML
    void initialize() {
        // Add the choices to the ComboBox
        usertypeselection.getItems().addAll("Admin", "Member");

        // Set the default value to "Admin"
        usertypeselection.setValue("Admin");
    }

    @FXML
    void loginOnAction(ActionEvent event) {
        String username = txtusername.getText();
        String password = txtpassword.getText();
        String userType = usertypeselection.getValue();

        // Validate input fields
        if (username.isEmpty() || password.isEmpty() || userType == null) {
            showAlert("Input Error", "Please fill in all fields.");
            return;
        }

        // Check user credentials
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserForLogin(username);

        if (user != null && user.getPassword().equals(password) && user.getRole().equals(userType)) {
            try {
                FXMLLoader loader;
                if (userType.equals("Admin")) {
                    loader = new FXMLLoader(getClass().getResource("/view/adminDash.fxml"));
                } else {
                    loader = new FXMLLoader(getClass().getResource("/view/userDash.fxml"));
                }

                Stage stage = (Stage) txtusername.getScene().getWindow();
                stage.setScene(new Scene(loader.load()));

                // Pass the user_id to the UserPage controller
                if (userType.equals("Member")) {
                    UserPage userPageController = loader.getController();
                    userPageController.setUserDetails(user.getUserId()); // Pass the user_id
                }

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load the dashboard.");
            }
        } else {
            showAlert("Login Failed", "Invalid username, password, or user type.");
        }
    }

    @FXML
    void onActionRegister(ActionEvent event) {
        // Implement registration logic here
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}