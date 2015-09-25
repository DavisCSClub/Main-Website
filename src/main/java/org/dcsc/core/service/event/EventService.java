package org.dcsc.core.service.event;

import org.dcsc.core.model.event.EventForm;
import org.dcsc.core.model.event.Event;
import org.dcsc.core.persistence.event.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/7/2015.
 */
@Service
public class EventService {
    private static final String EVENT_DATE_COLUMN_LABEL = "date";
    private static final String EVENT_START_TIME_COLUMN_LABEL = "startTime";
    private static final String REGEX_TIME_FORMAT = "([0-9]{2}):([0-9]{2}):([0-9]{2})";

    @Autowired
    private EventRepository eventRepository;

    public Event saveEvent(EventForm eventForm) {
        return eventRepository.save(eventForm.build());
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
    public List<Event> getEventsByTag(String tag) {
        return eventRepository.findEventsByTag(tag);
    }

    @Transactional(readOnly = true)
    public Page<Event> getPagedEvents(int index, int size) {
        PageRequest request = new PageRequest(index, size, Sort.Direction.DESC, EVENT_DATE_COLUMN_LABEL, EVENT_START_TIME_COLUMN_LABEL);

        return eventRepository.findAll(request);
    }

    private String standardTimeFormat(String time) {
        return time.matches(REGEX_TIME_FORMAT) ? time : time + ":00";
    }
}

