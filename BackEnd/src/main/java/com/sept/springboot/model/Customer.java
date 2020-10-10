package com.sept.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name="CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    @Size(min = 3, max = 20, message = "Enter 3 to 20 characters")
    @NotBlank(message = "Username is required")
    private String username;
    @Size(min = 6, message = "Enter a minimum of length 6")
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @Max(value = 2, message = "RoleID is not valid")
    @Min(value = 0, message = "RoleID is not valid")
    private long roleID;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private Date created;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private Date lastModified;

    public Customer() {

    }

    public Customer(String username, String password, String email, long roleID)
    {
        super();
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleID = roleID;
    }

    public long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(long customerId)
    {
        this.customerId = customerId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
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

    public long getRoleID()
    {
        return roleID;
    }

    public void setRoleID(long roleID)
    {
        this.roleID = roleID;
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
