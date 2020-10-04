package com.sept.springboot.controller;

import com.sept.springboot.model.Booking;
import com.sept.springboot.services.BookingService;
import com.sept.springboot.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin
public class BookingController
{
    @Autowired
    private BookingService bookingService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping
    public ResponseEntity<?> createNewBooking(@Valid @RequestBody Booking booking, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        return new ResponseEntity<>(bookingService.addBooking(booking), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable("id") long id)
    {
        return new ResponseEntity<>(bookingService.findByBookingId(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Booking> getAllBookings()
    {
        return bookingService.findAllBookings();
    }

    @GetMapping("/event/{id}")
    public Iterable<Booking> getBookingsByEventId(@PathVariable("id") long id)
    {
        return bookingService.findByEventId(id);
    }

    @GetMapping("/customer/{id}")
    public Iterable<Booking> getBookingsByCustomerId(@PathVariable("id") long id)
    {
        return bookingService.findByCustomerId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByBookingId(@PathVariable("id") long id)
    {
        bookingService.deleteByBookingId(id);

        return new ResponseEntity<>("Booking with ID: '" + id + "' was deleted", HttpStatus.OK);
    }
}
