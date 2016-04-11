package org.dcsc.core.event;

import org.dcsc.core.attendees.EventAttendeeService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class EventService {
    private static final String EVENT_DATE_COLUMN_LABEL = "date";
    private static final String EVENT_START_TIME_COLUMN_LABEL = "startTime";

    @Autowired
    private EventAttendeeService eventAttendeeService;
    @Autowired
    private EventRepository eventRepository;


    public Event saveEvent(EventForm eventForm) {
        return saveEvent(eventForm.build());
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    @Transactional(readOnly = true)
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Event> getEventById(long id) {
        return eventRepository.findEventById(id);
    }


    @Transactional(readOnly = true)
    public Page<Event> getEvents(Pageable pageable) {
        return eventRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Event> getPagedEvents(int index, int size) {
        PageRequest request = new PageRequest(index, size, Sort.Direction.DESC, EVENT_DATE_COLUMN_LABEL, EVENT_START_TIME_COLUMN_LABEL);

        return eventRepository.findAll(request);
    }

    @Transactional(readOnly = true)
    public Set<EventAttendanceDTO> getLastNDayEvents(int n) {
        DateTime currentDateTime = DateTime.now(DateTimeZone.forID("America/Los_Angeles"));
        Date today = currentDateTime.toLocalDateTime().toDate();
        Date lastN = currentDateTime.minusDays(n).toLocalDateTime().toDate();

        Set<Event> events = eventRepository.findEventsByDateRange(lastN, today);

        Set<EventAttendanceDTO> eventAttendances = new HashSet<>();

        for (Event e : events) {
            Date date = e.getDate();
            int count = eventAttendeeService.getAttendanceCount(e.getId());

            eventAttendances.add(new EventAttendanceDTO(date, count));
        }

        return eventAttendances;
    }

    public void deleteEventById(long id) {
        eventRepository.delete(id);
    }
}

