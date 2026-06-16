package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://192.168.43.21:3306/hotel_management", "kalab", "1234");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
