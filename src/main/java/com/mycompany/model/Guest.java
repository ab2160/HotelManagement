package com.mycompany.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.sql.ResultSet;

public class Guest extends User {

    protected int guest_id;
    int booking_id;
    Date checkin_date;
    Date checkout_date;
    int room_num;

    public Guest() {
    }
    public Guest(int guest_id, String f_name, String l_name, String user_name, String password, String phone_num)
    {
        this.guest_id = guest_id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.user_name = user_name;
        this.password = password;
        this.phone_num = phone_num;
    }
    
    public Guest(String user_name, String password)
    {
        this.user_name = user_name;
        this.password = password;
    }
    
    public Guest(int guest_id, String user_name)
    {
        this.guest_id = guest_id;
        this.user_name = user_name;
    }

    public void setUsername(String user_name) {
        this.user_name = user_name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String f_name) {
        this.f_name = f_name;
    }

    public void setLastname(String l_name) {
        this.l_name = l_name;
    }

    public void setPhonenum(String phone_num) {
        this.phone_num = phone_num;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setGuestid(int guest_id) {
        this.guest_id = guest_id;
    }

    public String getUsername() {
        return user_name;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstname() {
        return f_name;
    }

    public String getLastname() {
        return l_name;
    }

    public String getPhonenum() {
        return phone_num;
    }

    public String getRole() {
        return role;
    }

    public int getGuestid() {
        return guest_id;
    }
}
