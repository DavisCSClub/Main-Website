package org.dcsc.admin.calendar;

import org.dcsc.core.time.AcademicTerm;
import org.dcsc.core.time.AcademicTermService;
import org.dcsc.core.tutor.OfficeHour;
import org.dcsc.core.tutor.OfficeHourService;
import org.dcsc.core.tutor.Tutor;
import org.dcsc.core.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/admin/r/calendar/tutoring")
@RestController("adminCalendarController")
public class CalendarController {
    @Autowired
    private AcademicTermService academicTermService;
    @Autowired
    private OfficeHourService officeHourService;
    @Autowired
    private TutorService tutorService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<FullCalendarEvent> getCalendarEvents(Authentication authentication,
                                                           @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                                           @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        Tutor tutor = tutorService.getTutor(authentication);
        List<OfficeHour> officeHours = tutor.getCurrentOfficeHours();

        return officeHours.stream()
                .filter(oh -> oh.getStartDateTime().getDayOfYear() >= start.getDayOfYear())
                .filter(oh -> oh.getEndDateTime().getDayOfYear() <= end.getDayOfYear())
                .map(oh -> new FullCalendarEvent(oh.getId(), "Office Hour", oh.getStartDateTime().toString(), oh.getEndDateTime().toString(), null))
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public void createOfficeHour(Authentication authentication, @RequestBody TutorOfficeHourTransaction transaction) throws Exception {
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
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateOfficeHour(@PathVariable("id") int id, @RequestParam("editFuture") boolean editFuture, @RequestBody TutorOfficeHourEdit transaction) {
        if (editFuture) {
            LocalDateTime transactionStart = transaction.getStart();
            LocalDateTime transactionEnd = transaction.getEnd();
            DayOfWeek newStartDayOfWeek = transactionStart.getDayOfWeek();
            DayOfWeek newEndDayOfWeek = transactionEnd.getDayOfWeek();

            for (OfficeHour officeHour = officeHourService.getOfficeHour(id); officeHour != null; officeHour = officeHour.getChildOfficeHour()) {
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
            OfficeHour officeHour = officeHourService.getOfficeHour(id);
            officeHour.setStartDateTime(transaction.getStart());
            officeHour.setEndDateTime(transaction.getEnd());
            officeHour.setChildOfficeHour(null);

            officeHourService.save(officeHour);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteOfficeHour(@PathVariable("id") int id, @RequestParam("deleteFuture") boolean deleteFuture) throws Exception {
        officeHourService.delete(id, deleteFuture);
    }
}
