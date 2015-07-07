package org.dcsc.event;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by tktong on 7/7/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReadOnlyEventServiceTest {
    @Mock
    private EventRepository eventRepository;
    @Mock
    private List<Event> listEvents;
    @Mock
    private Event event;
    @Mock
    private Page<Event> pagedEvent;
    @InjectMocks
    private ReadOnlyEventService readOnlyEventService;

    @Test
    public void getEventByValidId() {
        long id = 0;
        Optional<Event> optionalEvent = Optional.of(event);

        Mockito.when(eventRepository.findEventById(id)).thenReturn(optionalEvent);

        Optional<Event> actualOptionalEvent = readOnlyEventService.getEventById(id);

        Assert.assertTrue(actualOptionalEvent.isPresent());

        Event actualEvent = actualOptionalEvent.get();

        Assert.assertEquals(event, actualEvent);
    }

    @Test
    public void getEventByInvalidId() {
        long id = 0;
        Optional<Event> emptyEvent = Optional.empty();

        Mockito.when(eventRepository.findEventById(id)).thenReturn(emptyEvent);

        Optional<Event> actualOptionalEvent = readOnlyEventService.getEventById(id);

        Assert.assertFalse(actualOptionalEvent.isPresent());
    }


    @Test
    public void getEventsByTag() {
        String tag = "";

        Mockito.when(eventRepository.findEventsByTag(tag)).thenReturn(listEvents);

        List<Event> actualListEvents = readOnlyEventService.getEventsByTag(tag);

        Assert.assertEquals(listEvents, actualListEvents);
    }

    @Test
    public void getPagedEvents() {
        int index = 0;
        int size = 1;

        Mockito.when(eventRepository.findAll(Mockito.any(PageRequest.class))).thenReturn(pagedEvent);

        Page<Event> actualPagedEvent = readOnlyEventService.getPagedEvents(index, size);

        Assert.assertEquals(pagedEvent, actualPagedEvent);
    }
}