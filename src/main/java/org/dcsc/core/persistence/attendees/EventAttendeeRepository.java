package org.dcsc.core.persistence.attendees;

import org.dcsc.core.model.attendees.EventAttendee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Long> {
    Optional<EventAttendee> findEventAttendeeById(Long id);

    Optional<EventAttendee> findEventAttendeeByName(String name);

    Optional<EventAttendee> findEventAttendeeByEmail(String email);
}
