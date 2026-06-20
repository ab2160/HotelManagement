package com.mycompany.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class ManagerConnection {

    public Boolean saveManagerData(com.mycompany.model.Manager M) {
        Connection con = null;
        PreparedStatement P1 = null, P2 = null, P3 = null, P4 = null;
        try {
            con = DatabaseConnection.getConnection();
            String addUser = "INSERT INTO User (f_name, l_name, user_name, password, phone_num, role) VALUES (?, ?, ?, ?, ?, ?)";

            P1 = con.prepareStatement(addUser);
            P1.setString(1, M.getFirstname());
            P1.setString(2, M.getLastname());
            P1.setString(3, M.getUsername());
            P1.setString(4, M.getPassword());
            P1.setString(5, M.getPhonenum());
            P1.setString(6, M.getRole());

            int x = P1.executeUpdate();

            if ("Manager".equalsIgnoreCase(M.getRole())) {
                String addManager = "INSERT INTO Manager (user_name) VALUES (?)";
                P2 = con.prepareStatement(addManager);
                P2.setString(1, M.getUsername());
//                P2.setInt(2, M.getManagerid());

                int y = P2.executeUpdate();
                if (y > 0) {
                    System.out.println("Manager Data Saved");
                }

            } else if ("Waiter".equalsIgnoreCase(M.getRole())) {
                String addWaiter = "INSERT INTO Waiter (user_name) VALUES (?)";
                P3 = con.prepareStatement(addWaiter);
                P3.setString(1, M.getUsername());
//                P3.setInt(2, M.getWaiterid());

                int z = P3.executeUpdate();
                if (z > 0) {
                    System.out.println("Waiter Data Saved");
                }

            } else if ("Guest".equalsIgnoreCase(M.getRole())) {
                String addGuest = "INSERT INTO Guest (user_name) VALUES (?)";
                P4 = con.prepareStatement(addGuest);
                P4.setString(1, M.getUsername());
//                P4.setInt(2, M.getGuestid());

                int w = P4.executeUpdate();
                if (w > 0) {
                    System.out.println("Guest Data Saved");
                }
            }

            if (x > 0) {
                System.out.println("Data saved");
                return true;
            } else {
                System.out.println("Data not saved");
                return false;
            }

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        } finally {
            try {
                if (P4 != null) {
                    P4.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            try {
                if (P3 != null) {
                    P3.close();
                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

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

    public Boolean loginManager(String user_name, String password) {
        Connection con = null;
        PreparedStatement P = null;
        ResultSet x = null;
        try {
            con = DatabaseConnection.getConnection();
            String checkUser = "SELECT * FROM User WHERE user_name = ? AND password = ? AND role = 'Manager'";
            P = con.prepareStatement(checkUser);
            P.setString(1, user_name);
            P.setString(2, password);

            x = P.executeQuery();

            if (x.next()) {
                System.out.println("Manager Data found");
                return true;
            } else {
                System.out.println("Manager Data not found.");
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

    public Boolean DeleteGuestData(String user_name) {
        Connection con = null;
        PreparedStatement P1 = null, P2 = null;
        try {
            con = DatabaseConnection.getConnection();
            String removeGuest = "DELETE FROM Guest WHERE user_name = ?";
            String removeUser = "DELETE FROM User WHERE user_name = ?";

            P1 = con.prepareStatement(removeGuest);
            P2 = con.prepareStatement(removeUser);
            P1.setString(1, user_name);
            P2.setString(1, user_name);

            int x = P1.executeUpdate();
            int y = P2.executeUpdate();
            if (x > 0 && y > 0) {
                System.out.println("Data Removed successfully.");
                return true;
            } else {
                System.out.println("Data not Removed.");
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

    public Boolean DeleteManagerData(String user_name) {
        Connection con = null;
        PreparedStatement P1 = null, P2 = null;

        try {
            con = DatabaseConnection.getConnection();
            String removeManager = "DELETE FROM Manager WHERE user_name = ?";
            String removeUser = "DELETE FROM User WHERE user_name = ?";

            P1 = con.prepareStatement(removeManager);
            P2 = con.prepareStatement(removeUser);

            P1.setString(1, user_name);

            P2.setString(1, user_name);

            int x = P1.executeUpdate();
            int y = P2.executeUpdate();

            if (x > 0 && y > 0) {
                System.out.println("Manager Data Removed successfully.");
                return true;
            } else {
                System.out.println("Manager Data not Removed.");
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

    public Boolean DeleteWaiterData(String user_name) {
        Connection con = null;
        PreparedStatement P1 = null, P2 = null;
        try {
            con = DatabaseConnection.getConnection();
            String removeWaiter = "DELETE FROM Waiter WHERE user_name = ?";
            String removeUser = "DELETE FROM User WHERE user_name = ?";

            P1 = con.prepareStatement(removeWaiter);
            P2 = con.prepareStatement(removeUser);
            P1.setString(1, user_name);
            P2.setString(1, user_name);

            int x = P1.executeUpdate();
            int y = P2.executeUpdate();
            if (x > 0 && y > 0) {
                System.out.println("Data Removed successfully.");
                return true;
            } else {
                System.out.println("Data not Removed.");
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
}
