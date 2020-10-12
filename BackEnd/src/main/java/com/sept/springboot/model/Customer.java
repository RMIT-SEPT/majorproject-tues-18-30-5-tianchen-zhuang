package com.sept.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Customer extends User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerId;
    @NotBlank(message = "Full Name is required")
    private String fullName;
    @NaturalId
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 3, max = 20, message = "Enter 3 to 20 characters")
    @NotBlank(message = "Username is required")
    private String username;
    @Size(min = 6, message = "Enter a minimum of length 6")
    @NotBlank(message = "Password is required")
    private String password;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private Date created;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private Date lastModified;

    public Customer()
    {
        super('c');
    }

    public long getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId(long customerId)
    {
        this.customerId = customerId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
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
