package com.cosminpetrea.event_planning.controllers;

import com.cosminpetrea.event_planning.entities.Booking;
import com.cosminpetrea.event_planning.payloads.BookingDTO;
import com.cosminpetrea.event_planning.payloads.response.BookingResponseDTO;
import com.cosminpetrea.event_planning.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Booking> getAllBookings(){return this.bookingService.getBooking();}

    @PostMapping
    public BookingResponseDTO saveBooking(@RequestBody BookingDTO payload){
        Booking booking = bookingService.saveBooking(payload);
        return new BookingResponseDTO(booking.getId(),booking.getEvent().getTitle(), booking.getEvent().getDescription());
    }
}
