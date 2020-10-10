package com.sept.springboot.exception;

public class DoubleBookingException extends RuntimeException
{
    public DoubleBookingException(String message)
    {
        super(message);
    }
}