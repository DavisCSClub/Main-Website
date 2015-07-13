package org.dcsc.unit.event;

import org.dcsc.event.Event;
import org.dcsc.event.EventRepository;
import org.dcsc.event.ReadOnlyEventService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by tktong on 7/7/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Optional.class)
public class ReadOnlyEventServiceTest {
    @Mock private EventRepository eventRepository;
    @Mock private List<Event> listEvents;
    @Mock private Optional<Event> optionalEvent;
    @Mock private Event event;
    @Mock private Page<Event> pagedEvent;

    @InjectMocks
    private ReadOnlyEventService readOnlyEventService;

    @Test
    public void getEventByValidId() {
        long id = 0;

        Mockito.when(eventRepository.findEventById(id)).thenReturn(optionalEvent);
        Mockito.when(optionalEvent.isPresent()).thenReturn(true);
        Mockito.when(optionalEvent.get()).thenReturn(event);

        Optional<Event> actualOptionalEvent = readOnlyEventService.getEventById(id);

        Assert.assertTrue(actualOptionalEvent.isPresent());
        Assert.assertEquals(event, actualOptionalEvent.get());
    }

    @Test
    public void getEventByInvalidId() {
        long id = 0;

        Mockito.when(eventRepository.findEventById(id)).thenReturn(optionalEvent);
        Mockito.when(optionalEvent.isPresent()).thenReturn(false);

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