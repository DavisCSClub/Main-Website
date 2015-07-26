package org.dcsc.admin;

import org.dcsc.event.Event;
import org.dcsc.event.EventForm;
import org.dcsc.event.EventFormValidator;
import org.dcsc.event.EventService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
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

        return "admin/events";
    }

    @RequestMapping(value = "/admin/events/event/{eventId}", method = RequestMethod.GET)
    public String event(@PathVariable("eventId") long eventId, Model model) {
        Optional<Event> event = eventService.getEventById(eventId);

        if(!event.isPresent()) {
            return "redirect:/error/404";
        }

        Event e = event.get();
        EventForm eventForm = new EventForm(e);

        model.addAttribute("event", e);
        model.addAttribute("eventForm", eventForm);

        return "admin/event";
    }

    @RequestMapping(value = "/admin/events/event/{eventId}", method = RequestMethod.POST)
    public String saveEvent(@PathVariable("eventId") long eventId,
                            @Valid @ModelAttribute EventForm eventForm, BindingResult result,
                            RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());

            return "redirect:/admin/events/event/" + eventId + "?error";
        }

        eventService.saveEvent(eventId, eventForm);

        return "redirect:/admin/events/event/" + eventId + "?success";
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException e) {
        return "redirect:/admin/events";
    }
}
