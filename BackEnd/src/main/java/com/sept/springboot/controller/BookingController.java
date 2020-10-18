package com.sept.springboot.controller;

import com.sept.springboot.exception.DuplicateException;
import com.sept.springboot.model.Booking;
import com.sept.springboot.services.BookingService;
import com.sept.springboot.services.CustomerService;
import com.sept.springboot.services.EventService;
import com.sept.springboot.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
/*
    API that controls all functionality for the bookings of customers.
 */
@RestController
@RequestMapping("/api/booking")
@CrossOrigin
public class BookingController
{
    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    /*
        This post mapping method makes a new booking for an event.
        Searches if the customer has booked for this event already.
        If the customer has not booked for this event, the booking will be add to the database,
        and the currCapacity variable in the event model will be incremented.
     */
    @PostMapping
    public ResponseEntity<?> createNewBooking(@Valid @RequestBody Booking booking, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        customerService.findByCustomerId(booking.getCustomerId());
        eventService.findByEventId(booking.getEventId());

        Iterable<Booking> bookingsForEvent = bookingService.findByEventId(booking.getEventId());

        for(Booking t : bookingsForEvent)
            if(t.getCustomerId() == booking.getCustomerId())
                throw new DuplicateException("Customer ID: '" + booking.getCustomerId() + "' has already booked for event ID: '" + booking.getEventId() + "'");

        eventService.incrementEventById(booking.getEventId());

        return new ResponseEntity<>(bookingService.addBooking(booking), HttpStatus.CREATED);
    }

    /*
        Returns the booking by the id (bookingId) passed through.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable("id") long id)
    {
        return new ResponseEntity<>(bookingService.findByBookingId(id), HttpStatus.OK);
    }

    /*
        Returns all the bookings in the database that have been made to all events.
        Returned as an iterable list.
     */
    @GetMapping("/all")
    public Iterable<Booking> getAllBookings()
    {
        return bookingService.findAllBookings();
    }

    /*
        Returns all the bookings for an event by the id (eventId) passed through.
        Returned as an iterable list.
     */
    @GetMapping("/event/{id}")
    public Iterable<Booking> getBookingsByEventId(@PathVariable("id") long id)
    {
        return bookingService.findByEventId(id);
    }

    /*
        Returns all the bookings made by a customer by the id (customerId) passed through.
        Returned as an iterable list.
     */
    @GetMapping("/customer/{id}")
    public Iterable<Booking> getBookingsByCustomerId(@PathVariable("id") long id)
    {
        return bookingService.findByCustomerId(id);
    }

    /*
        Deletes the booking by the id (bookingId) passed through.
        When a booking is deleted, the currCapacity variable in the event model is decremented.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteByBookingId(@PathVariable("id") long id)
    {
        Booking booking = bookingService.findByBookingId(id);

        eventService.decrementEventById(booking.getEventId());
        bookingService.deleteByBookingId(id);

        return new ResponseEntity<>("Booking with ID: '" + id + "' was deleted", HttpStatus.OK);
    }
}
