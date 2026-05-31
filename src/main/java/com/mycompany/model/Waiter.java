package com.mycompany.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Waiter extends User {

    protected int waiter_id;
    int service_id;
    String service_type;
    double service_price;
    int booking_id;

    public Waiter() {
    }
    
        public Waiter(int waiter_id, String f_name, String l_name, String user_name, String password, String phone_num, String role)
    {
        this.waiter_id = waiter_id;
        this.f_name = f_name;
        this.l_name = l_name;
        this.user_name = user_name;
        this.password = password;
        this.phone_num = phone_num;
        this.role = role;
    }
        
    public Waiter(int waiter_id, String user_name)
    {
        this.waiter_id = waiter_id;
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

    public void setWaiterid(int waiter_id) {
        this.waiter_id = waiter_id;
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

    public int getWaiterid() {
        return waiter_id;
    }
}
