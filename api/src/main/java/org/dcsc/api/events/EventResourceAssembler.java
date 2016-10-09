package org.dcsc.api.events;

import org.dcsc.core.event.Event;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component("apiEventResourceAssembler")
class EventResourceAssembler extends ResourceAssemblerSupport<Event, Resource> {
    // Default constructor for Spring
    public EventResourceAssembler() {
        this(EventsController.class, Resource.class);
    }

    private EventResourceAssembler(Class<?> clazz, Class<Resource> resourceType) {
        super(clazz, resourceType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Resource<Event> toResource(Event event) {
        return createResourceWithId(event.getId(), event);
    }
}
