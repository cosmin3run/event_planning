package com.cosminpetrea.event_planning.entities;

import com.cosminpetrea.event_planning.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Role role;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Booking> bookings;

    public User(String username, String name, String surname, String email, String password, Role role, List<Booking> bookings) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.bookings = bookings;
    }
}
