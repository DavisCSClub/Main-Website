package org.dcsc.persistence.event;

import org.dcsc.model.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/7/2015.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long>{
    Optional<Event> findEventById(Long id);

    List<Event> findEventsByTag(String tag);
}
