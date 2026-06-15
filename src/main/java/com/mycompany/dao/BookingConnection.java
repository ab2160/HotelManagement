package com.mycompany.dao;

import com.mycompany.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BookingConnection {

    public boolean check_out(Booking B) {
        try (Connection con = DatabaseConnection.getConnection()) {
            // 1. Update checkout_date
            String checkout = "UPDATE Booking SET checkout_date = CURDATE() WHERE booking_id = ?";
            PreparedStatement P1 = con.prepareStatement(checkout);
            P1.setInt(1, B.getBookingid());
            int x = P1.executeUpdate();

            // 2. Free the room
            String statusUpdate = "UPDATE Room SET room_status = 'Available' WHERE room_num = ?";
            PreparedStatement P2 = con.prepareStatement(statusUpdate);
            P2.setInt(1, B.getRoomnum());
            int y = P2.executeUpdate();

            // 3. Calculate total (room + completed services) and update Payment
            String paymentUpdate = "UPDATE Payment p "
                    + "JOIN Booking b ON p.booking_id = b.booking_id "
                    + "JOIN Room r ON b.room_num = r.room_num "
                    + "SET p.total_amount = r.room_price + ("
                    + "    SELECT IFNULL(SUM(sa.service_price),0) "
                    + "    FROM Service s "
                    + "    JOIN ServiceAdding sa ON s.service_id = sa.service_id "
                    + "    WHERE s.booking_id = b.booking_id AND s.service_status = 'Completed'"
                    + "), "
                    + "p.payment_status = 'Paid', "
                    + "p.payment_date = CURDATE() "
                    + "WHERE p.booking_id = ?";
            PreparedStatement P3 = con.prepareStatement(paymentUpdate);
            P3.setInt(1, B.getBookingid());
            int z = P3.executeUpdate();

            if (x > 0 && y > 0 && z > 0) {
                // 4. Show receipt
                String receiptQuery = "SELECT p.booking_id, p.total_amount FROM Payment p WHERE p.booking_id = ?";
                PreparedStatement P4 = con.prepareStatement(receiptQuery);
                P4.setInt(1, B.getBookingid());
                ResultSet r = P4.executeQuery();

                if (r.next()) {
                    double totalPaid = r.getDouble("total_amount");
                    System.out.println("Booking " + B.getBookingid() + " paid " + totalPaid);
                }

                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean addBooking(Booking B) {
        Connection con = null;
        PreparedStatement P1 = null, P2 = null, P3 = null, P4 = null;
        try {
            con = DatabaseConnection.getConnection();
            String bookingAdd = "INSERT INTO Booking (checkin_date, room_num) VALUES(?, ?)";

            P1 = con.prepareStatement(bookingAdd, Statement.RETURN_GENERATED_KEYS);

            java.sql.Date sqlcheckinDate = new java.sql.Date(B.getCheckin().getTime());
            P1.setDate(1, sqlcheckinDate);
            P1.setInt(2, B.getRoomnum());

            int x = P1.executeUpdate();
            if (x > 0) {
                ResultSet key = P1.getGeneratedKeys();
                if (key.next()) {
                    B.setBookingid(key.getInt(1));
                }
                // Update room status
                String statusUpdate = "UPDATE Room SET room_status = 'Occupied' WHERE room_num = ? AND room_status = 'Available'";
                P2 = con.prepareStatement(statusUpdate);
                P2.setInt(1, B.getRoomnum());
                int y = P2.executeUpdate();

                if (y > 0) {
                    // Insert Payment row with Pending status
                    String paymentInsert = "INSERT INTO Payment (booking_id, total_amount, payment_date, payment_status) "
                            + "SELECT b.booking_id, r.room_price, CURDATE(), 'Pending' "
                            + "FROM Booking b JOIN Room r ON b.room_num = r.room_num "
                            + "WHERE b.booking_id = ?;";

                    P3 = con.prepareStatement(paymentInsert);
                    P3.setInt(1, B.getBookingid());

                    int z = P3.executeUpdate();
                    if (z > 0) {
                        // Insert Guest_booking 
                        String guestBookingInsert = "INSERT INTO Guest_booking (guest_id, booking_id) VALUES (?, ?)";
                        P4 = con.prepareStatement(guestBookingInsert);
                        P4.setInt(1, B.getGuestId());
                        P4.setInt(2, B.getBookingid());
                        int w = P4.executeUpdate();
                        
                        if (w > 0) {
                            System.out.println("Booked successfully. Payment initialized as Pending.");
                            return true;
                        } else {
                            System.out.println("Failed to insert Guest_booking row.");
                            return false;
                        }
                    } else {
                        System.out.println("Payment insert failed.");
                        return false;
                    }
                } else {
                    System.out.println("Room status not updated.");
                    return false;
                }
            } else {
                System.out.println("Not Booked.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (P4 != null) {
                    P4.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            try {
                if (P3 != null) {
                    P3.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (P2 != null) {
                    P2.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (P1 != null) {
                    P1.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }
    }

    public boolean removeBooking(int bookingId, int roomNum) {
        try (Connection con = DatabaseConnection.getConnection()) {
            // 0. Remove guest_booking link
            String guestBookingRemove = "DELETE FROM Guest_booking WHERE booking_id = ?";
            PreparedStatement P0 = con.prepareStatement(guestBookingRemove);
            P0.setInt(1, bookingId);
            P0.executeUpdate();
            
            // 1. Remove all services linked to this booking
            String serviceRemove = "DELETE FROM Service WHERE booking_id = ?";
            PreparedStatement P1 = con.prepareStatement(serviceRemove);
            P1.setInt(1, bookingId);
            P1.executeUpdate();

            // 2. Remove all payments linked to this booking
            String paymentRemove = "DELETE FROM Payment WHERE booking_id = ?";
            PreparedStatement P2 = con.prepareStatement(paymentRemove);
            P2.setInt(1, bookingId);
            P2.executeUpdate();

            // 3. Delete the booking itself
            String bookingRemove = "DELETE FROM Booking WHERE booking_id = ? AND room_num = ?";
            PreparedStatement P3 = con.prepareStatement(bookingRemove);
            P3.setInt(1, bookingId);
            P3.setInt(2, roomNum);
            int rows = P3.executeUpdate();

            if (rows > 0) {
                // 4. Free up the room
                String roomUpdate = "UPDATE Room SET room_status = 'Available' WHERE room_num = ?";
                PreparedStatement P4 = con.prepareStatement(roomUpdate);
                P4.setInt(1, roomNum);
                P4.executeUpdate();

                System.out.println("Booking, services and payments removed successfully.");
                return true;
            } else {
                System.out.println("Booking not found.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
