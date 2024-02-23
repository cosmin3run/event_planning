package com.cosminpetrea.event_planning.services;

import com.cosminpetrea.event_planning.entities.Booking;
import com.cosminpetrea.event_planning.entities.Event;
import com.cosminpetrea.event_planning.entities.User;
import com.cosminpetrea.event_planning.exceptions.NotFoundExceptions;
import com.cosminpetrea.event_planning.payloads.BookingDTO;
import com.cosminpetrea.event_planning.repositories.BookingsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class BookingService {
    @Autowired
    private BookingsDAO bookingsDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    public List<Booking> getBooking(){return this.bookingsDAO.findAll();}

    public Booking saveBooking(BookingDTO payload){
        User user = userService.findById(payload.user());
        Event event = eventService.findById(payload.event());
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setEvent(event);
        booking.setSeatsNumber(payload.seatsNumber());
        return bookingsDAO.save(booking);
    }
    public Booking findById(UUID id){
        return bookingsDAO.findById(id).orElseThrow(()->new NotFoundExceptions(id));
    }

    public Booking findByIdAndUpdate(UUID id, BookingDTO payload){
        Booking found = this.findById(id);
        User user = userService.findById(payload.event());
        Event event = eventService.findById(payload.user());
        found.setEvent(event);
        found.setUser(user);
        return bookingsDAO.save(found);
    }

    public void findByIdAndDelete(UUID id) {
        Booking found = this.findById(id);
        bookingsDAO.delete(found);
    }
}
