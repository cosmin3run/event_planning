package com.cosminpetrea.event_planning.repositories;

import com.cosminpetrea.event_planning.entities.Event;
import com.cosminpetrea.event_planning.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventsDAO extends JpaRepository<Event, UUID> {


}
