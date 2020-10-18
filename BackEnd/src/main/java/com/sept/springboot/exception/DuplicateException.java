package com.sept.springboot.exception;

/*
    Exception for when a booking is made by the same customer for the same event
 */
public class DuplicateException extends RuntimeException
{
    public DuplicateException(String message)
    {
        super(message);
    }
}