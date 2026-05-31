package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel_management", "root", "7427421");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
