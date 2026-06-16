package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class GuestConnection extends com.mycompany.model.User {

    public Boolean saveGuestData(com.mycompany.model.Guest G) {
        Connection con = null;
        PreparedStatement P1 = null, P2 = null;
        try {
            con = DatabaseConnection.getConnection();
            String addUser = "INSERT INTO User(f_name, l_name, user_name, password, phone_num) VALUES (?, ?, ?, ?, ?)";
            String addGuest = "INSERT INTO Guest(user_name) VALUES (?)";

            P1 = con.prepareStatement(addUser);
            P2 = con.prepareStatement(addGuest);

            P1.setString(1, G.getFirstname());
            P1.setString(2, G.getLastname());
            P1.setString(3, G.getUsername());
            P1.setString(4, G.getPassword());
            P1.setString(5, G.getPhonenum());

            P2.setString(1, G.getUsername());
            //P2.setInt(2, G.getGuestid());

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

    public Boolean loginGuest(String user_name, String password) {
        Connection con = null;
        PreparedStatement P = null;
        ResultSet x = null;
        try {
            con = DatabaseConnection.getConnection();
            String checkUser = "SELECT *FROM User WHERE user_name = ? AND password = ?";

            P = con.prepareStatement(checkUser);

            P.setString(1, user_name);
            P.setString(2, password);

            x = P.executeQuery();
            if (x.next()) {
                System.out.println("Data found.");
                return true;
            } else {
                System.out.println("Data not found.");
                return false;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        } finally {
            try {
                if (x != null) {
                    x.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

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
