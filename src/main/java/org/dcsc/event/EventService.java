package org.dcsc.event;

import org.dcsc.activity.Actions;
import org.dcsc.activity.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
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
    @Autowired
    private ActivityService activityService;


    public Event saveEvent(long id, EventForm eventForm) throws SavingUndefinedEventException {
        Optional<Event> event = eventRepository.findEventById(id);

        if(event.isPresent()) {
            Event e = saveEvent(event.get(), eventForm);

            activityService.save("Event", "Updated event #" + id + " (" + e.getName() +").", Actions.UPDATE);

            return e;
        }
        else {
            throw new SavingUndefinedEventException("Attempted to save an event with id that does not exists.");
        }
    }

    public Event saveEvent(EventForm eventForm) {
        Event e =  saveEvent(new Event(), eventForm);

        activityService.save("Event", "Created event " + e.getName() +".", Actions.UPDATE);

        return e;
    }

    private Event saveEvent(Event event, EventForm eventForm) {
        String name = eventForm.getName();
        String description = eventForm.getDescription();
        Date date = Date.valueOf(eventForm.getDate());

        Time startTime = Time.valueOf(standardTimeFormat(eventForm.getStartTime()));
        Time endTime = Time.valueOf(standardTimeFormat(eventForm.getEndTime()));
        String location = eventForm.getLocation();
        boolean isPublished = eventForm.isPublished();

        event.setName(name);
        event.setDescription(description);
        event.setDate(date);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setLocation(location);
        event.setPublished(isPublished);

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

