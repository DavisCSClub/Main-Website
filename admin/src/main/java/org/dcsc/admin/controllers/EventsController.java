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
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

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
    public void createEvent(HttpServletResponse response, @RequestBody Event event) {
        event.setDescription(HtmlUtils.htmlEscape(event.getDescription()));
        Event persistedEvent = eventService.saveEvent(event);

        response.setHeader(HttpHeaders.LOCATION, linkTo(EventsController.class).slash(persistedEvent.getId()).toUri().toString());
    }

    @RequestMapping(value = "/template", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEventTemplate() {
        return new Event();
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Event> getEvent(@PathVariable("eventId") int eventId) {
        Event event = eventService.getEventById(eventId).get();
        event.setDescription(HtmlUtils.htmlUnescape(event.getDescription()));
        return resourceAssembler.toResource(event);
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateEvent(HttpServletResponse response, @PathVariable("eventId") int eventId, @RequestBody Event event) {
        if (event.getId() != eventId) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            event.setDescription(HtmlUtils.htmlEscape(event.getDescription()));
            eventService.saveEvent(event);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable("eventId") int eventId) {
        eventService.deleteEventById(eventId);
    }
}
