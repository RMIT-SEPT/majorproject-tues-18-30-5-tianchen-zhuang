package com.sept.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "EVENT")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long eventId;
    @Min(value = 1, message = "Business ID is required and must be greater than 0")
    private long businessId;
    @NotBlank(message = "Event name is required")
    private String eventName;
    private String eventDesc;
    private int currCapacity;
    @Min(value = 1, message = "Max capacity must be greater than 0")
    private int maxCapacity;
    @NotNull(message = "Event date is required")
    @JsonFormat(pattern = "dd-mm-yyyy")
    private Date eventDate;
    @NotNull(message = "Event time is required")
    @JsonFormat(pattern = "hh:mm:ss")
    private Date eventTime;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private Date created;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private Date lastModified;

    public Event() {
        // Defaults
        currCapacity = 0;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    @PrePersist
    protected void onCreate()
    {
        this.created = new Date();
    }

    @PreUpdate
    protected void onUpdate()
    {
        this.lastModified = new Date();
    }

    public boolean incrementCurrCapacity() {
        if (currCapacity + 1 <= maxCapacity)
            currCapacity += 1;
        else
            return false;

        return true;
    }

    public boolean decrementCurrCapacity()
    {
        if(currCapacity != 0)
            currCapacity -= 1;
        else
            return false;

        return true;
    }
}
