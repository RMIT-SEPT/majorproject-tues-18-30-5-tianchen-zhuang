package com.sept.springboot.exception;

/*
    Exception for when a user (customer, business, or admin) is not found
 */
public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String message)
    {
        super(message);
    }
}
