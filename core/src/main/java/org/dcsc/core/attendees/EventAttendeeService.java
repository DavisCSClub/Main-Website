package org.dcsc.core.attendees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventAttendeeService {
    @Autowired
    private EventAttendeeRepository eventAttendeeRepository;

    public int getAttendanceCount(long eventId) {
        return eventAttendeeRepository.countByAttendedEventsId(eventId);
    }
}
