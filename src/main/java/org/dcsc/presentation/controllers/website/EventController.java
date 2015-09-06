package org.dcsc.presentation.controllers.website;

import org.dcsc.model.event.Event;
import org.dcsc.presentation.constants.ModelAttributeNames;
import org.dcsc.presentation.constants.ViewNames;
import org.dcsc.service.event.EventService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * Created by tktong on 7/9/2015.
 */
@Controller
public class EventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/event/{eventId}")
    public String event(@PathVariable("eventId") long eventId, Model model) {
        Optional<Event> event = eventService.getEventById(eventId);

        if(!event.isPresent()) {
            return "redirect:/error/404";
        }

        model.addAttribute(ModelAttributeNames.EVENT, event.get());

        return ViewNames.EVENT;
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException e) {
        return "redirect:/timeline";
    }
}
