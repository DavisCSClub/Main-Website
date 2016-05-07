package org.dcsc.admin.events;

import org.dcsc.admin.events.EventsController;
import org.dcsc.admin.events.EventResourceAssembler;
import org.dcsc.core.event.Event;
import org.dcsc.core.event.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EventsControllerTest {
    private static final int EVENT_ID = 1;
    private static final String ESCAPED_DESCRIPTION = "&lt;p&gt;hi&lt;/p&gt;";
    private static final String UNESCAPED_DESCRIPTION = "<p>hi</p>";

    @Mock
    private EventService eventService;
    @Mock
    private EventResourceAssembler eventResourceAssembler;

    @InjectMocks
    private EventsController eventsController;

    @Mock
    private Event event;
    @Mock
    private HttpServletResponse response;

    @Test
    public void getEvents() {
        Page<Event> events = mock(Page.class);
        Pageable pageable = mock(Pageable.class);
        PagedResourcesAssembler pagedResourcesAssembler = mock(PagedResourcesAssembler.class);
        PagedResources<Event> eventPagedResources = mock(PagedResources.class);

        when(eventService.getEvents(pageable)).thenReturn(events);
        when(pagedResourcesAssembler.toResource(events, eventResourceAssembler)).thenReturn(eventPagedResources);

        assertEquals(eventPagedResources, eventsController.getEvents(pageable, pagedResourcesAssembler));
    }

    @Test
    public void getEvent() {
        Resource<Event> resource = mock(Resource.class);

        when(eventService.getEventById(EVENT_ID)).thenReturn(Optional.of(event));
        when(event.getDescription()).thenReturn(ESCAPED_DESCRIPTION);
        when(eventResourceAssembler.toResource(event)).thenReturn(resource);

        assertEquals(resource, eventsController.getEvent(EVENT_ID));
        verify(event).setDescription(UNESCAPED_DESCRIPTION);
    }

    @Test
    public void getEventTemplate() {
        assertTrue(eventsController.getEventTemplate() instanceof Event);
    }

    @Test
    public void createEvent() {
        when(event.getDescription()).thenReturn(UNESCAPED_DESCRIPTION);
        when(eventService.saveEvent(event)).thenReturn(event);
        when(event.getId()).thenReturn((long) EVENT_ID);

        eventsController.createEvent(event);

        verify(event).setDescription(ESCAPED_DESCRIPTION);
    }

    @Test
    public void updateEventWithMismatchedIds() {
        when(event.getId()).thenReturn((long) EVENT_ID);

        eventsController.updateEvent(response, 1234, event);

        verify(response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }

    @Test
    public void updateEvent() {
        when(event.getId()).thenReturn((long) EVENT_ID);
        when(event.getDescription()).thenReturn(UNESCAPED_DESCRIPTION);

        eventsController.updateEvent(response, EVENT_ID, event);

        verify(response).setStatus(HttpServletResponse.SC_NO_CONTENT);
        verify(event).setDescription(ESCAPED_DESCRIPTION);
        verify(eventService).saveEvent(event);
    }

    @Test
    public void deleteEvent() {
        eventsController.deleteEvent(EVENT_ID);
        verify(eventService).deleteEventById(EVENT_ID);
    }
}