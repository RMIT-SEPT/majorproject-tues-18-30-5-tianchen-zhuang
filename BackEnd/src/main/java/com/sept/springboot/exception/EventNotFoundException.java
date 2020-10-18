package com.sept.springboot.exception;

/*
    Exception for when an event is not found
 */
public class EventNotFoundException extends RuntimeException
{
    public EventNotFoundException(String message)
    {
        super(message);
    }
}