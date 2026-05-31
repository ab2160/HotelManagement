package com.mycompany.dao;

import com.mycompany.model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomConnection {

    public boolean addRoom(Room R) {
        Connection con = null;
        PreparedStatement P = null;
        try {
            con = DatabaseConnection.getConnection();
            String roomAdd = "INSERT INTO Room(room_status, class_name, bed_type, room_price) VALUES(?, ?, ?, ?)";
            P = con.prepareStatement(roomAdd);

            P.setString(1, R.getRoomstatus());
            P.setString(2, R.getClassname());
            P.setString(3, R.getBedtype());
            P.setDouble(4, R.getRoomprice());

            int x = P.executeUpdate();
            if (x > 0) {
                System.out.println("Room added successfully");
                return true;
            } else {
                System.out.println("Room not added");
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

    public boolean removeRoom(int roomNum) {
        String delete = "DELETE FROM Room WHERE room_num = ?";

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(delete)) {

            ps.setInt(1, roomNum);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Room removed successfully");
                return true;
            } else {
                System.out.println("Room not found or not removed");
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
