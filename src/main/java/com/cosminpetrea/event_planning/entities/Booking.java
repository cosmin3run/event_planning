package com.cosminpetrea.event_planning.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "booking")

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int seatsNumber;

    @ManyToOne
    @JoinColumn(name = "event_id")
    public Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
