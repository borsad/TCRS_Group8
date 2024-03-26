package com.example.tcrs_group8.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Utils {

    DBConnector con = new DBConnector();

    private String query;
    public int insert(String tableName,String values){
        try{
            Statement stmt = DBConnector.getConnection().createStatement();
            String query = "insert into "+ tableName +" values ("+values+")";
            int result= stmt.executeUpdate(query);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
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

    public int update(String tableName,String set, String where){

        try{
            Statement stmt = DBConnector.getConnection().createStatement();
            query= "UPDATE " + tableName+
                    " SET "+set+ " WHERE "+ where;
            return stmt.executeUpdate(query);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int delete(String tableName,String condition){

        try{
            Statement stmt = DBConnector.getConnection().createStatement();
            query= "DELETE FROM "+tableName+" WHERE "+condition;
            return stmt.executeUpdate(query);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Utils(){}
}
