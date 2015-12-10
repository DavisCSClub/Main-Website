package org.dcsc.admin.controllers;

import org.dcsc.admin.constants.AttributeNames;
import org.dcsc.admin.constants.ViewNames;
import org.dcsc.core.attendees.EventAttendeeService;
import org.dcsc.core.event.Event;
import org.dcsc.core.event.EventService;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Time;
import java.util.Date;

@Controller
public class AdminEventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventAttendeeService eventAttendeeService;

    @RequestMapping(value = "/admin/events")
    @PreAuthorize("hasPermission('event','read')")
    public String events() {
        return ViewNames.ADMIN_EVENT_LIST;
    }

    @RequestMapping(value = "/admin/event/{eventId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('event','read')")
    public String event(@PathVariable("eventId") String eventId, Model model) {
        long id = Long.parseLong(eventId);

        Event event = eventService.getEventById(id).get();
        model.addAttribute(AttributeNames.EVENT, event);
        model.addAttribute(AttributeNames.ATTENDANCE_COUNT, eventAttendeeService.getAttendanceCount(id));

        return ViewNames.ADMIN_EVENT_FORM;
    }

    @RequestMapping(value = "/admin/event/{eventId}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('event','update')")
    public String eventEdit(@PathVariable("eventId") String eventId, Model model) {
        Event event = eventService.getEventById(Long.parseLong(eventId)).get();
        model.addAttribute(AttributeNames.EVENT, event);
        return ViewNames.ADMIN_EVENT_EDIT;
    }

    @RequestMapping(value = "/admin/event/create", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('event','create')")
    public String eventCreate(Model model) {
        DateTime currentDateTime = DateTime.now(DateTimeZone.forID("America/Los_Angeles"));
        Date date = currentDateTime.toLocalDateTime().toDate();

        Event event = new Event();
        event.setName("Untitled Event");
        event.setDate(new java.sql.Date(date.getTime()));
        event.setStartTime(Time.valueOf("00:00:00"));
        event.setEndTime(Time.valueOf("00:00:00"));

        model.addAttribute(AttributeNames.EVENT, event);

        return ViewNames.ADMIN_EVENT_EDIT;
    }
}
