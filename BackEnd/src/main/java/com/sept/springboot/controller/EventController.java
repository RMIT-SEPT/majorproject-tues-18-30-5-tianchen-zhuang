package com.sept.springboot.controller;

import com.sept.springboot.exception.DuplicateException;
import com.sept.springboot.exception.OutOfBoundsException;
import com.sept.springboot.model.Booking;
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
import java.text.SimpleDateFormat;

/*
    API that controls all functionality for the events of businesses.
 */
@RestController
@RequestMapping("/api/event")
@CrossOrigin
public class EventController
{
    @Autowired
    private EventService eventService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    /*
        The post mapping that adds a new event for a business to the database.
        Prevents the business from creating two events at the same time.
     */
    @PostMapping()
    public ResponseEntity<?> createNewEvent(@Valid @RequestBody Event event, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        businessService.findByBusinessId(event.getBusinessId());

        Iterable<Event> eventsForBusiness = eventService.findAllEventsForBusinessId(event.getBusinessId());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");

        for(Event t : eventsForBusiness)
            if(dateFormatter.format(t.getEventDate()).equals(dateFormatter.format(event.getEventDate())) && timeFormatter.format(t.getEventTime()).equals(timeFormatter.format(event.getEventTime())))
                throw new DuplicateException("Business ID: '" + event.getBusinessId() + "' has event at " + timeFormatter.format(event.getEventTime()) + " " + dateFormatter.format(event.getEventDate()) + " already");

        return new ResponseEntity<>(eventService.addOrUpdateEvent(event), HttpStatus.CREATED);
    }

    /*
        Returns the event by the id (eventId) passed through.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable long id)
    {
        return new ResponseEntity<>(eventService.findByEventId(id), HttpStatus.OK);
    }

    /*
        Returns all the events. All events that were created by all businesses.
        Returned as an iterable list.
     */
    @GetMapping("/all")
    public Iterable<Event> getAllEvents()
    {
        return eventService.findAllEvents();
    }

    /*
        Returns all the events made by a business, by the id (businessId) passed through.
        Returned as an iterable list.
     */
    @GetMapping("/business/{id}")
    public Iterable<Event> getAllEventsForBusiness(@PathVariable long id)
    {
        return eventService.findAllEventsForBusinessId(id);
    }

    /*
        Deletes a business by the id (businessId) passed through.
        When an event is deleted, all bookings made to the event are also deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable long id)
    {
        Iterable<Booking> bookings = bookingService.findByEventId(id);

        for(Booking t : bookings)
            bookingService.deleteByBookingId(t.getBookingId());

        eventService.deleteByEventId(id);

        return new ResponseEntity<>("Event with ID: '" + id + "' was deleted", HttpStatus.OK);
    }

    /*
        Updates the event with the id (eventId) passed through.
        Prevents business from making the maxCapacity less than the amount of people already booked.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable(value = "id") long id, @Valid @RequestBody Event eventDetails, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Event event = eventService.findByEventId(id);

        if(event.getCurrCapacity() > eventDetails.getMaxCapacity())
            throw new OutOfBoundsException("Max capacity is less than the current capacity of customers");

        event.setBusinessId(eventDetails.getBusinessId());
        event.setEventName(eventDetails.getEventName());
        event.setEventDesc(eventDetails.getEventDesc());
        event.setMaxCapacity(eventDetails.getMaxCapacity());
        event.setEventDate(eventDetails.getEventDate());
        event.setEventTime(eventDetails.getEventTime());

        eventService.addOrUpdateEvent(event);

        return new ResponseEntity<>("Event with ID: '" + id + "' has been updated", HttpStatus.OK);
    }
}
