package org.dcsc.admin.events;

import org.dcsc.core.event.Event;
import org.dcsc.core.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletResponse;

@RequestMapping("/admin/r/events")
@RestController("adminEventsController")
public class EventsController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventResourceAssembler resourceAssembler;

    @PreAuthorize("hasPermission('event', 'read')")
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResources<Event> getEvents(Pageable pageable, PagedResourcesAssembler assembler) {
        return assembler.toResource(eventService.getEvents(pageable), resourceAssembler);
    }

    @PreAuthorize("hasPermission('event', 'create')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createEvent(@RequestBody Event event) {
        event.setDescription(HtmlUtils.htmlEscape(event.getDescription()));
        eventService.saveEvent(event);
    }

    @PreAuthorize("hasPermission('event', 'read')")
    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Event> getEvent(@PathVariable("eventId") int eventId) {
        Event event = eventService.getEventById(eventId).get();
        event.setDescription(HtmlUtils.htmlUnescape(event.getDescription()));
        return resourceAssembler.toResource(event);
    }

    @PreAuthorize("hasPermission('event', 'update')")
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

    @PreAuthorize("hasPermission('event', 'delete')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{eventId}", method = RequestMethod.DELETE)
    public void deleteEvent(@PathVariable("eventId") int eventId) {
        eventService.deleteEventById(eventId);
    }

    @PreAuthorize("hasPermission('event', 'read')")
    @RequestMapping(value = "/template", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Event getEventTemplate() {
        return new Event();
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public void nonSupportedHttpMethods(HttpRequestMethodNotSupportedException exception) {
        // nop
    }
}
