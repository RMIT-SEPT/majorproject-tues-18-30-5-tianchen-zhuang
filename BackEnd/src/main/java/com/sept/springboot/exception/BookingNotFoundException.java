package com.sept.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookingNotFoundException extends RuntimeException
{
    public BookingNotFoundException(String message)
    {
        super(message);
    }
}
