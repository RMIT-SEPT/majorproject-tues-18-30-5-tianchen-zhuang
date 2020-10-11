package com.sept.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class SpringbootBackendApplication
{
    @PostConstruct
    void init()
    {
        TimeZone.setDefault(TimeZone.getTimeZone("AEDT"));
    }

    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootBackendApplication.class, args);
    }
}
