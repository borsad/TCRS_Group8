package com.example.tcrs_group8;
import java.sql.*;

public class DBConnector {
    public static final String username = "sql5692985";
    public static final String password = "DTZc8zg7xe";
    public static final String dbURL = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5692985";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbURL, password, username);
    }
}