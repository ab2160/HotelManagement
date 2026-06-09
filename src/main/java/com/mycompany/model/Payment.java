package com.mycompany.model;

import java.util.Date;


public class Payment {
    private int payment_id;
    private int booking_id;
    private double total_amount;
    private Date payment_date;
    private String payment_status;
    
    public Payment(){}
    public Payment(int payment_id, int booking_id, double total_amount, Date payment_date, String payment_status)
    {
        this.payment_id = payment_id;
        this.booking_id = booking_id;
        this.total_amount = total_amount;
        this.payment_date = payment_date;
        this.payment_status = payment_status;
    }
    
    public void setPaymentId(int payment_id)
    {
        this.payment_id = payment_id;
    }
    
    public void setBookingId(int booking_id)
    {
        this.booking_id = booking_id;
    }
    
    public void setTotalAmount(int total_amount)
    {
        this.total_amount = total_amount;
    }
    
    public void setPaymentDate(Date payment_date)
    {
        this.payment_date = payment_date;
    }
    
    public void setPaymentStatus(String payment_status)
    {
        this.payment_status = payment_status;
    }
    
    public int getPaymentId()
    {
        return payment_id;
    }
    
    public int getBookingId()
    {
        return booking_id;
    }
    
    public double getTotalAmount()
    {
        return total_amount;
    }
    
    public Date getPaymentDate()
    {
        return payment_date;
    }
    
    public String getPaymentStatus()
    {
        return payment_status;
    }
}
