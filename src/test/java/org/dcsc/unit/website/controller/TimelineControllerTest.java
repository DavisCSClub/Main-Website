package org.dcsc.unit.website.controller;

import org.dcsc.event.Event;
import org.dcsc.event.ReadOnlyEventService;
import org.dcsc.website.controller.TimelineController;
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

import static org.junit.Assert.*;

/**
 * Created by tktong on 7/9/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimelineControllerTest {
    @Mock
    private ReadOnlyEventService eventService;
    @Mock
    private Page<Event> expectedPage;

    @InjectMocks
    private TimelineController timelineController = new TimelineController();

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(timelineController).build();

    @Test
    public void timelineDefaultPage() throws Exception {
        Mockito.when(eventService.getPagedEvents(Mockito.eq(0), Mockito.anyInt())).thenReturn(expectedPage);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/timeline");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("page", expectedPage))
                .andExpect(MockMvcResultMatchers.view().name("main/timeline"));
    }

    @Test
    public void timelinePageWithId() throws Exception {
        int pageId = 3;

        Mockito.when(eventService.getPagedEvents(Mockito.eq(pageId), Mockito.anyInt())).thenReturn(expectedPage);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/timeline?p=" + pageId);

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("page",expectedPage))
                .andExpect(MockMvcResultMatchers.view().name("main/timeline"));
    }
}