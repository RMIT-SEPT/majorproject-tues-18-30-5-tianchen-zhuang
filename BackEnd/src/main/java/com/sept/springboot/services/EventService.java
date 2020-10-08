package com.sept.springboot.services;

import com.sept.springboot.dao.EventRepository;
import com.sept.springboot.exception.OutOfBoundsException;
import com.sept.springboot.exception.UserNotFoundException;
import com.sept.springboot.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EventService
{
    @Autowired
    private EventRepository eventRepository;

    public Event addEvent(Event event)
    {
        return eventRepository.save(event);
    }

    public Event findByEventId(long id)
    {
        Event event = eventRepository.findByEventId(id);

        if(event == null)
            throw new UserNotFoundException("Event ID '" + id + "' does not exist");

        return event;
    }

    public Event findByBusinessId(long id)
    {
        return eventRepository.findByBusinessId(id);
    }

    public Iterable<Event> findAllEventsForBusinessId(long id)
    {
        Iterable<Event> allEvents = eventRepository.findAll();
        ArrayList<Long> ids = new ArrayList<>();

        for(Event t : allEvents)
        {
            if(t.getBusinessId() == id)
                ids.add(t.getEventId());
        }

        return eventRepository.findAllById(ids);
    }

    public Iterable<Event> findAllEvents()
    {
        return eventRepository.findAll();
    }

    public void deleteByEventId(long id)
    {
        Event event = eventRepository.findByEventId(id);

        if(event == null)
            throw new UserNotFoundException("Cannot delete event with ID '" + id + "'. This event does not exist");

        eventRepository.delete(event);
    }

    public void incrementEventById(long id)
    {
        Event event = eventRepository.findByEventId(id);

        if(!event.incrementCurrCapacity())
            throw new OutOfBoundsException("Capacity has been reached");
    }

    public void decrementEventById(long id)
    {
        Event event = eventRepository.findByEventId(id);

        if(!event.decrementCurrCapacity())
            throw new OutOfBoundsException("Capacity has reached zero");
    }
}
