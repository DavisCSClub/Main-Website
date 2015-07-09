package org.dcsc.unit.website.controller;

import org.dcsc.event.Event;
import org.dcsc.event.ReadOnlyEventService;
import org.dcsc.website.controller.EventController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

/**
 * Created by tktong on 7/9/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Optional.class)
public class EventControllerTest {
    @Mock private ReadOnlyEventService eventService;
    @Mock private Optional<Event> test;
    @Mock private Event expectedEvent;

    @InjectMocks private EventController eventController = new EventController();

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();

    @Test
    public void eventWithValidId() throws Exception {
        long eventId = 1;

        Whitebox.setInternalState(test, "value", expectedEvent);

        Mockito.when(eventService.getEventById(eventId)).thenReturn(test);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/event/" + eventId);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("event",expectedEvent))
                .andExpect(MockMvcResultMatchers.view().name("main/event"));
    }

    @Test
    public void eventWithIdNotFound() throws Exception {
        long eventId = 1;

        Mockito.when(eventService.getEventById(eventId)).thenReturn(test);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/event/" + eventId);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/error/404"));
    }
}