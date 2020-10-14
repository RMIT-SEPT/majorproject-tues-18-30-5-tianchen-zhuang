package com.sept.springboot.exception;

public class EventNotFoundException extends RuntimeException
{
    public EventNotFoundException(String message)
    {
        super(message);
    }
}