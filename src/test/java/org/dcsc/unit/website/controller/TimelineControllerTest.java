package org.dcsc.unit.website.controller;

import org.dcsc.event.Event;
import org.dcsc.event.EventService;
import org.dcsc.website.controller.TimelineController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

/**
 * Created by tktong on 7/9/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class TimelineControllerTest {
    @Mock private EventService eventService;
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