package com.mycompany.model;

import java.util.Date;

public class Booking {

    private int booking_id;
    private Date checkin_date;
    private int room_num;
    private int guest_id;

    public Booking() {
    }
    
    public Booking (int booking_id, Date checkin_date, int room_num)
    {
        this.booking_id = booking_id;
        this.checkin_date = checkin_date;
        this.room_num = room_num;
    }
    
        public Booking (int booking_id, Date checkin_date, int room_num, int guest_id)
    {
        this.booking_id = booking_id;
        this.checkin_date = checkin_date;
        this.room_num = room_num;
        this.guest_id = guest_id;
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
    
    public void setGuestId(int guest_id)
    {
        this.guest_id = guest_id;
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
    
    public int getGuestId()
    {
        return guest_id;
    }
}
