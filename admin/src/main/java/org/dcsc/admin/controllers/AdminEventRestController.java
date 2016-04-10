package org.dcsc.admin.controllers;

import org.dcsc.admin.dto.RestTransactionResult;
import org.dcsc.core.event.EventForm;
import org.dcsc.core.event.EventFormValidator;
import org.dcsc.core.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

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
    
    @RequestMapping(value = "/admin/r/event/{eventId}", method = RequestMethod.DELETE)
    @PreAuthorize("hasPermission('event','delete')")
    public RestTransactionResult deleteEventById(@PathVariable("eventId") String eventId) {
        boolean success;
        String message;

        try {
            eventService.deleteEventById(Long.parseLong(eventId));
            success = true;
            message = String.format("Event %s succesfully deleted.", eventId);
        } catch (Exception e) {
            success = false;
            message = String.format("Failed to delete event %s", eventId);
        }

        return new RestTransactionResult(success, message);
    }

    @CrossOrigin(origins = {"https://daviscsclub.org", "http://localhost:8080"}, methods = {RequestMethod.PUT})
    @RequestMapping(value = "/admin/r/event/{eventId}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('event','update')")
    public RestTransactionResult updateEventById(@PathVariable("eventId") String eventId,
                                                 @RequestBody EventForm eventForm,
                                                 BindingResult bindingResult) {
        boolean success = false;
        String message = null;

        if (!bindingResult.hasErrors()) {
            eventForm.setDescription(HtmlUtils.htmlEscape(eventForm.getDescription()));
            eventService.saveEvent(eventForm);

            message = String.format("Event %s successfully updated.", eventId);
            success = true;
        } else {
            message = String.format("Failed to update event %s.", eventId);
        }

        return new RestTransactionResult(success, message);
    }
}
