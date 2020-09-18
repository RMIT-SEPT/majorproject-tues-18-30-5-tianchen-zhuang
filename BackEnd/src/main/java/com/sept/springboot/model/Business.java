package com.sept.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "BUSINESS")
@SecondaryTable(name = "ADDRESS", pkJoinColumns = @PrimaryKeyJoinColumn(name = "businessId"))
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long businessId;
    @Size(min = 3, max = 20, message = "Enter 3 to 20 characters")
    @NotBlank(message = "Username is required")
    private String username;
    @Size(min = 6, message = "Enter a minimum of length 6")
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Business Email is required")
    private String email;
    @NotBlank(message = "Business Name is required")
    private String businessName;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date lastModified;

    @NotBlank(message = "Country Field Required")
    @Column(table = "address")
    private String country;
    @NotBlank(message = "City Field Required")
    @Column(table = "address")
    private String city;
    @NotBlank(message = "Postal Code Required")
    @Column(table = "address")
    private String postCode;
    @NotBlank(message = "Street Field Required")
    @Column(table = "address")
    private String street;

    public Business() {

    }

//    public Business(String email) {
//        super();
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.businessName = businessName;
//        this.created = created;
//        this.lastModified = lastModified;
//    }

    public String getBusinessName() { return businessName; }

    public void setBusinessName(String businessName) { this.businessName = businessName; }

    public long getBusinessId()
    {
        return businessId;
    }

    public void setBusinessId(long businessId)
    {
        this.businessId = businessId;
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

    public String getPostCode() { return postCode; }

    public void setPostCode(String postCode) { this.postCode = postCode; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

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
