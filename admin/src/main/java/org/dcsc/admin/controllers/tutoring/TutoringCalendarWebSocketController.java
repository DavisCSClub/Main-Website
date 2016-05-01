package org.dcsc.admin.controllers.tutoring;

import org.dcsc.admin.dto.RestTransactionResult;
import org.dcsc.admin.dto.TutorOfficeHourDelete;
import org.dcsc.admin.dto.TutorOfficeHourEdit;
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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Deprecated
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
            result = RestTransactionResult.fail("Failed to add office hour. Cause: " + e.getClass().getSimpleName());
        }

        return result;
    }


    @MessageMapping("/tutor/calendar/delete")
    @SendToUser("/queue/tutoring/calendar/notification")
    public RestTransactionResult deleteOfficeHour(@RequestBody TutorOfficeHourDelete transaction) {
        RestTransactionResult result;

        try {
            officeHourService.delete(transaction.getId(), transaction.isDeleteFuture());
            result = RestTransactionResult.success("Office hour(s) successfully deleted.");
        } catch (Exception e) {
            e.printStackTrace();
            result = RestTransactionResult.fail("Failed to delete office hour. Cause: " + e.getClass().getSimpleName());
        }

        return result;
    }

    @MessageMapping("/tutor/calendar/edit")
    @SendToUser("/queue/tutoring/calendar/notification")
    public RestTransactionResult editOfficeHour(@RequestBody TutorOfficeHourEdit transaction) {
        RestTransactionResult result;

        if (transaction.isRepeat()) {
            LocalDateTime transactionStart = transaction.getStart();
            LocalDateTime transactionEnd = transaction.getEnd();
            DayOfWeek newStartDayOfWeek = transactionStart.getDayOfWeek();
            DayOfWeek newEndDayOfWeek = transactionEnd.getDayOfWeek();

            for (OfficeHour officeHour = officeHourService.getOfficeHour(transaction.getId()); officeHour != null; officeHour = officeHour.getChildOfficeHour()) {
                LocalDateTime startDateTime = officeHour.getStartDateTime();
                LocalDateTime endDateTime = officeHour.getEndDateTime();

                int startDayDifference = newStartDayOfWeek.getValue() - startDateTime.getDayOfWeek().getValue();
                int endDayDifference = newEndDayOfWeek.getValue() - endDateTime.getDayOfWeek().getValue();

                if (newStartDayOfWeek == DayOfWeek.SUNDAY) {
                    startDayDifference -= 7;
                } else if (startDateTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    startDayDifference += 7;
                }

                if (newEndDayOfWeek == DayOfWeek.SUNDAY) {
                    startDayDifference -= 7;
                } else if (endDateTime.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    startDayDifference += 7;
                }

                LocalDate newStartDate = startDateTime.plusDays(startDayDifference).toLocalDate();
                LocalDate newEndDate = endDateTime.plusDays(endDayDifference).toLocalDate();

                officeHour.setStartDateTime(LocalDateTime.of(newStartDate, transactionStart.toLocalTime()));
                officeHour.setEndDateTime(LocalDateTime.of(newEndDate, transactionEnd.toLocalTime()));

                officeHourService.save(officeHour);
            }
        } else {
            OfficeHour officeHour = officeHourService.getOfficeHour(transaction.getId());
            officeHour.setStartDateTime(transaction.getStart());
            officeHour.setEndDateTime(transaction.getEnd());
            officeHour.setChildOfficeHour(null);

            officeHourService.save(officeHour);
        }

        result = RestTransactionResult.success("Office hour(s) changes successfully saved.");

        return result;
    }
}
