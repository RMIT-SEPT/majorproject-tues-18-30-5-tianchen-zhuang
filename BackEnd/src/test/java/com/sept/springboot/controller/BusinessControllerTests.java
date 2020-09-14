package com.sept.springboot.controller;

import com.sept.springboot.model.Business;
import com.sept.springboot.services.BusinessService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@RunWith(SpringRunner.class)

public class BusinessControllerTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @MockBean
    private BusinessService businessService;


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

    @Test
    public void getAllBusinesses() throws Exception {

        mockMvc.perform(get("/api/business/all"))
                .andExpect(status().is(200))
                .andReturn();
    }

    @Test
    public void getBusinessbyID() throws Exception {

        Business testBusiness = new Business();

        testBusiness.setBusinessId(1);
        testBusiness.setBusinessName("testbusiness");
        testBusiness.setUsername("testusername");
        testBusiness.setPassword("testpassword");
        testBusiness.setEmail("test@test.com");
        testBusiness.setCountry("testcountry");
        testBusiness.setPostCode("testpostcode");
        testBusiness.setCity("testcity");
        testBusiness.setStreet("teststreet");

        when(businessService.findByBusinessId(1)).thenReturn(testBusiness);

        mockMvc.perform(get("/api/business/1"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("businessName").value("testbusiness"))
                .andExpect(MockMvcResultMatchers.jsonPath("username").value("testusername"))
                .andExpect(MockMvcResultMatchers.jsonPath("password").value("testpassword"))
                .andExpect(MockMvcResultMatchers.jsonPath("country").value("testcountry"))
                .andExpect(MockMvcResultMatchers.jsonPath("city").value("testcity"))
                .andExpect(MockMvcResultMatchers.jsonPath("street").value("teststreet"))
                .andExpect(MockMvcResultMatchers.jsonPath("postCode").value("testpostcode"))
                .andReturn();
    }


    @Test
    public void deleteBusinessbyIDInvalidID() throws Exception {

        mockMvc.perform(delete("/api/business/20"))
                .andExpect(status().is(400))
                .andExpect(content().string("Cannot delete business with ID '20'. This business does not exist"))
                .andReturn();
    }

    @Test
    public void getBusinessbyIDInvalidID() throws Exception {

        mockMvc.perform(get("/api/business/100"))
                .andExpect(status().is(400))
                .andExpect(content().string("Business ID '100' does not exist"))
                .andReturn();
    }



}
