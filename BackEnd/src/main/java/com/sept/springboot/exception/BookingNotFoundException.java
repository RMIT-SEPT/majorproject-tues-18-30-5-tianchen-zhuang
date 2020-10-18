package com.sept.springboot.exception;

/*
    Exception for when a booking is not found
 */
public class BookingNotFoundException extends RuntimeException
{
    public BookingNotFoundException(String message)
    {
        super(message);
    }
}
