package org.dcsc.web.here;

import org.dcsc.core.attendees.EventAttendee;
import org.dcsc.core.event.Event;
import org.dcsc.core.attendees.EventAttendeeRepository;
import org.dcsc.core.event.EventRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HereService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventAttendeeRepository eventAttendeeRepository;

    public List<Event> getCurrentEvent() {
        DateTime currentDateTime = DateTime.now(DateTimeZone.forID("America/Los_Angeles"));
        int currentHour = currentDateTime.getHourOfDay();

        Date date = currentDateTime.toLocalDateTime().toDate();
        List<Event> eventsHappeningToday = eventRepository.findEventsByDate(date);

        return eventsHappeningToday.stream()
                .filter(event -> (event.getStartTime().getHours() <= currentHour) && (event.getEndTime().getHours() >= currentHour))
                .collect(Collectors.toList());
    }

    public void addAttendee(String email, long eventId) {
        email = "" + email.hashCode();

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
