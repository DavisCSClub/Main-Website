package org.dcsc.presentation.controllers.portal.event;

import org.dcsc.model.event.Event;
import org.dcsc.service.event.EventService;
import org.dcsc.model.event.EventForm;
import org.dcsc.logical.event.EventFormValidator;
import org.dcsc.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by tktong on 8/8/2015.
 */
@Controller
@RequestMapping(value = "/admin/events/event/create")
public class AdminEventCreationController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventFormValidator eventFormValidator;

    @InitBinder("eventForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(eventFormValidator);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String createEvent(Model model) {
        EventForm eventForm = new EventForm();

        eventForm.setName("New Event");
        model.addAttribute("eventForm", eventForm);

        return Template.ADMIN_EVENT_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveEvent(@Valid @ModelAttribute EventForm eventForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());

            return "redirect:/admin/events/event/create?error";
        }

        System.out.println(eventForm.getId());
        Event event = eventService.saveEvent(eventForm);

        return "redirect:/admin/events";
    }
}
