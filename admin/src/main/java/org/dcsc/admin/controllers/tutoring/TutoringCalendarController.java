package org.dcsc.admin.controllers.tutoring;

import org.dcsc.admin.dto.FullCalendarEvent;
import org.dcsc.core.tutor.OfficeHour;
import org.dcsc.core.tutor.Tutor;
import org.dcsc.core.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TutoringCalendarController {
    @Autowired
    private TutorService tutorService;

    @RequestMapping("/admin/tutoring/calendar")
    public String tutorCalendar() {
        return "admin/tutoring-calendar";
    }

    @RequestMapping("/admin/r/tutoring/calendar")
    @ResponseBody
    public List<FullCalendarEvent> getOfficeHours(Authentication authentication, @RequestParam("start") String start, @RequestParam("end") String end) {
        Tutor tutor = tutorService.getTutor(authentication);

        List<OfficeHour> officeHours = tutor.getCurrentOfficeHours();

        return officeHours.stream()
                .map(oh -> new FullCalendarEvent("Office Hour", oh.getStartDateTime().toString(), oh.getEndDateTime().toString(), null))
                .collect(Collectors.toList());
    }
}
