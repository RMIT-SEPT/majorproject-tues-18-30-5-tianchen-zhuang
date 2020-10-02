package com.sept.springboot.dao;

import com.sept.springboot.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long>
{
    Booking findByBookingId(long bookingId);

    @Override
    Iterable<Booking> findAll();

    @Override
    Iterable<Booking> findAllById(Iterable<Long> ids);
}
