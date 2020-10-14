package com.sept.springboot.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import main.java.com.sept.springboot.controller.UserController;


@SpringBootTest
@RunWith(SpringRunner.class)

public class CustomerControllerTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @Before
    public void setUp()
    {
        this.mockMvc= MockMvcBuilders.webAppContextSetup(this.context).build();
    }



    @Test
    public void addNewUserSuccess() throws Exception {

        mockMvc.perform(
            post("/api/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"password\":\"testpassword\",\"username\":\"testusername\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(201))
                .andReturn();

    }

    @Test
    public void addNewUserInvalidUsernameLengthTooShort() throws Exception {
        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"te\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"username\": \"Enter 3 to 20 characters\"}"))
                .andReturn();
    }

    @Test
    public void addNewUserInvalidUsernameLengthTooLong() throws Exception {

        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusertestusertestu\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"username\": \"Enter 3 to 20 characters\"}"))
                .andReturn();
    }
    @Test
    public void addNewUserInvalidUsernameNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"username\": \"Username is required\"}"))
                .andReturn();
    }
    @Test
    public void addNewUserInvalidPasswordLengthTooShort() throws Exception {

        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testp\",\"username\":\"testusername\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"password\": \"Enter a minimum of length 6\"}"))
                .andReturn();
    }

    @Test
    public void addNewUserInvalidPasswordNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"username\":\"testusername\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"password\": \"Password is required\"}"))
                .andReturn();
    }

    @Test
    public void addNewUserInvalidEmailNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"email\": \"Email is required\"}"))
                .andReturn();
    }

    // Lockie's Test
    @Ignore
    @Test
    public void getUserById() throws Exception
    {
        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(201))
                .andReturn();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date created = new Date();

        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().is(200))
                .andExpect(content().json("{\"userId\": 1,\n" +
            "\"username\": \"testusername\",\n" +
            "\"password\": \"testpassword\",\n" +
            "\"email\": \"test@test.com\",\n" +
            "\"roleID\": 0,\n" +
            "\"created\": \""+ dateFormat.format(created) +"\",\n" +
            "\"lastModified\": null}"));
    }

    // Lockie's Test
    @Test
    public void getUserByIdNotInDB() throws Exception
    {
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().is(400))
                .andExpect(content().string("User ID '1' does not exist"));
    }

    // Lockie's Test
    @Ignore
    @Test
    public void getUserByIDInDBWrongID() throws Exception
    {
        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(201))
                .andReturn();

        mockMvc.perform(get("/api/user/2"))
                .andExpect(status().is(400))
                .andExpect(content().string("User ID '2' does not exist"));
    }

    // Lockie's Test
    @Ignore
    @Test
    public void getAllUsersNoneInDB() throws Exception
    {
        mockMvc.perform(get("/api/user/all"))
                .andExpect(status().is(200))
                .andExpect(content().json("[]"))
                .andReturn();
    }

    // Lockie's Test
    @Ignore
    @Test
    public void getAllUsers() throws Exception
    {
        mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(201))
                .andReturn();

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Date created = new Date();

        mockMvc.perform(get("/api/user/all"))
                .andExpect(status().is(200))
                .andExpect(content().json("[{\"userId\": 1,\n" +
                        "\"username\": \"testusername\",\n" +
                        "\"password\": \"testpassword\",\n" +
                        "\"email\": \"test@test.com\",\n" +
                        "\"roleID\": 0,\n" +
                        "\"created\": \""+ dateFormat.format(created) +"\",\n" +
                        "\"lastModified\": null}]"))
                .andReturn();
    }

}
