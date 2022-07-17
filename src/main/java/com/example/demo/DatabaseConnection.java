package com.example.demo;
import javafx.scene.control.TextField;

//import java.awt.*;
import java.sql.*;

public class DatabaseConnection {
    public static Connection conn;

    public String ConnectToDatabase(String username, String password) throws SQLException {
        String url = "jdbc:sqlserver://SQL8002.site4now.net;database=db_a86b63_marige";
        String user = "db_a86b63_marige_admin";
        String PWD = "pgsR67lG";

        try
                (Connection conn = DriverManager.getConnection(url, user, PWD)) {
            String verifyLogin = "SELECT count(1) FROM LoginProject WHERE username = '" + username + "' AND password = '" + password + "'";
           // String insertLogin = "INSERT INTO LoginProject (username, password) VALUES '" + username + "'AND password'" + password + "'";
            try {
                Statement statement = conn.createStatement();
                ResultSet queryResult = statement.executeQuery(verifyLogin);

                while (queryResult.next()) {
                    if (queryResult.getInt(1) == 1) {
                        return "Welcome!";
                    } else {
                        return "Invalid login! Please try again!";

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Invalid login! Please try again";
    }
}
