package org.dcsc.unit.website.controller;

import org.dcsc.event.Event;
import org.dcsc.event.EventService;
import org.dcsc.website.controller.HomeController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

import java.util.List;

/**
 * Created by tktong on 7/7/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {
    @Mock
    private EventService eventService;
    @Mock
    private Page<Event> page;
    @Mock
    private List<Event> eventList;
    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @Test
    public void home() {
        Mockito.when(eventService.getPagedEvents(Mockito.anyInt(), Mockito.anyInt())).thenReturn(page);
        Mockito.when(page.getContent()).thenReturn(eventList);

        String view = homeController.home(model);

        Mockito.verify(model).addAttribute("events",eventList);

        Assert.assertEquals("main/home", view);
    }
}