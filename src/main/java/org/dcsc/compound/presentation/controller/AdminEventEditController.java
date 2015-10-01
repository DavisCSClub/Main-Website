package org.dcsc.compound.presentation.controller;

import org.dcsc.compound.presentation.constants.ViewNames;
import org.dcsc.core.model.event.Event;
import org.dcsc.core.model.event.EventForm;
import org.dcsc.core.service.event.EventService;
import org.dcsc.logical.event.EventFormValidator;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping(value = "/admin/events/event")
public class AdminEventEditController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventFormValidator eventFormValidator;

    @InitBinder("eventForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(eventFormValidator);
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.GET)
    public String editEvent(@PathVariable("eventId") long eventId, Model model) {
        Optional<Event> event = eventService.getEventById(eventId);

        if (!event.isPresent()) {
            return "redirect:/error/404";
        }

        model.addAttribute("eventForm", new EventForm(event.get()));

        return ViewNames.ADMIN_EVENT_FORM;
    }

    @RequestMapping(value = "/{eventId}", method = RequestMethod.POST)
    public String saveEvent(@PathVariable(value = "eventId") long eventId, @Valid @ModelAttribute EventForm eventForm,
                            BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());

            return String.format("redirect:/admin/events/event/%d?error", eventId);
        }

        Event event = eventService.saveEvent(eventForm);

        return String.format("redirect:/admin/events/event/%d?success", event.getId());
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException e) {
        return "redirect:/admin/events";
    }
}
