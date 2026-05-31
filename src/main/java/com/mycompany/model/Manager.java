package com.mycompany.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Manager extends User {

    protected int manager_id;

    Date checkin_date;
    Date checkout_date;
    protected int room_num;
    protected int waiter_id;
    protected int guest_id;

    public Manager() {
    }
    
    public Manager(int manager_id, String f_name, String l_name, String user_name, String password, String phone_num, String role)
    {
        this.manager_id = manager_id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.user_name = user_name;
        this.password = password;
        this.phone_num = phone_num;
        this.role = role;
    }
    
    public Manager(int manager_id, String user_name)
    {
        this.manager_id = manager_id;
        this.user_name = user_name;
    }

    public void setManagerid(int manager_id) {
        this.manager_id = manager_id;
    }

    public void setGuestid(int guest_id) {
        this.guest_id = guest_id;

    }

    public void setWaiterid(int waiter_id) {
        this.waiter_id = waiter_id;
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

    public int getManagerid() {
        return manager_id;
    }

    public int getGuestid() {
        return guest_id;
    }

    public int getWaiterid() {
        return waiter_id;
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
}
