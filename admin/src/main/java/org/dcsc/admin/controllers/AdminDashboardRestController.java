package org.dcsc.admin.controllers;

import org.dcsc.core.event.EventAttendanceDTO;
import org.dcsc.core.event.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Deprecated
@RestController
public class AdminDashboardRestController {
    private static final int DEFAULT_NUM_DAYS = 30;

    @Autowired
    private EventService eventService;

    @RequestMapping("/admin/r/here/{days}")
    public Set<EventAttendanceDTO> attendanceForLastNDays(String stringDays) {
        int days = DEFAULT_NUM_DAYS;

        try {
            days = Integer.parseInt(stringDays);
        } catch (Exception e) {
            // exception swallowed
        }

        return eventService.getLastNDayEvents(days);
    }
}
