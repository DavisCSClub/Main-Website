package org.dcsc.api.events;

import javassist.NotFoundException;
import org.dcsc.core.event.Event;
import org.dcsc.core.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@RestController("apiEventsController")
@RequestMapping("/api/events")
class EventsController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventResourceAssembler eventResourceAssembler;

    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.GET)
    public PagedResources<Event> getEvents(Pageable pageable, PagedResourcesAssembler assembler) {
        Page<Event> events = eventService.getEvents(pageable);
        return assembler.toResource(events, eventResourceAssembler);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Resource<Event> createEvent(@RequestBody Event event) {
        event.setDescription(HtmlUtils.htmlEscape(event.getDescription()));
        Event persistedEvent = eventService.saveEvent(event);
        return eventResourceAssembler.toResource(persistedEvent);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Resource<Event> getEvent(@PathVariable("id") int id) throws NotFoundException {
        Event event = eventService.getEventById(id).orElseThrow(() -> new NotFoundException("No event with id"));
        return eventResourceAssembler.toResource(event);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object handleNotFound() {
        return new Object();
    }
}