package org.dcsc.compound.presentation.controller;

import org.dcsc.compound.presentation.constants.ViewNames;
import org.dcsc.core.model.event.Event;
import org.dcsc.core.service.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminEventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/admin/events")
    public String events(Model model) {
        List<Event> events = eventService.getAllEvents();

        model.addAttribute("events", events);

        return ViewNames.ADMIN_EVENT_LIST;
    }
}
