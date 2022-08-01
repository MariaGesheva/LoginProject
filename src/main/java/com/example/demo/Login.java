package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;


public class Login {

    @FXML
    private Button loginButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button generatePassword;
    @FXML
    private Label loginMessage;
    @FXML
    private Label generateMessage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField digitsTextField;
    @FXML
    private TextField displayGeneratedPassword;

    @FXML
    private ImageView monzoLogo;

    //  isEmpty() method returns true if, and only if, string length is 0.
    //  isBlank() method only checks for non-whitespace characters. It does not check the string length


    // method for checking if the password and email are in the database by calling the validateLogin method.
    // if statement for making sure fields are not empty and displaying a message if data does not match database info
    @FXML
    public void loginButtonOnAction(ActionEvent event) throws SQLException {
        if (username.getText().isBlank() == false && password.getText().isBlank() == false) {
            validateLogin();

        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            loginMessage.setText("Fields cannot be empty!");
            if (username.getText().isEmpty()) {
                loginMessage.setText("Username empty");
            }
            else {
                loginMessage.setText("Password empty");
            }
        } else {
            loginMessage.setText("Wrong username or password!");
        }
    }

    //calling connection method from DatabaseConnection class. SQL query converted to a string in order to run
    @FXML
    private void validateLogin() throws SQLException {
       // new DatabaseConnection();
        DatabaseConnection DBconnect = new DatabaseConnection();
       String x= DBconnect.ConnectToDatabase(username.getText(),password.getText());
       loginMessage.setText(x);
    }


    @FXML
    private void cancelButtonOnAction(ActionEvent event) {
        Stage stg = (Stage) cancelButton.getScene().getWindow();
        stg.close();
    }


    // Using Math.random to generate random combination of numbers and letters,
    // the length of which is set by the user by using the digit text field.
    // If statements checks for empty fields if not runs the for loop
    // using
    @FXML
    public void generatePasswordOnAction() throws IOException {

        StringBuilder randPassword = null;
        if (digitsTextField.getText().isBlank() == false) {
            int digit = Integer.parseInt(digitsTextField.getText());

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
    }

    }
