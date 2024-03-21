package com.example.tcrs_group8.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {

    DBConnector con = new DBConnector();
    private String query;
    public void insert(){

    }

    public ResultSet getData(String tableName){
        try {
            Statement stmt = DBConnector.getConnection().createStatement();
            query = "select * from " + tableName;
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Utils(){}
}
