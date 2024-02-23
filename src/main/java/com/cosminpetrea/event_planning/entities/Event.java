package com.cosminpetrea.event_planning.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "event")

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String title;
    private String description;
    private LocalDate eventDate;
    private String location;
    private int maxParticipants;


    @OneToMany(mappedBy = "event")
    @JsonIgnore
    private List<Booking> bookings;


    public Event(String title, String description, String eventDate, String location, int maxParticipants, List<Booking> bookings) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.location = location;
        this.maxParticipants = maxParticipants;
        this.bookings = bookings;
    }
}
