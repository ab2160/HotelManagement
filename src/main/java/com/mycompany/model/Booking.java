package com.mycompany.model;

import java.util.Date;

public class Booking {

    protected int booking_id;
    protected Date checkin_date;
    protected int room_num;

    public Booking() {
    }
    
    public Booking (int booking_id, Date checkin_date, int room_num)
    {
        this.booking_id = booking_id;
        this.checkin_date = checkin_date;
        this.room_num = room_num;
    }

    public void setBookingid(int booking_id) {
        this.booking_id = booking_id;
    }

    public void setCheckin(Date checkin_date) {
        this.checkin_date = checkin_date;
    }

    public void setRoomnum(int room_num) {
        this.room_num = room_num;
    }

    public int getBookingid() {
        return booking_id;
    }

    public Date getCheckin() {
        return checkin_date;
    }

    public int getRoomnum() {
        return room_num;
    }
}
