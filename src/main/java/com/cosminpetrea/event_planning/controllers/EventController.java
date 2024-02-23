package com.cosminpetrea.event_planning.controllers;

import com.cosminpetrea.event_planning.entities.Event;
import com.cosminpetrea.event_planning.payloads.EventDTO;
import com.cosminpetrea.event_planning.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping
    public List<Event> getEvents(){return this.eventService.getEvents();}
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event saveEvent(@RequestBody EventDTO payload){
        return eventService.saveEvent(payload);
    }


    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Event modifyEvent(@PathVariable UUID id, @RequestBody EventDTO payload){
        return eventService.findByIdAndUpdate(id, payload);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteEvent(@PathVariable UUID id){
        eventService.findByIdAndDelete(id);
    }
}
