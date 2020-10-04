package com.sept.springboot.services;

import com.sept.springboot.dao.BookingRepository;
import com.sept.springboot.exception.UserNotFoundException;
import com.sept.springboot.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class BookingService
{
    @Autowired
    private BookingRepository bookingRepository;

    public Booking addBooking(Booking booking)
    {
        return bookingRepository.save(booking);
    }

    public Booking findByBookingId(long bookingId)
    {
        Booking booking = bookingRepository.findByBookingId(bookingId);

        if(booking == null)
            throw new UserNotFoundException("Booking ID '" + bookingId + "' does not exist");

        return booking;
    }

    public Iterable<Booking> findByEventId(long eventId)
    {
        Iterable<Booking> allBookings = bookingRepository.findAll();
        ArrayList<Long> ids = new ArrayList<>();

        for(Booking t : allBookings)
            if(t.getEventId() == eventId)
                ids.add(t.getBookingId());

        return bookingRepository.findAllById(ids);
    }

    public Iterable<Booking> findByCustomerId(long customerId)
    {
        Iterable<Booking> allBookings = bookingRepository.findAll();
        ArrayList<Long> ids = new ArrayList<>();

        for(Booking t : allBookings)
            if(t.getCustomerId() == customerId)
                ids.add(t.getBookingId());

        return bookingRepository.findAllById(ids);
    }

    public Iterable<Booking> findAllBookings()
    {
        return bookingRepository.findAll();
    }

    public void deleteByBookingId(long bookingId)
    {
        Booking booking = bookingRepository.findByBookingId(bookingId);

        if(booking == null)
            throw new UserNotFoundException("Cannot delete booking with ID '" + bookingId + "'. This booking does not exist");

        bookingRepository.delete(booking);
    }
}
