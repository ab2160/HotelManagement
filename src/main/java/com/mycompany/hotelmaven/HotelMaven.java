package com.mycompany.hotelmaven;

import com.mycompany.model.Booking;
import com.mycompany.dao.BookingConnection;
import com.mycompany.model.Guest;
import com.mycompany.dao.GuestConnection;
import com.mycompany.model.Manager;
import com.mycompany.dao.ManagerConnection;
import com.mycompany.model.Room;
import com.mycompany.dao.RoomConnection;
import com.mycompany.model.Service;
import com.mycompany.dao.ServiceConnection;
import com.mycompany.dao.WaiterConnection;
import com.mycompany.model.Waiter;
import java.sql.Date;
import java.util.Scanner;

public class HotelMaven {

    public static void adminPage() {
        Guest G = new Guest();
        Manager M = new Manager();
        Waiter W = new Waiter();
        Booking B = new Booking();
        Service S = new Service();
        Room R = new Room();

        ManagerConnection MC = new ManagerConnection();
        GuestConnection GC = new GuestConnection();
        BookingConnection BC = new BookingConnection();
        ServiceConnection SC = new ServiceConnection();
        RoomConnection RC = new RoomConnection();
        WaiterConnection WC = new WaiterConnection();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Admin's Page");
            System.out.println("1. Register Guest");
            System.out.println("2. Register Manager");
            System.out.println("3. Register Waiter");
            System.out.println("4. Remove Guest");
            System.out.println("5. Remove Manager");
            System.out.println("6. Remove Waiter");
            System.out.println("7. Add Room");
            System.out.println("8. Add Booking");
            System.out.println("9. Remove Booking");
            System.out.println("10. Add Service");
            System.out.println("11. Remove Service");
            System.out.println("12. Check out");
            System.out.println("13. Return to Main Menu");
            System.out.print("Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    String input;
                    int x,
                     y;
                    double z;

                    System.out.print("First name: ");
                    input = sc.nextLine();
                    G.setFirstname(input);

                    System.out.print("Last name: ");
                    input = sc.nextLine();
                    G.setLastname(input);

                    System.out.print("User name: ");
                    input = sc.nextLine();
                    G.setUsername(input);

                    System.out.print("Password: ");
                    input = sc.nextLine();
                    G.setPassword(input);

                    System.out.print("Phone number: ");
                    input = sc.nextLine();
                    G.setPhonenum(input);

                    System.out.print("Role: ");
                    input = sc.nextLine();
                    G.setRole(input);

                    System.out.print("Guest Id: ");
                    x = sc.nextInt();
                    G.setGuestid(x);

                    GC.saveGuestData(G);
                    break;

                case 2:
                    System.out.print("First name: ");
                    input = sc.nextLine();
                    M.setFirstname(input);

                    System.out.print("Last name: ");
                    input = sc.nextLine();
                    M.setLastname(input);

                    System.out.print("User name: ");
                    input = sc.nextLine();
                    M.setUsername(input);

                    System.out.print("Password: ");
                    input = sc.nextLine();
                    M.setPassword(input);

                    System.out.print("Phone number: ");
                    input = sc.nextLine();
                    M.setPhonenum(input);

                    System.out.print("Role: ");
                    input = sc.nextLine();
                    M.setRole(input);

                    System.out.print("Manager Id: ");
                    x = sc.nextInt();
                    M.setManagerid(x);

                    MC.saveManagerData(M);
                    break;

                case 3:
                    System.out.print("First name: ");
                    input = sc.nextLine();
                    W.setFirstname(input);

                    System.out.print("Last name: ");
                    input = sc.nextLine();
                    W.setLastname(input);

                    System.out.print("User name: ");
                    input = sc.nextLine();
                    W.setUsername(input);

                    System.out.print("Password: ");
                    input = sc.nextLine();
                    W.setPassword(input);

                    System.out.print("Phone number: ");
                    input = sc.nextLine();
                    W.setPhonenum(input);

                    System.out.print("Role: ");
                    input = sc.nextLine();
                    W.setRole(input);

                    System.out.print("Waiter Id: ");
                    x = sc.nextInt();
                    W.setWaiterid(x);

                    WC.saveWaiterData(W);
                    break;

                case 4:
                    System.out.print("User name: ");
                    input = sc.nextLine();
                    G.setUsername(input);
                    MC.DeleteGuestData(G.getUsername());
                    break;

                case 5:
                    System.out.print("User name: ");
                    input = sc.nextLine();
                    M.setUsername(input);
                    MC.DeleteManagerData(M.getUsername());
                    break;

                case 6:
                    System.out.print("User name: ");
                    input = sc.nextLine();
                    W.setUsername(input);
                    MC.DeleteWaiterData(W.getUsername());
                    break;
                case 7:
                    System.out.print("Room number: ");
                    x = sc.nextInt();
                    R.setRoomnum(x);
                    sc.nextLine();

                    System.out.print("Room status: ");
                    input = sc.nextLine();
                    R.setRoomstatus(input);

                    System.out.print("Class name: ");
                    input = sc.nextLine();
                    R.setClassname(input);

                    System.out.print("Bed type: ");
                    input = sc.nextLine();
                    R.setBedtype(input);

                    System.out.print("Room price: ");
                    z = sc.nextDouble();
                    R.setRoomprice(z);

                    RC.addRoom(R);
                    break;
                case 8:
                    System.out.print("Booking Id: ");
                    x = sc.nextInt();
                    B.setBookingid(x);
                    sc.nextLine();

                    System.out.print("Check in date (yyyy-mm-dd): ");
                    input = sc.nextLine();
                    Date checkin = Date.valueOf(input);
                    B.setCheckin(checkin);

                    System.out.print("Room number: ");
                    x = sc.nextInt();
                    B.setRoomnum(x);

                    BC.addBooking(B);
                    break;

                case 9:
                    System.out.print("Booking Id: ");
                    x = sc.nextInt();
                    B.setBookingid(x);

                    System.out.print("Room number: ");
                    y = sc.nextInt();
                    B.setRoomnum(y);

                    BC.removeBooking(x, y);
                    break;

                case 10:
                    double p;
                    System.out.print("Service Id: ");
                    x = sc.nextInt();
                    S.setServiceid(x);
                    sc.nextLine();

                    System.out.print("Service type: ");
                    input = sc.nextLine();
                    S.setServicetype(input);

                    System.out.print("Service price: ");
                    p = sc.nextDouble();
                    S.setServiceprice(p);

                    SC.addService(S);
                    break;

                case 11:
                    System.out.print("Service Id: ");
                    x = sc.nextInt();
                    S.setServiceid(x);

                    SC.removeService(S);
                    break;

                case 12:
                    System.out.print("Booking id: ");
                    x = sc.nextInt();
                    B.setBookingid(x);

                    System.out.print("Room number: ");
                    y = sc.nextInt();
                    B.setRoomnum(y);

                    if (BC.check_out(B)) {
                        System.out.print("Check out successfully.");
                    } else {
                        System.out.print("Couldn't checkout.");
                    }
                    break;

                case 13:
                    System.out.println("Return to Main Menu");
                    break;

                default:
                    System.out.println("Incorrect choice.");
            }
        } while (choice != 13);
    }

    public static void guestPage() {
        Guest G = new Guest();
        Booking B = new Booking();
        Service S = new Service();
        GuestConnection GC = new GuestConnection();
        BookingConnection BC = new BookingConnection();
        ServiceConnection SC = new ServiceConnection();

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Guest Page");
            System.out.println("1. Register");
            System.out.println("2. Add booking");
            System.out.println("3. Remove booking");
            System.out.println("4. Add service");
            System.out.println("5. Cancel service");
            System.out.println("6. Check out");
            System.out.println("7. Return to Main Menu");

            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    String input;
                    int x,
                     y;

                    System.out.print("First name: ");
                    input = sc.nextLine();
                    G.setFirstname(input);

                    System.out.print("Last name: ");
                    input = sc.nextLine();
                    G.setLastname(input);

                    System.out.print("User name: ");
                    input = sc.nextLine();
                    G.setUsername(input);

                    System.out.print("Password: ");
                    input = sc.nextLine();
                    G.setPassword(input);

                    System.out.print("Phone number: ");
                    input = sc.nextLine();
                    G.setPhonenum(input);

                    System.out.print("Role: ");
                    input = sc.nextLine();
                    G.setRole(input);

                    System.out.print("Guest Id: ");
                    x = sc.nextInt();
                    G.setGuestid(x);

                    GC.saveGuestData(G);
                    break;

                case 2:
                    System.out.print("Booking Id: ");
                    x = sc.nextInt();
                    B.setBookingid(x);
                    sc.nextLine();

                    System.out.print("Check in date (yyyy-mm-dd): ");
                    input = sc.nextLine();
                    Date checkin = Date.valueOf(input);
                    B.setCheckin(checkin);

                    System.out.print("Room number: ");
                    x = sc.nextInt();
                    B.setRoomnum(x);

                    BC.addBooking(B);
                    break;

                case 3:
                    System.out.print("Booking Id: ");
                    x = sc.nextInt();
                    B.setBookingid(x);

                    System.out.print("Room number: ");
                    y = sc.nextInt();
                    B.setRoomnum(y);

                    BC.removeBooking(x, y);
                    break;

                case 4:
                    System.out.print("Service Id: ");
                    x = sc.nextInt();
                    S.setServiceid(x);
                    sc.nextLine();

                    System.out.print("Booking Id: ");
                    y = sc.nextInt();
                    S.setBookingid(y);

                    SC.addGuestService(y, x);
                    break;

                case 5:
                    System.out.print("Service Id: ");
                    x = sc.nextInt();
                    S.setServiceid(x);

                    System.out.print("Booking Id: ");
                    y = sc.nextInt();
                    S.setBookingid(y);

                    SC.cancelGuestService(y, x);
                    break;

                case 6:
                    System.out.print("Booking id: ");
                    x = sc.nextInt();
                    B.setBookingid(x);

                    System.out.print("Room number: ");
                    y = sc.nextInt();
                    B.setRoomnum(y);

                    if (BC.check_out(B)) {
                        System.out.print("Check out successfully.");
                    } else {
                        System.out.print("Couldn't checkout.");
                    }
                    break;

                case 7:
                    System.out.println("Return to Main Menu");
                    break;

                default:
                    System.out.println("Incorrect choice.");
            }
        } while (choice != 7);
    }

    public static void staffPage() {
        Booking B = new Booking();
        Service S = new Service();
        ServiceConnection SC = new ServiceConnection();

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Staff's Page");
            System.out.println("1. Display services");
            System.out.println("2. Complete services");
            System.out.println("3. Return to Main Menu");

            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    SC.showService();
                    break;
                case 2:
                    int x;
                    System.out.print("Service id: ");
                    x = sc.nextInt();
                    S.setServiceid(x);

                    System.out.print("Booking id: ");
                    x = sc.nextInt();
                    S.setBookingid(x);

                    SC.completeService(S.getServiceid(), S.getBookingId());
                    break;
                case 3:
                    System.out.println("Return to Main Menu");
                    break;
                default:
                    System.out.println("Incorrect choice");
            }
        } while (choice != 3);
    }

    public static void main(String[] args) {
        Guest G = new Guest();
        Manager M = new Manager();
        Waiter W = new Waiter();
        ManagerConnection MC = new ManagerConnection();
        WaiterConnection WC = new WaiterConnection();
        GuestConnection GC = new GuestConnection();

        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Hotel Management System and Billing System.");
            System.out.println("1. Admin page ");
            System.out.println("2. Guest page ");
            System.out.println("3. Staff page ");
            System.out.println("4. Exit system ");

            System.out.print("Choose your page: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    String choice1;
                    System.out.println("Admin's page");

                    System.out.print("User name: ");
                    choice1 = sc.nextLine();
                    M.setUsername(choice1);

                    System.out.print("Password: ");
                    choice1 = sc.nextLine();
                    M.setPassword(choice1);

                    if (MC.loginManager(M.getUsername(), M.getPassword())) {
                        adminPage();
                    } else {
                        System.out.print("Incorrect information.");
                    }
                    break;
                case 2:
                    guestPage();
                    break;
                case 3:
                    System.out.println("Staff's page");

                    System.out.print("User name: ");
                    choice1 = sc.nextLine();
                    W.setUsername(choice1);

                    System.out.print("Password: ");
                    choice1 = sc.nextLine();
                    W.setPassword(choice1);

                    if (WC.loginWaiter(W.getUsername(), W.getPassword())) {
                        staffPage();
                    } else {
                        System.out.print("Incorrect information.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Incorrect choice.");
                    break;
            }

        } while (choice != 4);
    }

}
