package org.dcsc.admin.controllers;

import org.dcsc.core.event.Event;
import org.dcsc.core.event.EventForm;
import org.dcsc.core.event.EventFormValidator;
import org.dcsc.core.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminEventRestController {
    private static final int EVENT_PAGE_SIZE = 10;

    @Autowired
    private EventFormValidator eventFormValidator;
    @Autowired
    private EventService eventService;

    @InitBinder("eventForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(eventFormValidator);
    }

    @RequestMapping(value = "/admin/r/events/{pageId}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('event',get)")
    public Page<Event> getEventsByPage(@PathVariable("pageId") String pageId) {
        int pageIndex = 0;

        try {
            pageIndex = Integer.parseInt(pageId);
        } catch (Exception e) {
            // swallow exception
        }

        return eventService.getPagedEvents(pageIndex, EVENT_PAGE_SIZE);
    }

    @RequestMapping(value = "/admin/r/event/{eventId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasPermission('event',delete)")
    public boolean deleteEventById(@PathVariable("eventId") String eventId) {
        boolean success = false;

        try {
            eventService.deleteEventById(Long.parseLong(eventId));
            success = true;
        } catch (Exception e) {
            // swallow exception
        }

        return success;
    }

    @CrossOrigin(origins = {"https://daviscsclub.org", "http://localhost:8080"}, methods = {RequestMethod.PUT})
    @RequestMapping(value = "/admin/r/event/{eventId}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('event',update)")
    public boolean updateEventById(@RequestBody EventForm eventForm, BindingResult bindingResult) {
        boolean success = false;

        if (!bindingResult.hasErrors()) {
            eventService.saveEvent(eventForm);
            success = true;
        }

        return success;
    }
}
