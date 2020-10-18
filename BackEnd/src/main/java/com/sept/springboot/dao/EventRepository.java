package com.sept.springboot.dao;

import com.sept.springboot.model.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
    Repository for the admin event
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long>
{
    Event findByEventId(long eventId);
    Event findByBusinessId(long businessId);

    @Override
    Iterable<Event> findAll();

    @Override
    Iterable<Event> findAllById(Iterable<Long> ids);
}
