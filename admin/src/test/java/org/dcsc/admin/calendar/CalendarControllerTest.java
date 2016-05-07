package org.dcsc.admin.calendar;

import org.dcsc.admin.dto.FullCalendarEvent;
import org.dcsc.admin.dto.TutorOfficeHourTransaction;
import org.dcsc.core.time.AcademicTerm;
import org.dcsc.core.time.AcademicTermService;
import org.dcsc.core.tutor.OfficeHour;
import org.dcsc.core.tutor.OfficeHourService;
import org.dcsc.core.tutor.Tutor;
import org.dcsc.core.tutor.TutorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalendarControllerTest {
    @Mock
    private AcademicTermService academicTermService;
    @Mock
    private OfficeHourService officeHourService;
    @Mock
    private TutorService tutorService;

    @InjectMocks
    private CalendarController controller;

    @Mock
    private Authentication authentication;
    @Mock
    private Tutor tutor;
    @Mock
    private List<OfficeHour> officeHours;
    @Mock
    private Stream officeHourStream;
    @Mock
    private Collection<FullCalendarEvent> calendarEvents;
    @Mock
    private AcademicTerm currentTerm;
    @Mock
    private TutorOfficeHourTransaction createTransaction;

    @Test
    public void getOfficeHoursBetweenStartAndEndDate() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();

        when(tutorService.getTutor(authentication)).thenReturn(tutor);
        when(tutor.getCurrentOfficeHours()).thenReturn(officeHours);
        when(officeHours.stream()).thenReturn(officeHourStream);
        when(officeHourStream.filter(any(Predicate.class))).thenReturn(officeHourStream);
        when(officeHourStream.map(any(Function.class))).thenReturn(officeHourStream);
        when(officeHourStream.collect(any(Collector.class))).thenReturn(calendarEvents);

        assertEquals(calendarEvents, controller.getCalendarEvents(authentication, startDate, endDate));
    }

    @Test
    public void createSingleOfficeHour() throws Exception {
        LocalDate currentTermEndDate = LocalDate.now();
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();

        when(academicTermService.getCurrentTerm()).thenReturn(currentTerm);
        when(tutorService.getTutor(authentication)).thenReturn(tutor);
        when(currentTerm.getEndDate()).thenReturn(currentTermEndDate);
        when(createTransaction.getStart()).thenReturn(start);
        when(createTransaction.getEnd()).thenReturn(end);
        when(createTransaction.isRepeat()).thenReturn(false);

        controller.createOfficeHour(authentication, createTransaction);

        verify(officeHourService).save(any(OfficeHour.class));
    }

    @Test
    public void createRepeatingOfficeHour() throws Exception {
        LocalDate currentTermEndDate = LocalDate.now();
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();

        when(academicTermService.getCurrentTerm()).thenReturn(currentTerm);
        when(tutorService.getTutor(authentication)).thenReturn(tutor);
        when(currentTerm.getEndDate()).thenReturn(currentTermEndDate);
        when(createTransaction.getStart()).thenReturn(start);
        when(createTransaction.getEnd()).thenReturn(end);
        when(createTransaction.isRepeat()).thenReturn(true);

        controller.createOfficeHour(authentication, createTransaction);

        verify(officeHourService).save(any(List.class));
    }
}