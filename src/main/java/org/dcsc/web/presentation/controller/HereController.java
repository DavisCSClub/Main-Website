package org.dcsc.web.presentation.controller;

import org.dcsc.core.model.event.Event;
import org.dcsc.core.service.event.EventService;
import org.dcsc.web.service.HereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class HereController {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    @Autowired
    private HereService hereService;
    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/here", method = RequestMethod.GET)
    public String here(Model model) {
        List<Event> events = hereService.getCurrentEvent();

        model.addAttribute("events", events);

        return "main/here";
    }

    @RequestMapping(value = "/hereForm", method = RequestMethod.GET)
    public String hereForm(Model model, @RequestParam("event_id") String eventId) {
        int id = -1;

        try {
            id = Integer.parseInt(eventId);
        } catch (Exception e) {
            return "main/here-form::error";
        }

        Event event = eventService.getEventById(id).get();

        model.addAttribute("event", event);

        return "main/here-form::here-form";
    }

    @ResponseBody
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public boolean checkin(@RequestParam("email") String email, @RequestParam("event_id") String eventId) {
        boolean success = validEmail(email);
        int id = -1;

        try {
            id = Integer.parseInt(eventId);
        } catch (Exception e) {
            success = false;
        }

        if (success) {
            hereService.addAttendee(email, id);
        }

        return success;
    }

    @RequestMapping(value = "/privacy", method = RequestMethod.GET)
    public String privacyNotice() {
        return "main/privacy";
    }

    private boolean validEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
