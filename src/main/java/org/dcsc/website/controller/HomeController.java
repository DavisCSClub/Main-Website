package org.dcsc.website.controller;

import org.dcsc.event.Event;
import org.dcsc.event.ReadOnlyEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tktong on 7/7/2015.
 */
@Controller
public class HomeController {
    @Autowired
    private ReadOnlyEventService eventService;

    @RequestMapping(value = "/")
    public String home(Model model) {
        Page<Event> page = eventService.getPagedEvents(0, 8);

        model.addAttribute("events", page.getContent());

        return "main/home";
    }
}
