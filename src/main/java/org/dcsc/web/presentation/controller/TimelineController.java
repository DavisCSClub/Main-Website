package org.dcsc.web.presentation.controller;

import org.dcsc.core.model.event.Event;
import org.dcsc.web.presentation.constants.ModelAttributeNames;
import org.dcsc.web.presentation.constants.ViewNames;
import org.dcsc.core.service.event.EventService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by tktong on 7/8/2015.
 */
@Controller
public class TimelineController {
    private static final int NUM_EVENTS_PER_PAGE = 5;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/timeline")
    public String timeline(@RequestParam(value = "p", defaultValue = "0") int pageId, Model model) {
        Page<Event> page = eventService.getPagedEvents(pageId, NUM_EVENTS_PER_PAGE);

        model.addAttribute(ModelAttributeNames.PAGE, page);

        return ViewNames.TIMELINE;
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException e) {
        return "redirect:/timeline";
    }
}
