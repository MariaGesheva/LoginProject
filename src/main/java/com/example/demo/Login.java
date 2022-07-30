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
import java.sql.*;
import java.util.Objects;

public class Login {

    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField username;
    @FXML
    private TextField digitsTextField;
    @FXML
    private TextField displayGeneratedPassword;
    @FXML
    private Label generateMessage;
    @FXML
    private PasswordField password;


    @FXML
    public void loginButtonOnAction(ActionEvent event) throws SQLException {

        if (username.getText().isBlank() == false && password.getText().isBlank() == false) {
            validateLogin();

        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            loginMessage.setText("Fields cannot be empty!");
            if (username.getText().isEmpty()) {
                loginMessage.setText("Username empty");
            } else {
                loginMessage.setText("Password empty");
            }
        } else {
            loginMessage.setText("Wrong username or password!");
        }
    }


    @FXML
    private void validateLogin() throws SQLException {
        new DatabaseConnection();
        DatabaseConnection DBconnect = new DatabaseConnection();
        String x = DBconnect.ConnectToDatabase(username.getText(),password.getText());
        loginMessage.setText(x);
    }

    @FXML
    private void cancelButtonOnAction(ActionEvent event) {
        Stage stg = (Stage) cancelButton.getScene().getWindow();
        stg.close();
    }

    @FXML
    public void generatePasswordOnAction() throws IOException {


        //String randPassword = null; //part of Ascii version
        StringBuilder randPassword = null;
        if (!digitsTextField.getText().isBlank()) {
            int digit = Integer.parseInt(digitsTextField.getText());

//// ASCII version:
//            randPassword = "";
//            for (int i = 0; i < digit; i++) {
//                int rand = (int) (Math.random() * 62);
//
//                if (rand <= 9) {
//                    int ascii = rand + 48;
//                    return (char) (ascii);
//                } else if (rand <= 35) {
//                    int ascii = rand + 55;
//                    return (char) (ascii);
//                } else {
//                    int ascii = rand + 61;
//                    return (char) (ascii);
//                }
//            }

            // randPassword = null; //part of Ascii version
            String lower_case = "qwertyuiopasdfghjklzxcvbnm";
            String upper_case = "QWERTYUIOPASDFGHJKLZXCVBNM";
            randPassword = new StringBuilder();

            for (int i = 0; i < digit; i++) {
                int rand = (int) (3 * Math.random());  // goes from 0 to 2

                switch (rand) {
                    case 0 -> randPassword.append((int) (0 * Math.random()));
                    case 1 -> {
                        rand = (int) (lower_case.length() * Math.random());  // case for lowercase letters.
                        randPassword.append((lower_case.charAt(rand)));      // casts digit int and turns it into a sting
                    }
                    case 2 -> {
                        rand = (int) (upper_case.length() * Math.random());  // case for uppercase letters
                        randPassword.append((upper_case.charAt(rand)));      //casts digit int and turns it into a sting
                    }
                }
            }
        } else {
            generateMessage.setText("Please enter data!");
        }
        displayGeneratedPassword.setText(String.valueOf(randPassword));
//        return 0 ; //part of Ascii version
    }
    }
