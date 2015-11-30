package org.dcsc.web.here;

import org.dcsc.core.attendees.EventAttendee;
import org.dcsc.core.attendees.EventAttendeeRepository;
import org.dcsc.core.event.Event;
import org.dcsc.core.event.EventRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .filter(event -> (event.getStartTime().getHours() <= currentHour) && (event.getEndTime().getHours() >= currentHour) && (event.isPublished()))
                .collect(Collectors.toList());
    }
    
    public void checkIn(HereForm hereForm) {
        String email = hereForm.getEmail();

        Optional<Event> eventWrapper = eventRepository.findEventById((long) hereForm.getEventId());
        Optional<EventAttendee> attendeeWrapper = eventAttendeeRepository.findEventAttendeeByEmail(email);

        if (eventWrapper.isPresent()) {
            Event event = eventWrapper.get();
            EventAttendee attendee = attendeeWrapper.orElseGet(() -> new EventAttendee(email));
            List<Event> attendedEvents = attendee.getAttendedEvents();

            if (!attendedEvents.contains(event)) {
                attendee.addEvent(event);
                eventAttendeeRepository.save(attendee);
            }

        } else {
            // Non-existent event found
        }
    }
}
