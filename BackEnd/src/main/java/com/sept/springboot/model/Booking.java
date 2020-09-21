package com.sept.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "BOOKING")
public class Booking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    private long businessId;
    private String bookingName;
    private String bookingDesc;
    private int currCapacity;
    private int maxCapacity;
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date bookingDate;
    @JsonFormat(pattern = "hh:mm:ss")
    private Date bookingTime;
    private String businessStatus;
    private String userStatus;

    public Booking()
    {
        // Defaults
        currCapacity = 0;
        maxCapacity = 1;
        businessStatus = "Not confirmed";
        userStatus = "Not confirmed";
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public String getBookingDesc() {
        return bookingDesc;
    }

    public void setBookingDesc(String bookingDesc) {
        this.bookingDesc = bookingDesc;
    }

    public Date getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public int getCurrCapacity() {
        return currCapacity;
    }

    public void setCurrCapacity(int currCapacity) {
        this.currCapacity = currCapacity;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
