package org.dcsc.core.attendees;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface EventAttendeeRepository extends JpaRepository<EventAttendee, Long> {
    Optional<EventAttendee> findEventAttendeeById(Long id);

    Optional<EventAttendee> findEventAttendeeByName(String name);

    Optional<EventAttendee> findEventAttendeeByEmail(String email);

    Set<EventAttendee> findByAttendedEventsId(Long id);

    int countByAttendedEventsId(Long id);
}
