package org.dcsc.compound.presentation.controller;

import org.dcsc.compound.presentation.constants.ViewNames;
import org.dcsc.core.model.event.EventForm;
import org.dcsc.core.service.event.EventService;
import org.dcsc.logical.event.EventFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminEventCreationController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventFormValidator eventFormValidator;

    @InitBinder("eventForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(eventFormValidator);
    }

    @RequestMapping(value = "/admin/events/event/create", method = RequestMethod.GET)
    public String createEvent(Model model) {
        EventForm eventForm = new EventForm();

        eventForm.setName("New Event");
        model.addAttribute("eventForm", eventForm);

        return ViewNames.ADMIN_EVENT_FORM;
    }
}
