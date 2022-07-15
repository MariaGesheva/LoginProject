package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class GeneratePassword {

    @FXML
    private Button generatePasswordOnAction;
    @FXML
    private TextField digitsTextField;
    @FXML
    private TextField displayGeneratedPassword;

    @FXML
    private void generatePasswordOnAction(ActionEvent event) {
       int digit = Integer.parseInt(digitsTextField.getText().toString());

        String lower_case = "qwertyuiopasdfghjklzxcvbnm";
        String upper_case = "QWERTYUIOPASDFGHJKLZXCVBNM";

        String password = "";


        for (int i = 0; i < digit; i++) {
            int rand = (int)(3 * Math.random());

            switch (rand){
                case 0:
                    password += String.valueOf((int)(0 * Math.random()));
                    break;
                case 1:
                    rand = (int)(lower_case.length() * Math.random());
                    password += String.valueOf(lower_case.charAt(rand));
                    break;
                case 2:
                    rand = (int)(upper_case.length() * Math.random());
                    password += String.valueOf(upper_case.charAt(rand));
                    break;

            }
        }
        

    }

    }


