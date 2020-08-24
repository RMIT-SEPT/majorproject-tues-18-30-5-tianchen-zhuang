package com.sept.springboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sept.springboot.services.UserService;
//import main.java.com.sept.springboot.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)

public class UserControllerTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    ObjectMapper om=new ObjectMapper();

    @MockBean
    private UserService userService;
    private UserController userController;


    @Before
    public void setUp()
    {
        this.mockMvc= MockMvcBuilders.webAppContextSetup(this.context).build();
    }



    @Test
    public void addNewUserSuccess() throws Exception {

    MvcResult result=mockMvc.perform(
            post("/api/user")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"password\":\"testpassword\",\"username\":\"testusername\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(201))
                .andReturn();

    }

    @Test
    public void addNewUserInvalidUsernameLengthTooShort() throws Exception {

        MvcResult result=mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"te\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("[{\"defaultMessage\": \"Enter 3 to 20 characters\"}]"))
                .andReturn();
    }

    @Test
    public void addNewUserInvalidUsernameLengthTooLong() throws Exception {

        MvcResult result=mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusertestusertestu\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("[{\"defaultMessage\": \"Enter 3 to 20 characters\"}]"))
                .andReturn();
    }
    @Test
    public void addNewUserInvalidUsernameNotInRequest() throws Exception {

        MvcResult result=mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("[{\"defaultMessage\": \"Username is required\"}]"))
                .andReturn();
    }
    @Test
    public void addNewUserInvalidPasswordLengthTooShort() throws Exception {

        MvcResult result=mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testp\",\"username\":\"testusername\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("[{\"defaultMessage\": \"Enter a minimum of length 6\"}]"))
                .andReturn();
    }

    @Test
    public void addNewUserInvalidPasswordNotInRequest() throws Exception {

        MvcResult result=mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"username\":\"testusername\",\"email\":\"test@test.com\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("[{\"defaultMessage\": \"Password is required\"}]"))
                .andReturn();
    }

    @Test
    public void addNewUserInvalidEmailNotInRequest() throws Exception {

        MvcResult result=mockMvc.perform(
                post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("[{\"defaultMessage\": \"Email is required\"}]"))
                .andReturn();
    }



}
