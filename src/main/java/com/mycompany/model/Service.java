package com.mycompany.model;

public class Service {

    private int service_id;
    private String service_type;
    private double service_price;
    private String service_status;
    private int booking_id;

    public Service() {
    }
    public Service(int service_id, String service_type, double service_price)
    {
        this.service_id = service_id;
        this.service_type = service_type;
        this.service_price = service_price;
    }
    public Service (int service_id, String service_type, double service_price, String service_status, int booking_id)
    {
        this.service_id = service_id;
        this.service_type = service_type;
        this.service_price = service_price;
        this.service_status = service_status;
        this.booking_id = booking_id;
    }

    public void setServiceid(int service_id) {
        this.service_id = service_id;
    }

    public void setServicetype(String service_type) {
        this.service_type = service_type;
    }

    public void setServiceprice(double service_price) {
        this.service_price = service_price;
    }

    public void setServicestatus(String service_status) {
        this.service_status = service_status;
    }

    public void setBookingid(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getServiceid() {
        return service_id;
    }

    public String getServicetype() {
        return service_type;
    }

    public double getServicePrice() {
        return service_price;
    }

    public String getServiceStatus() {
        return service_status;
    }

    public int getBookingId() {
        return booking_id;
    }
}
