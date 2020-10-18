package com.sept.springboot.controller;

import com.sept.springboot.model.Booking;
import com.sept.springboot.model.Business;
import com.sept.springboot.model.Event;
import com.sept.springboot.services.BookingService;
import com.sept.springboot.services.BusinessService;
import com.sept.springboot.services.EventService;
import com.sept.springboot.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/*
    API that controls all functionality for the business.
 */
@RestController
@RequestMapping("/api/business")
@CrossOrigin
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    /*
        This adds a new business to the database.
     */
    @PostMapping("")
    public ResponseEntity<?> createNewBusiness(@Valid @RequestBody Business business, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Business newBusiness = businessService.addOrUpdateBusiness(business);

        return new ResponseEntity<>(newBusiness, HttpStatus.CREATED);
    }

    /*
        This returns the business with the id (businessId) passed through.
        Returns an iterable list.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getBusinessById(@PathVariable long id)
    {
        return new ResponseEntity<>(businessService.findByBusinessId(id), HttpStatus.OK);
    }

    /*
        Returns the email of the business by the id (businessId) passed through.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getBusinessEmail(@PathVariable String email)
    {
        Business business = businessService.findByEmail(email);

        return new ResponseEntity<>(business, HttpStatus.OK);
    }

    /*
        Returns the password of the business with the email that is passed through.
        This is an unsecure way to allow logging in a business in the frontend.
        Is here is a temporary loose solution.
     */
    @GetMapping("/login/{email}")
    public ResponseEntity<?> getPasswordByEmail(@PathVariable String email)
    {
        Business business = businessService.findByEmail(email);

        return new ResponseEntity<>(business.getPassword(), HttpStatus.OK);
    }

    /*
        Returns all of the businesses in the database.
        Returns an iterable list.
     */
    @GetMapping("/all")
    public Iterable<Business> getAllBusinesses()
    {
        return businessService.findAllBusinesses();
    }

    /*
        Deletes the business with the id (businessId) that is passed through.
        When a business is deleted, all the events for that business are deleted, and all the
        bookings for those events are also deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBusiness(@PathVariable long id)
    {
        Iterable<Event> events = eventService.findAllEventsForBusinessId(id);

        for(Event t : events)
        {
            Iterable<Booking> bookings = bookingService.findByEventId(t.getEventId());

            for(Booking t2 : bookings)
                bookingService.deleteByBookingId(t2.getBookingId());

            eventService.deleteByEventId(t.getEventId());
        }

        businessService.deleteByBusinessId(id);

        return new ResponseEntity<>("Business with ID: '" + id + "' was deleted", HttpStatus.OK);
    }

    /*
        Updates the business with the id (businessId) passed through.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBusiness(@PathVariable(value = "id") long id, @Valid @RequestBody Business businessDetails, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Business business = businessService.findByBusinessId(id);

        business.setBusinessName(businessDetails.getBusinessName());
        business.setUsername(businessDetails.getUsername());
        business.setPassword(businessDetails.getPassword());
        business.setEmail(businessDetails.getEmail());
        business.setStreet(businessDetails.getStreet());
        business.setCity(businessDetails.getCity());
        business.setCountry(businessDetails.getCountry());
        business.setPostCode(businessDetails.getPostCode());

        businessService.addOrUpdateBusiness(business);

        return new ResponseEntity<>("Business with ID: '" + id + "' has been updated", HttpStatus.OK);
    }
}
