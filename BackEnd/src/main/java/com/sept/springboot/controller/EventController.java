package com.sept.springboot.controller;

import com.sept.springboot.exception.OutOfBoundsException;
import com.sept.springboot.model.Event;
import com.sept.springboot.services.EventService;
import com.sept.springboot.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/event")
@CrossOrigin
public class EventController
{
    @Autowired
    private EventService eventService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping()
    public ResponseEntity<?> createNewEvent(@Valid @RequestBody Event event, BindingResult result)
    {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

        if(errorMap != null)
            return errorMap;

        Event newEvent = eventService.addOrUpdateEvent(event);
        return new ResponseEntity<>(newEvent, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable long id)
    {
        Event event = eventService.findByEventId(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Event> getAllEvents()
    {
        return eventService.findAllEvents();
    }

    @GetMapping("/business/{id}")
    public Iterable<Event> getAllEventsForBusiness(@PathVariable long id)
    {
        return eventService.findAllEventsForBusinessId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable long id)
    {
        eventService.deleteByEventId(id);

        return new ResponseEntity<>("Event with ID: '" + id + "' was deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEvent(@PathVariable(value = "id") long id, @Valid @RequestBody Event eventDetails)
    {
        Event event = eventService.findByEventId(id);

        if(event.getCurrCapacity() > eventDetails.getMaxCapacity())
            throw new OutOfBoundsException("Max capacity is less than the current capacity of customers");

        event.setBusinessId(eventDetails.getBusinessId());
        event.setEventName(eventDetails.getEventName());
        event.setEventDesc(eventDetails.getEventDesc());
        //event.setCurrCapacity(eventDetails.getCurrCapacity());
        event.setMaxCapacity(eventDetails.getMaxCapacity());
        event.setEventDate(eventDetails.getEventDate());
        event.setEventTime(eventDetails.getEventTime());
        event.setBusinessStatus(eventDetails.getBusinessStatus());
        event.setCustomerStatus(eventDetails.getCustomerStatus());

        eventService.addOrUpdateEvent(event);

        return new ResponseEntity<>("Event with ID: '" + id + "' has been updated", HttpStatus.OK);
    }
}
