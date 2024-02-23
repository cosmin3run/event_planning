package com.cosminpetrea.event_planning.repositories;

import com.cosminpetrea.event_planning.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookingsDAO extends JpaRepository<Booking, UUID> {
}
