package com.mycompany.dao;

import com.mycompany.model.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiceConnection {

    public Boolean addService(Service S) {
        Connection con = null;
        PreparedStatement P1 = null;
        try {
            con = DatabaseConnection.getConnection();
            String serviceAdd = "INSERT INTO ServiceAdding (service_type, service_price) VALUES(?, ?)";

            P1 = con.prepareStatement(serviceAdd);

            P1.setString(1, S.getServicetype());
            P1.setDouble(2, S.getServicePrice());

            int x = P1.executeUpdate();
            if (x > 0) {
                System.out.println("Service Added successfully.");
                return true;

            } else {
                System.out.println("Service not Added.");
                return false;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        } finally {
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

    public Boolean removeService(Service S) {
        Connection con = null;
        PreparedStatement P1 = null;
        try {
            con = DatabaseConnection.getConnection();
            String serviceRemove = "DELETE FROM ServiceAdding WHERE service_id = ?";
            P1 = con.prepareStatement(serviceRemove);
            P1.setInt(1, S.getServiceid());

            int x = P1.executeUpdate();
            if (x > 0) {
                System.out.println("Service removed successfully.");
                return true;
            } else {
                System.out.println("Service not removed");
                return false;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        } finally {
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

    public boolean addGuestService(int bookingId, int serviceId) {
        try (Connection con = DatabaseConnection.getConnection()) {

            String serviceInsert = "INSERT INTO Service (service_id, booking_id, service_status) "
                    + "SELECT service_id, ?, 'Pending' FROM ServiceAdding WHERE service_id = ?";
            PreparedStatement P1 = con.prepareStatement(serviceInsert);
            P1.setInt(1, bookingId);
            P1.setInt(2, serviceId);
            int rows = P1.executeUpdate();

            if (rows > 0) {

                String paymentInsert = "INSERT INTO Payment (booking_id, total_amount, payment_date, payment_status) "
                        + "SELECT ?, s.service_price, CURDATE(), 'Pending' "
                        + "FROM ServiceAdding s WHERE s.service_id = ?";
                PreparedStatement P2 = con.prepareStatement(paymentInsert);
                P2.setInt(1, bookingId);
                P2.setInt(2, serviceId);
                P2.executeUpdate();

                System.out.println("Service added and payment initialized as Pending.");
                return true;
            } else {
                System.out.println("Service not added.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean cancelGuestService(int bookingId, int serviceId) {
        try (Connection con = DatabaseConnection.getConnection()) {
            // Cancel service
            String serviceCancel = "UPDATE Service SET service_status = 'Cancelled' WHERE booking_id = ? AND service_id = ?";
            PreparedStatement P1 = con.prepareStatement(serviceCancel);
            P1.setInt(1, bookingId);
            P1.setInt(2, serviceId);
            int x = P1.executeUpdate();

            if (x > 0) {
                // Remove payment row for this service
                String paymentCancel = "DELETE FROM Payment WHERE booking_id = ? AND total_amount = ("
                        + "SELECT service_price FROM ServiceAdding WHERE service_id = ?)";
                PreparedStatement P2 = con.prepareStatement(paymentCancel);
                P2.setInt(1, bookingId);
                P2.setInt(2, serviceId);
                P2.executeUpdate();

                System.out.println("Service cancelled and payment removed.");
                return true;
            } else {
                System.out.println("Service not found or not cancelled.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showService() {
        try (Connection con = DatabaseConnection.getConnection()) {
            String serviceShow = "SELECT s.service_id, sa.service_type, sa.service_price, s.service_status, b.booking_id, b.room_num "
                    + "FROM Service s "
                    + "JOIN ServiceAdding sa ON s.service_id = sa.service_id "
                    + "JOIN Booking b ON s.booking_id = b.booking_id "
                    + "WHERE s.service_status = 'Pending'";

            PreparedStatement P = con.prepareStatement(serviceShow);
            ResultSet r = P.executeQuery();

            System.out.printf("%-12s %-20s %-12s %-12s %-12s %-12s%n",
                    "Service ID", "Service Type", "Price", "Status", "Booking ID", "Room Num");
            System.out.println("------------------------------------------------------------------------------------");

            while (r.next()) {
                System.out.printf("%-12d %-20s %-12.2f %-12s %-12d %-12d%n",
                        r.getInt("service_id"),
                        r.getString("service_type"),
                        r.getDouble("service_price"),
                        r.getString("service_status"),
                        r.getInt("booking_id"),
                        r.getInt("room_num"));
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    public Boolean completeService(int serviceId, int bookingId) {
        try (Connection con = DatabaseConnection.getConnection()) {
            // 1. Mark service as completed
            String serviceComplete = "UPDATE Service SET service_status = 'Completed' WHERE service_id = ? AND booking_id = ?";
            PreparedStatement P1 = con.prepareStatement(serviceComplete);
            P1.setInt(1, serviceId);
            P1.setInt(2, bookingId);
            int x = P1.executeUpdate();

            if (x > 0) {
                System.out.println("Service completed successfully.");

                // 2. Show updated service info
                String updateShow = "SELECT s.service_id, sa.service_type, sa.service_price, s.service_status, b.booking_id, b.room_num "
                        + "FROM Service s "
                        + "JOIN ServiceAdding sa ON s.service_id = sa.service_id "
                        + "JOIN Booking b ON s.booking_id = b.booking_id "
                        + "WHERE s.service_id = ? AND b.booking_id = ?";
                PreparedStatement P2 = con.prepareStatement(updateShow);
                P2.setInt(1, serviceId);
                P2.setInt(2, bookingId);

                ResultSet r = P2.executeQuery();

                System.out.printf("%-12s %-20s %-12s %-12s %-12s %-12s%n",
                        "Service ID", "Service Type", "Price", "Status", "Booking ID", "Room Num");
                System.out.println("------------------------------------------------------------------------------------");

                while (r.next()) {
                    System.out.printf("%-12d %-20s %-12.2f %-12s %-12d %-12d%n",
                            r.getInt("service_id"),
                            r.getString("service_type"),
                            r.getDouble("service_price"),
                            r.getString("service_status"),
                            r.getInt("booking_id"),
                            r.getInt("room_num"));
                }

                return true;
            } else {
                System.out.println("Service not found or already completed.");
                return false;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }
}
