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


@SpringBootTest
@RunWith(SpringRunner.class)

public class BusinessControllerTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }


    @Test
    public void addNewBusinessSuccess() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\"" +
                                ",\"email\":\"test@test.com\",\"businessName\":\"testbusiness\"" +
                                ",\"country\":\"testcountry\",\"city\":\"testcity\"" +
                                ",\"postCode\":\"testPostcode\",\"street\":\"teststreet\"}"))
                .andExpect(status().is(201))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidUsernameLengthTooShort() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"te\"" +
                                ",\"email\":\"test@test.com\",\"businessName\":\"testbusiness\"" +
                                ",\"country\":\"testcountry\",\"city\":\"testcity\"" +
                                ",\"postCode\":\"testPostcode\",\"street\":\"teststreet\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"username\": \"Enter 3 to 20 characters\"}"))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidUsernameLengthTooLong() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusernametestusern\"" +
                                ",\"email\":\"test@test.com\",\"businessName\":\"testbusiness\"" +
                                ",\"country\":\"testcountry\",\"city\":\"testcity\"" +
                                ",\"postCode\":\"testPostcode\",\"street\":\"teststreet\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"username\": \"Enter 3 to 20 characters\"}"))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidUsernameNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\"" +
                                ",\"email\":\"test@test.com\",\"businessName\":\"testbusiness\"" +
                                ",\"country\":\"testcountry\",\"city\":\"testcity\"" +
                                ",\"postCode\":\"testPostcode\",\"street\":\"teststreet\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"username\": \"Username is required\"}"))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidPasswordLengthTooShort() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testp\",\"username\":\"testusername\"" +
                                ",\"email\":\"test@test.com\",\"businessName\":\"testbusiness\"" +
                                ",\"country\":\"testcountry\",\"city\":\"testcity\"" +
                                ",\"postCode\":\"testPostcode\",\"street\":\"teststreet\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"password\": \"Enter a minimum of length 6\"}"))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidPasswordNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"username\":\"testusername\"" +
                                ",\"email\":\"test@test.com\",\"businessName\":\"testbusiness\"" +
                                ",\"country\":\"testcountry\",\"city\":\"testcity\"" +
                                ",\"postCode\":\"testPostcode\",\"street\":\"teststreet\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"password\": \"Password is required\"}"))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidEmailNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\"" +
                                ",\"businessName\":\"testbusiness\"" +
                                ",\"country\":\"testcountry\",\"city\":\"testcity\"," +
                                "\"postCode\":\"testPostcode\",\"street\":\"teststreet\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"email\": \"Business Email is required\"}"))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidBusinessNameNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\"" +
                                ",\"email\":\"test@test.com\"" +
                                ",\"country\":\"testcountry\",\"city\":\"testcity\"," +
                                "\"postCode\":\"testPostcode\",\"street\":\"teststreet\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"businessName\": \"Business Name is required\"}"))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidCountryNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\"" +
                                ",\"email\":\"test@test.com\",\"businessName\":\"testbusiness\"" +
                                ",\"city\":\"testcity\"" +
                                ",\"postCode\":\"testPostcode\",\"street\":\"teststreet\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"country\": \"Country Field Required\"}"))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidCityNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\"" +
                                ",\"email\":\"test@test.com\",\"businessName\":\"testbusiness\"" +
                                ",\"country\":\"testcountry\"" +
                                ",\"postCode\":\"testPostcode\",\"street\":\"teststreet\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"city\": \"City Field Required\"}"))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidPostcodeNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\"" +
                                ",\"email\":\"test@test.com\",\"businessName\":\"testbusiness\"" +
                                ",\"country\":\"testcountry\",\"city\":\"testcity\"" +
                                ",\"street\":\"teststreet\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"postCode\": \"Postal Code Required\"}"))
                .andReturn();
    }

    @Test
    public void addNewBusinessInvalidStreetNotInRequest() throws Exception {

        mockMvc.perform(
                post("/api/business")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("{\"password\":\"testpassword\",\"username\":\"testusername\"" +
                                ",\"email\":\"test@test.com\",\"businessName\":\"testbusiness\"" +
                                ",\"country\":\"testcountry\",\"city\":\"testcity\"" +
                                ",\"postCode\":\"testPostcode\"}"))
                .andExpect(status().is(400))
                .andExpect(content().json("{\"street\": \"Street Field Required\"}"))
                .andReturn();
    }
}
