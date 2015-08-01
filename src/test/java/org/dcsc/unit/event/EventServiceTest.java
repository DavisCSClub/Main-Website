package org.dcsc.unit.event;

import org.dcsc.activity.Actions;
import org.dcsc.activity.ActivityService;
import org.dcsc.event.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/7/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({EventService.class, Date.class, Time.class})
public class EventServiceTest {
    private static final long ID = 0;
    private static final String TAG = "tag";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String LOCATION = "location";
    private static final boolean IS_PUBLISHED = true;
    private static final String STRING_DATE = "1111-11-11";
    private static final String STRING_START_TIME = "00:00:00";
    private static final String STRING_END_TIME = "00:00:00";

    @Mock
    private EventRepository eventRepository;
    @Mock
    private ActivityService activityService;

    @InjectMocks
    private EventService eventService;

    @Mock
    private List<Event> expectedEventList;
    @Mock
    private Optional<Event> expectedEventOptional;
    @Mock
    private Event event;
    @Mock
    private EventForm eventForm;
    @Mock
    private PageRequest pageRequest;
    @Mock
    private Page page;
    @Mock
    private Date date;
    @Mock
    private Time startTime;
    @Mock
    private Time endTime;

    @Test(expected = SavingUndefinedEventException.class)
    public void saveEventWithInvalidId() throws SavingUndefinedEventException {
        Mockito.when(eventRepository.findEventById(ID)).thenReturn(expectedEventOptional);
        Mockito.when(expectedEventOptional.isPresent()).thenReturn(false);

        eventService.saveEvent(ID, eventForm);
    }

    @Test
    public void saveEventWithValidId() throws Exception {
        PowerMockito.mockStatic(Date.class);
        PowerMockito.mockStatic(Time.class);

        Mockito.when(eventRepository.findEventById(ID)).thenReturn(expectedEventOptional);
        Mockito.when(expectedEventOptional.isPresent()).thenReturn(true);
        Mockito.when(expectedEventOptional.get()).thenReturn(event);

        Mockito.when(eventForm.getName()).thenReturn(NAME);
        Mockito.when(eventForm.getDescription()).thenReturn(DESCRIPTION);
        Mockito.when(eventForm.getDate()).thenReturn(STRING_DATE);
        Mockito.when(Date.valueOf(STRING_DATE)).thenReturn(date);

        Mockito.when(eventForm.getStartTime()).thenReturn(STRING_START_TIME);
        Mockito.when(Time.valueOf(Mockito.anyString())).thenReturn(startTime);

        Mockito.when(eventForm.getEndTime()).thenReturn(STRING_END_TIME);
        Mockito.when(Time.valueOf(Mockito.anyString())).thenReturn(endTime);

        Mockito.when(eventForm.getLocation()).thenReturn(LOCATION);
        Mockito.when(eventForm.isPublished()).thenReturn(IS_PUBLISHED);
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        Event actualEvent = eventService.saveEvent(ID, eventForm);

        Mockito.verify(event).setName(NAME);
        Mockito.verify(event).setDescription(DESCRIPTION);
        Mockito.verify(event).setDate(date);
        Mockito.verify(event).setStartTime(Mockito.any(Time.class));
        Mockito.verify(event).setEndTime(Mockito.any(Time.class));
        Mockito.verify(event).setLocation(LOCATION);
        Mockito.verify(event).setPublished(IS_PUBLISHED);

        Mockito.verify(activityService).save(Mockito.anyString(), Mockito.anyString(), Mockito.eq(Actions.UPDATE));

        Assert.assertEquals(event, actualEvent);
    }

    @Test
    public void saveEventWithFormOnly () throws Exception {
        PowerMockito.mockStatic(Date.class);
        PowerMockito.mockStatic(Time.class);

        PowerMockito.whenNew(Event.class).withNoArguments().thenReturn(event);

        Mockito.when(eventForm.getName()).thenReturn(NAME);
        Mockito.when(eventForm.getDescription()).thenReturn(DESCRIPTION);
        Mockito.when(eventForm.getDate()).thenReturn(STRING_DATE);
        Mockito.when(Date.valueOf(STRING_DATE)).thenReturn(date);
        Mockito.when(eventForm.getStartTime()).thenReturn(STRING_START_TIME);
        Mockito.when(Time.valueOf(STRING_START_TIME)).thenReturn(startTime);
        Mockito.when(eventForm.getEndTime()).thenReturn(STRING_END_TIME);
        Mockito.when(Time.valueOf(STRING_END_TIME)).thenReturn(endTime);
        Mockito.when(eventForm.getLocation()).thenReturn(LOCATION);
        Mockito.when(eventForm.isPublished()).thenReturn(IS_PUBLISHED);
        Mockito.when(eventRepository.save(event)).thenReturn(event);

        Event actualEvent = eventService.saveEvent(eventForm);

        Mockito.verify(event).setName(NAME);
        Mockito.verify(event).setDescription(DESCRIPTION);
        Mockito.verify(event).setDate(date);
        Mockito.verify(event).setStartTime(Mockito.any(Time.class));
        Mockito.verify(event).setEndTime(Mockito.any(Time.class));
        Mockito.verify(event).setLocation(LOCATION);
        Mockito.verify(event).setPublished(IS_PUBLISHED);

        Assert.assertEquals(event, actualEvent);
    }

    @Test
    public void getAllEvents() {
        Mockito.when(eventRepository.findAll()).thenReturn(expectedEventList);

        Assert.assertEquals(expectedEventList, eventService.getAllEvents());
    }

    @Test
    public void getEventById() {
        Mockito.when(eventRepository.findEventById(ID)).thenReturn(expectedEventOptional);

        Assert.assertEquals(expectedEventOptional, eventService.getEventById(ID));
    }

    @Test
    public void getEventsByTag() {
        Mockito.when(eventRepository.findEventsByTag(TAG)).thenReturn(expectedEventList);

        Assert.assertEquals(expectedEventList, eventService.getEventsByTag(TAG));
    }

    @Test
    public void getPagedEvents() throws Exception {
        int INDEX = 0;
        int SIZE = 5;

        PowerMockito.whenNew(PageRequest.class)
                .withArguments(INDEX, SIZE, Sort.Direction.DESC, "date", "startTime")
                .thenReturn(pageRequest);

        Mockito.when(eventRepository.findAll(pageRequest)).thenReturn(page);

        Assert.assertEquals(page, eventService.getPagedEvents(INDEX, SIZE));
    }
}