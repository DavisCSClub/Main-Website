package org.dcsc.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/7/2015.
 */
@Service
public class EventService {
    private static final String EVENT_DATE_COLUMN_LABEL = "date";
    private static final String EVENT_START_TIME_COLUMN_LABEL = "startTime";

    @Autowired
    private EventRepository eventRepository;

    public void saveEvent(long id, EventForm eventForm) {
        Optional<Event> event = eventRepository.findEventById(id);

        if(event.isPresent()) {
            saveEvent(event.get(), eventForm);
        }
    }

    public void saveEvent(EventForm eventForm) {
        saveEvent(new Event(), eventForm);
    }

    private void saveEvent(Event event, EventForm eventForm) {
        String name = eventForm.getName();
        String description = eventForm.getDescription();
        Date date = Date.valueOf(eventForm.getDate());
        Time startTime = Time.valueOf(eventForm.getStartTime());
        Time endTime = Time.valueOf(eventForm.getEndTime());
        String location = eventForm.getLocation();
        boolean isPublished = eventForm.isPublished();

        event.setName(name);
        event.setDescription(description);
        event.setDate(date);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setLocation(location);
        event.setPublished(isPublished);

        System.out.println("Event Saved");
        //eventRepository.save(event);
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
}

