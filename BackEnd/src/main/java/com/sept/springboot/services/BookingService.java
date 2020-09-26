package com.sept.springboot.services;

import com.sept.springboot.dao.BookingRepository;
import com.sept.springboot.exception.UserNotFoundException;
import com.sept.springboot.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;

@Service
public class BookingService
{
    @Autowired
    private BookingRepository bookingRepository;

    public Booking addBusinessBooking(Booking booking)
    {
        return bookingRepository.save(booking);
    }

    public Booking findByBookingId(long id)
    {
        Booking booking = bookingRepository.findByBookingId(id);

        if(booking == null)
            throw new UserNotFoundException("Booking ID '" + id + "' does not exist");

        return booking;
    }

    public Booking findByBusinessId(long id)
    {
        Booking booking = bookingRepository.findByBusinessId(id);

        return booking;
    }

    public Iterable<Booking> findAllBookingsForBusinessId(long id)
    {
        Iterable<Booking> allBooking = bookingRepository.findAll();
        ArrayList<Long> ids = new ArrayList<>();

        for(Booking t : allBooking)
        {
            if(t.getBusinessId() == id)
                ids.add(t.getBookingId());
        }

        return bookingRepository.findAllById(ids);
    }

    public Iterable<Booking> findAllBooking()
    {
        return bookingRepository.findAll();
    }

    public void deleteByBookingId(long id)
    {
        Booking booking = bookingRepository.findByBookingId(id);

        if(booking == null)
            throw new UserNotFoundException("Cannot delete booking with ID '" + id + "'. This booking does not exist");

        bookingRepository.delete(booking);
    }
}
