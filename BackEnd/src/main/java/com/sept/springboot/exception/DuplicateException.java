package com.sept.springboot.exception;

public class DuplicateException extends RuntimeException
{
    public DuplicateException(String message)
    {
        super(message);
    }
}