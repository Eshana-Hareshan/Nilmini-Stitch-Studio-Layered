package lk.ijse.nilmini_stitch_studio.controller;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.nilmini_stitch_studio.App;
import lk.ijse.nilmini_stitch_studio.bo.custom.LoginBO;
import lk.ijse.nilmini_stitch_studio.bo.custom.impl.LoginBOImpl;
import lk.ijse.nilmini_stitch_studio.dao.custom.LoginDAO;
import lk.ijse.nilmini_stitch_studio.dao.custom.impl.LoginDAOImpl;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    LoginBO loginBO = new LoginBOImpl();

    @FXML
    void login(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Username and Password cannot be empty!").show();
            return;
        }
        try {
            boolean acc = loginBO.checkCredentials(username, password);

            if (acc) {
                App.setRoot("Layout");
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Username or Password!").show();
            }

        } catch (SQLException e) {
            System.err.println("Database Error during login: " + e.getMessage());
            new Alert(Alert.AlertType.ERROR, "A database error occurred: Cannot connect or query the database.").show();
        }
    }

}