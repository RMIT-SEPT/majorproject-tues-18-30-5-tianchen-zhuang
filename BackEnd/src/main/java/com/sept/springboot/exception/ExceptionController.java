package com.sept.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/*
    This controls all of the exceptions found within the exception package
 */
@ControllerAdvice
public class ExceptionController
{
    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> handleUserIdException(UserNotFoundException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OutOfBoundsException.class)
    public ResponseEntity<Object> handleOutOfBoundsException(OutOfBoundsException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BookingNotFoundException.class)
    public ResponseEntity<Object> handleBookingException(BookingNotFoundException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EventNotFoundException.class)
    public ResponseEntity<Object> handleEventException(EventNotFoundException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Object> handleDoubleBookingException(DuplicateException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
