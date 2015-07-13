package org.dcsc.unit.website.controller;

import org.dcsc.event.Event;
import org.dcsc.event.ReadOnlyEventService;
import org.dcsc.website.controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

/**
 * Created by tktong on 7/7/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    @Mock
    private ReadOnlyEventService eventService;
    @Mock
    private Page<Event> page;
    @Mock
    private List<Event> eventList;

    @InjectMocks
    private HomeController homeController = new HomeController();

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();

    @Test
    public void home() throws Exception {
        Mockito.when(eventService.getPagedEvents(Mockito.anyInt(), Mockito.anyInt())).thenReturn(page);
        Mockito.when(page.getContent()).thenReturn(eventList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("events", eventList))
                .andExpect(MockMvcResultMatchers.view().name("main/home"));
    }
}