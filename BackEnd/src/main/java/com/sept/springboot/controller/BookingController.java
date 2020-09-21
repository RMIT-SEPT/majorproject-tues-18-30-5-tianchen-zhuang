package com.sept.springboot.controller;

import com.sept.springboot.model.Booking;
import com.sept.springboot.model.Business;
import com.sept.springboot.model.User;
import com.sept.springboot.services.BookingService;
import com.sept.springboot.services.BusinessService;
import com.sept.springboot.services.MapValidationErrorService;
import com.sept.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/book")
@CrossOrigin
public class BookingController
{
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private BusinessService businessService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/business")
    public ResponseEntity<?> createNewBusinessBooking(@Valid @RequestBody Booking booking, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Booking newBook = bookingService.addBusinessBooking(booking);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable long id)
    {
        Booking booking = bookingService.findByBookingId(id);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Booking> getAllBookings()
    {
        return bookingService.findAllBooking();
    }

    @GetMapping("/business/{id}")
    public Iterable<Booking> getAllBookingsForBusiness(@PathVariable long id)
    {
        return bookingService.findAllBookingsForBusinessId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable long id)
    {
        bookingService.deleteByBookingId(id);

        return new ResponseEntity<>("Booking with ID: '" + id + "' was deleted", HttpStatus.OK);
    }
}
