package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection conn;
    static Connection ConnectToDatabase() throws SQLException {
        String url = "jdbc:sqlserver://SQL8002.site4now.net;database=db_a86b63_marige";
        String user = "db_a86b63_marige_admin";
        String password = "pgsR67lG";

        try
                (Connection conn = DriverManager.getConnection(url, user, password))
        {
            System.out.println(conn); //simply used to check that the connection works.
        }

        return conn;
    }
}
