package com.sept.springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = UserNotFoundException.class)
    public final ResponseEntity<Object> handleUserIdException(UserNotFoundException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OutOfBoundsException.class)
    public final ResponseEntity<Object> handleOutOfBoundsException(OutOfBoundsException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BookingNotFoundException.class)
    public final ResponseEntity<Object> handleBookingException(BookingNotFoundException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EventNotFoundException.class)
    public final ResponseEntity<Object> handleEventException(EventNotFoundException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DuplicateException.class)
    public final ResponseEntity<Object> handleDoubleBookingException(DuplicateException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameAlreadyExistsException.class)
    public final ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex, WebRequest request)
    {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
