package org.dcsc.admin;

import org.dcsc.event.EventPlanner;
import org.dcsc.event.EventPlannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by tktong on 7/20/2015.
 */
@Controller
public class AdminEventController {
    @Autowired
    private EventPlannerService eventPlannerService;

    @RequestMapping(value = "/admin/events")
    public String events(Model model) {
        List<EventPlanner> plannedEvents = eventPlannerService.getAllEventPlanners();

        model.addAttribute("events", plannedEvents);

        return "admin/events";
    }
}
