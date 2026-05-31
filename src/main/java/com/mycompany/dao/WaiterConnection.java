package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class WaiterConnection {

    public Boolean saveWaiterData(com.mycompany.model.Waiter W) {
        Connection con = null;
        PreparedStatement P1 = null, P2 = null;
        try {
            con = DatabaseConnection.getConnection();
            String addUser = "INSERT INTO User(f_name, l_name, user_name, password, phone_num) VALUES (?, ?, ?, ?, ?)";
            String addGuest = "INSERT INTO Waiter(user_name, waiter_id) VALUES (?, ?)";

            P1 = con.prepareStatement(addUser);
            P2 = con.prepareStatement(addGuest);

            P1.setString(1, W.getFirstname());
            P1.setString(2, W.getLastname());
            P1.setString(3, W.getUsername());
            P1.setString(4, W.getPassword());
            P1.setString(5, W.getPhonenum());

            P2.setString(1, W.getUsername());
            P2.setInt(2, W.getWaiterid());

            int x = P1.executeUpdate();
            int y = P2.executeUpdate();
            if (x > 0 && y > 0) {
                System.out.println("Data saved.");
                return true;
            } else {
                System.out.println("Data not saved.");
                return false;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        } finally {
            try {
                if (P2 != null) {
                    P2.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            try {
                if (P1 != null) {
                    P1.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }

    public Boolean loginWaiter(String user_name, String password) {
        Connection con = null;
        PreparedStatement P = null;
        try {
            con = DatabaseConnection.getConnection();
            String checkWaiter = "SELECT * FROM User WHERE user_name = ? AND password = ? AND role = 'Waiter'";

            P = con.prepareStatement(checkWaiter);
            P.setString(1, user_name);
            P.setString(2, password);

            ResultSet x = P.executeQuery();

            if (x.next()) {
                System.out.println("Waiter Data found");
                return true;
            } else {
                System.out.println("Waiter Data not found");
                return false;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        } finally {
            try {
                if (P != null) {
                    P.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }
    }
}
