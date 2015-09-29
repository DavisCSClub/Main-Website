package org.dcsc.web.service;

import org.dcsc.core.model.attendees.EventAttendee;
import org.dcsc.core.model.event.Event;
import org.dcsc.core.persistence.attendees.EventAttendeeRepository;
import org.dcsc.core.persistence.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

@Service
public class HereService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventAttendeeRepository eventAttendeeRepository;

    public List<Event> getCurrentEvent() {
        TimeZone pacificTimeZone = TimeZone.getTimeZone("PST");
        long time = Calendar.getInstance(pacificTimeZone).getTime().getTime();

        Date currentDate = new Date(time);
        Time currentTime = new Time(time);

        List<Event> events = eventRepository.findCurrentEvents(currentDate, currentTime);

        return events;
    }

    public void addAttendee(String email, long eventId) {
        Optional<Event> eventWrapper = eventRepository.findEventById(eventId);
        Optional<EventAttendee> eventAttendee = eventAttendeeRepository.findEventAttendeeByEmail(email);

        if (eventWrapper.isPresent()) {
            Event event = eventWrapper.get();
            EventAttendee attendee = null;
            List<Event> attendedEvents = null;

            if (eventAttendee.isPresent()) {
                attendee = eventAttendee.get();
                attendedEvents = attendee.getAttendedEvents();
            } else {
                attendee = new EventAttendee(email, "", null);
                attendedEvents = new ArrayList<>();
            }

            if (!attendedEvents.contains(event)) {
                attendedEvents.add(event);
                attendee.setAttendedEvents(attendedEvents);
                eventAttendeeRepository.save(attendee);
            }
        }
    }
}
