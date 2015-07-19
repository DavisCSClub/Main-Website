package org.dcsc.unit.website.controller;

import org.dcsc.event.Event;
import org.dcsc.event.ReadOnlyEventService;
import org.dcsc.website.controller.TimelineController;
import org.junit.Assert;
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
import org.springframework.ui.Model;

import static org.junit.Assert.*;

/**
 * Created by tktong on 7/9/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimelineControllerTest {
    @Mock private ReadOnlyEventService eventService;
    @Mock private Page<Event> expectedPage;
    @Mock private Model model;

    @InjectMocks
    private TimelineController timelineController;

    @Test
    public void timeline() {
        int pageId = 0;

        Mockito.when(eventService.getPagedEvents(Mockito.eq(pageId), Mockito.anyInt())).thenReturn(expectedPage);

        String view = timelineController.timeline(pageId, model);

        Mockito.verify(model).addAttribute("page", expectedPage);

        Assert.assertEquals("main/timeline", view);
    }
}