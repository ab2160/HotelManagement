package com.mycompany.model;

public class Room {

    private int room_num;
    private String room_status;
    private String class_name;
    private String bed_type;
    private double room_price;

    public Room() {
    }

    public Room(int room_num, double room_price) {
        this.room_num = room_num;
        this.room_price = room_price;
    }

    public Room(int room_num, String room_status, String class_name, String bed_type, double room_price) {
        this.room_num = room_num;
        this.room_status = room_status;
        this.class_name = class_name;
        this.bed_type = bed_type;
        this.room_price = room_price;
    }

    public Room(String room_status, String class_name, String bed_type, double room_price) {
        this.room_status = room_status;
        this.class_name = class_name;
        this.bed_type = bed_type;
        this.room_price = room_price;
    }

    public void setRoomnum(int room_num) {
        this.room_num = room_num;
    }

    public void setRoomstatus(String room_status) {
        this.room_status = room_status;
    }

    public void setClassname(String class_name) {
        this.class_name = class_name;
    }

    public void setBedtype(String bed_type) {
        this.bed_type = bed_type;
    }

    public void setRoomprice(double room_price) {
        this.room_price = room_price;
    }

    public int getRoomnum() {
        return room_num;
    }

    public String getRoomstatus() {
        return room_status;
    }

    public String getClassname() {
        return class_name;
    }

    public String getBedtype() {
        return bed_type;
    }

    public double getRoomprice() {
        return room_price;
    }
}
