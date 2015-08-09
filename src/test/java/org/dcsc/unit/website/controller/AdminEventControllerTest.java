package org.dcsc.unit.website.controller;

import org.dcsc.event.Event;
import org.dcsc.event.EventService;
import org.dcsc.controllers.mainwebsite.EventController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.TypeMismatchException;
import org.springframework.ui.Model;

import java.util.Optional;

/**
 * Created by tktong on 7/9/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(EventController.class)
public class AdminEventControllerTest {
    private static final long EVENT_ID = 0;

    @Mock
    private EventService eventService;
    @Mock
    private Optional<Event> expectedOptional;
    @Mock
    private Event expectedEvent;
    @Mock
    private Model model;
    @Mock
    private TypeMismatchException exception;

    @InjectMocks
    private EventController eventController;

    @Test
    public void eventWithValidId() {
        Mockito.when(eventService.getEventById(EVENT_ID)).thenReturn(expectedOptional);
        Mockito.when(expectedOptional.isPresent()).thenReturn(true);
        Mockito.when(expectedOptional.get()).thenReturn(expectedEvent);

        String actualView = eventController.event(EVENT_ID, model);

        Mockito.verify(model).addAttribute("event", expectedEvent);

        Assert.assertEquals("main/event", actualView);
    }

    @Test
    public void eventWithIdNotFound() {
        Mockito.when(eventService.getEventById(EVENT_ID)).thenReturn(expectedOptional);
        Mockito.when(expectedOptional.isPresent()).thenReturn(false);

        String actualView = eventController.event(EVENT_ID, model);

        Assert.assertEquals("redirect:/error/404", actualView);
    }

    @Test
    public void invalidEventId() {
        String redirect = eventController.handleTypeMismatchException(exception);

        Assert.assertEquals("redirect:/timeline", redirect);
    }
}