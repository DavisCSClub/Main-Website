package org.dcsc.admin.controllers;

import org.dcsc.admin.view.model.event.EventResourceAssembler;
import org.dcsc.core.event.Event;
import org.dcsc.core.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/admin/r/events")
@RestController("adminEventsController")
public class EventsController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventResourceAssembler resourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResources<Event> getEvents(Pageable pageable, PagedResourcesAssembler assembler) {
        return assembler.toResource(eventService.getEvents(pageable), resourceAssembler);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createEvent(HttpServletResponse response) {
        //eventService.saveEvent()
        response.setHeader(HttpHeaders.LOCATION, "");
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Event> getEvent(@PathVariable("eventId") int eventId) {
        return resourceAssembler.toResource(eventService.getEventById(eventId).get());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{eventId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateEvent(@PathVariable("eventId") int eventId) {

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable("eventId") int eventId) {
        eventService.deleteEventById(eventId);
    }
}
