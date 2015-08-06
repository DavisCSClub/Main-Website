package org.dcsc.admin;

import org.dcsc.event.Event;
import org.dcsc.event.form.EventForm;
import org.dcsc.event.form.EventFormValidator;
import org.dcsc.event.EventService;
import org.dcsc.template.Template;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/20/2015.
 */
@Controller
public class AdminEventController {
    @Autowired
    private EventService eventService;
    @Autowired
    private EventFormValidator eventFormValidator;

    @InitBinder("eventForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(eventFormValidator);
    }

    @RequestMapping(value = "/admin/events")
    public String events(Model model) {
        List<Event> events = eventService.getAllEvents();

        model.addAttribute("events", events);

        return Template.ADMIN_EVENT_LIST;
    }

    @RequestMapping(value = "/admin/events/event/create", method = RequestMethod.GET)
    public String createEvent(Model model) {
        EventForm eventForm = new EventForm();

        eventForm.setName("New Event");
        model.addAttribute("eventForm", eventForm);

        return Template.ADMIN_EVENT_FORM;
    }

    @RequestMapping(value = "/admin/events/event/create", method = RequestMethod.POST)
    public String saveEvent(@Valid @ModelAttribute EventForm eventForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());

            return "redirect:/admin/events/event/create?error";
        }

        Event event = eventService.saveEvent(eventForm);

        return String.format("redirect:/admin/events/event/%d?success", event.getId());
    }

    @RequestMapping(value = "/admin/events/event/{eventId}", method = RequestMethod.GET)
    public String editEvent(@PathVariable("eventId") long eventId, Model model) {
        Optional<Event> event = eventService.getEventById(eventId);

        if(!event.isPresent()) {
            return "redirect:/error/404";
        }

        EventForm eventForm = new EventForm(event.get());

        model.addAttribute("eventForm", eventForm);

        return Template.ADMIN_EVENT_FORM;
    }

    @RequestMapping(value = "/admin/events/event/{eventId}", method = RequestMethod.POST)
    public String saveEvent(@PathVariable("eventId") long eventId, @Valid @ModelAttribute EventForm eventForm,
                            BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());

            return String.format("redirect:/admin/events/event/%d?error", eventId);
        }

        try {
            eventService.saveEvent(eventId, eventForm);
        } catch(Exception e) {
            return "redirect:/admin/events";
        }

        return String.format("redirect:/admin/events/event/%d?success", eventId);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException e) {
        return "redirect:/admin/events";
    }
}
