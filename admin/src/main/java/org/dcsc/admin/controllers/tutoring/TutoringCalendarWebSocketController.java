package org.dcsc.admin.controllers.tutoring;

import org.dcsc.admin.dto.RestTransactionResult;
import org.dcsc.admin.dto.TutorOfficeHourTransaction;
import org.dcsc.core.time.AcademicTerm;
import org.dcsc.core.time.AcademicTermService;
import org.dcsc.core.tutor.OfficeHour;
import org.dcsc.core.tutor.OfficeHourService;
import org.dcsc.core.tutor.Tutor;
import org.dcsc.core.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TutoringCalendarWebSocketController {
    @Autowired
    private AcademicTermService academicTermService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private OfficeHourService officeHourService;

    @MessageMapping("/tutor/calendar/add")
    @SendToUser("/queue/tutoring/calendar/notification")
    public RestTransactionResult addOfficeHour(Authentication authentication, @RequestBody TutorOfficeHourTransaction transaction) {
        RestTransactionResult result = null;

        try {
            AcademicTerm currentTerm = academicTermService.getCurrentTerm();
            Tutor tutor = tutorService.getTutor(authentication);
            LocalDate lastDayOfQuarter = currentTerm.getEndDate();
            LocalDateTime start = transaction.getStart();
            LocalDateTime end = transaction.getEnd();

            if (transaction.isRepeat()) {
                List<OfficeHour> officeHours = new ArrayList<>();
                officeHours.add(new OfficeHour(start, end, currentTerm, tutor));

                long numWeeks = ChronoUnit.WEEKS.between(end.toLocalDate(), lastDayOfQuarter);
                for (int i = 1; i <= numWeeks; i++) {
                    LocalDateTime officeHourStart = start.plusWeeks(i);
                    LocalDateTime officeHourEnd = end.plusWeeks(i);

                    OfficeHour officeHour = new OfficeHour(officeHourStart, officeHourEnd, currentTerm, tutor);
                    OfficeHour parent = officeHours.get(i - 1);
                    parent.setChildOfficeHour(officeHour);

                    officeHours.add(officeHour);
                }

                officeHourService.save(officeHours);
            } else {
                OfficeHour officeHour = new OfficeHour(start, end, currentTerm, tutor);
                officeHourService.save(officeHour);
            }

            result = RestTransactionResult.success("Office hour successfully added.");
        } catch (Exception e) {
            e.printStackTrace();
            result = RestTransactionResult.fail("Failed to add course. Cause: " + e.getClass().getSimpleName());
        }

        return result;
    }

}
