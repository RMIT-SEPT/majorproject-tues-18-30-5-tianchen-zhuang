package com.sept.springboot.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import main.java.com.sept.springboot.controller.UserController;


@SpringBootTest
@RunWith(SpringRunner.class)

public class UserControllerTests {

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



}
