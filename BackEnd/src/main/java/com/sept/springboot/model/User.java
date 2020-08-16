package com.sept.springboot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    private String username;
    private String password;
    private String email;
    private Date created;
    private Date lastModified;

    public User() {

    }

    public User(String firstName, String lastName, String email) {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.created = created;
        this.lastModified = lastModified;
    }

    public long getId()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getFirstName()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Date getCreated()
    {
        return created;
    }

    public void setCreated(Date created)
    {
        this.created = created;
    }

    public Date getLastModified()
    {
        return lastModified;
    }

    public void setLastModified(Date lastModified)
    {
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
}
