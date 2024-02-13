package DAO;

import java.sql.*;
public class DatabaseHelper {
    public static Connection getConnection(){
        try {           
            Class.forName("org.sqlite.JDBC");    
            Connection con =DriverManager.getConnection("jdbc:sqlite:homelib.db");
            return con;
        } catch (Exception e) {
            System.err.println("Connection error");
            return null;
        }
    }
}
