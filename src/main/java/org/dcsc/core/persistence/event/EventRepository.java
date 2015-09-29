package org.dcsc.core.persistence.event;

import org.dcsc.core.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventById(Long id);

    @Query("SELECT e FROM Event e WHERE e.date = ?1 AND e.startTime <= ?2 AND e.endTime >= ?2")
    List<Event> findCurrentEvents(Date date, Time time);
}
