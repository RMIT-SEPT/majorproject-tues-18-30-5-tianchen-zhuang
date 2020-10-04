package com.sept.springboot.exception;

public class OutOfBoundsException extends RuntimeException
{
    public OutOfBoundsException(String message)
    {
        super(message);
    }
}
