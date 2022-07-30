package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Login {

    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button sendToGeneratePassword;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Scene scene;
    @FXML
    private Stage stage;
    @FXML
    private Parent root;

    @FXML
    public void loginButtonOnAction(ActionEvent event) throws SQLException {
        loginMessage.setText("Fields cannot be empty!");
        if (username.getText().isBlank() == false && password.getText().isBlank() == false) {
            validateLogin();

        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            loginMessage.setText("Fields cannot be empty!");
        } else {
            loginMessage.setText("Wrong username or password!");
        }
    }

    @FXML
    private void validateLogin() throws SQLException {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.ConnectToDatabase();

        String verifyLogin = "SELECT count(1) FROM LoginProject WHERE username = '" + username.getText() + "' AND password = '" + password.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next()) {
                if (queryResult.getInt(1) == 1) {
                    loginMessage.setText("Welcome!");
                } else {
                    loginMessage.setText("Invalid login! Please try again!");
                }
            }
        } catch (Exception e){ e.printStackTrace();}
    }


    @FXML
    private void cancelButtonOnAction(ActionEvent event) {
        Stage stg = (Stage) cancelButton.getScene().getWindow();
        stg.close();
    }

   @FXML
    private void generatePasswordButton(ActionEvent event) throws IOException {
//       root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("generatePassword.fxml")));
//       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//       scene = new Scene(root);
//       stage.setScene(scene);
//       stage.show();
       new FXMLLoader(GeneratePassword.class.getResource("generatePassword.fxml"));
       Stage stg = (Stage) sendToGeneratePassword.getScene().getWindow();
       scene = new Scene(root);
       stg.setScene(scene);
       stg.show();


    }
}