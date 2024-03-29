package com.example.tcrs_group8.Services;
import java.sql.*;

public class DBConnector {
    public static Connection con;
    public static final String username = "sql5694787";
    public static final String password = "1SV7AX3clq";
    public static final String dbURL = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5694787";
    public static Connection getConnection() throws SQLException {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbURL, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    public DBConnector() {

    }
}