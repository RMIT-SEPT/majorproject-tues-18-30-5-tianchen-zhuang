package com.sept.springboot.exception;

/*
    Exception for when an event has more capacity than the maximum capacity or is less than 0
 */
public class OutOfBoundsException extends RuntimeException
{
    public OutOfBoundsException(String message)
    {
        super(message);
    }
}
