package org.dcsc.admin.events;

import org.dcsc.admin.view.ViewController;
import org.dcsc.core.event.Event;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class EventResourceAssembler extends ResourceAssemblerSupport<Event, Resource> {
    public EventResourceAssembler() {
        this(EventsController.class, Resource.class);
    }

    public EventResourceAssembler(Class<?> controllerClass, Class<Resource> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public List<Resource> toResources(Iterable<? extends Event> events) {
        List<Resource> resources = new ArrayList<>();
        events.forEach(event -> resources.add(toResource(event)));
        return resources;
    }

    @Override
    public Resource toResource(Event event) {
        return new Resource(event, getRestEndpoint(event), getViewEndpoint(event));
    }


    private Link getRestEndpoint(Event event) {
        return linkTo(EventsController.class).slash(event.getId()).withSelfRel();
    }

    private Link getViewEndpoint(Event event) {
        return linkTo(ViewController.class).slash("event").slash(event.getId()).withRel("view");
    }
}
