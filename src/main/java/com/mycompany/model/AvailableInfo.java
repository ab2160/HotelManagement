package com.mycompany.model;


public class AvailableInfo extends User{
    private int booking_id;
    private int room_num;
    private String room_status;
    private double total_amount;
    
    public AvailableInfo(int booking_id, int room_num, String room_status, double total_amount) {
        this.booking_id = booking_id;
        this.room_num = room_num;
        this.room_status = room_status;
        this.total_amount = total_amount;
    }
    
    public AvailableInfo(String user_name, String f_name, String l_name, String phone_num)
    {
        this.user_name = user_name;
        this.f_name = f_name;
        this.l_name = l_name;
        this.phone_num = phone_num;
    }

    public String getUserName()
    {
        return user_name;
    }
    
    public String getFirstName()
    {
        return f_name;
    }
    
    public String getLastName()
    {
        return l_name;
    }
    
    public String getPhoneNum()
    {
        return phone_num;
    }
    
    public int getBookingId() { return booking_id; }
    public int getRoomNum() { return room_num; }
    public String getRoomStatus() { return room_status; }
    public double getTotalAmount() { return total_amount; }
}

