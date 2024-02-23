package com.cosminpetrea.event_planning.services;

import com.cosminpetrea.event_planning.entities.Event;
import com.cosminpetrea.event_planning.exceptions.NotFoundExceptions;
import com.cosminpetrea.event_planning.payloads.EventDTO;
import com.cosminpetrea.event_planning.repositories.EventsDAO;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventsDAO eventsDAO;

    public List<Event> getEvents(){return this.eventsDAO.findAll();}

    public Event findById(UUID id){return eventsDAO.findById(id).orElseThrow(() -> new NotFoundExceptions(id));}

    public Event saveEvent(EventDTO payload){
        Event newEvent = new Event();
        newEvent.setTitle(payload.title());
        newEvent.setEventDate(payload.date());
        newEvent.setDescription(payload.description());
        newEvent.setMaxParticipants(payload.maxParticipants());
        newEvent.setLocation(payload.location());
        return eventsDAO.save(newEvent);
    }

    public Event findByIdAndUpdate(UUID id, @Validated EventDTO payload){
        Event found = this.findById(id);

        found.setTitle(payload.title());
        found.setEventDate(payload.date());
        found.setDescription(payload.description());
        found.setMaxParticipants(payload.maxParticipants());
        found.setLocation(payload.location());
        return eventsDAO.save(found);
    }

    public void findByIdAndDelete(UUID id){
        Event found = this.findById(id);
            eventsDAO.delete(found);

    }


}
