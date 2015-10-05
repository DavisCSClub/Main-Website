package org.dcsc.core.persistence.event;

import org.dcsc.core.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventById(Long id);

    List<Event> findEventsByDate(Date date);
}
