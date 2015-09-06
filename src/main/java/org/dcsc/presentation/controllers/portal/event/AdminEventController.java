package org.dcsc.presentation.controllers.portal.event;

import org.dcsc.model.event.Event;
import org.dcsc.service.event.EventService;
import org.dcsc.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tktong on 7/20/2015.
 */
@Controller
public class AdminEventController {
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/admin/events")
    public String events(Model model) {
        List<Event> events = eventService.getAllEvents();

        model.addAttribute("events", events);

        return Template.ADMIN_EVENT_LIST;
    }
}
