package org.dcsc.compound.presentation.controller;

import org.dcsc.compound.presentation.constants.ViewNames;
import org.dcsc.core.model.event.Event;
import org.dcsc.core.service.event.EventService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Time;

@Controller
public class AdminEventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/admin/events")
    public String events() {
        return ViewNames.ADMIN_EVENT_LIST;
    }

    @RequestMapping(value = "/admin/event/{eventId}", method = RequestMethod.GET)
    public String event(@PathVariable("eventId") String eventId, Model model) {
        Event event = eventService.getEventById(Long.parseLong(eventId)).get();
        model.addAttribute("event", event);
        return ViewNames.ADMIN_EVENT_FORM;
    }

    @RequestMapping(value = "/admin/event/{eventId}/edit", method = RequestMethod.GET)
    public String eventEdit(@PathVariable("eventId") String eventId, Model model) {
        Event event = eventService.getEventById(Long.parseLong(eventId)).get();
        model.addAttribute("event", event);
        return ViewNames.ADMIN_EVENT_EDIT;
    }

    @RequestMapping(value = "/admin/event/create", method = RequestMethod.GET)
    public String eventCreate(Model model) {
        DateTime currentDateTime = DateTime.now(DateTimeZone.forID("America/Los_Angeles"));

        java.sql.Date date = new java.sql.Date(currentDateTime.getMillis());

        Event event = new Event();
        event.setName("Untitled Event");
        event.setDate(date);
        event.setStartTime(Time.valueOf("00:00:00"));
        event.setEndTime(Time.valueOf("00:00:00"));

        model.addAttribute("event", event);
        
        return ViewNames.ADMIN_EVENT_EDIT;
    }
}
