package org.dcsc.unit.event;

import org.dcsc.event.Event;
import org.dcsc.event.form.EventForm;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class EventFormTest {
    private static final long ID = 0;
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String STRING_DATE = "date";
    private static final String STRING_START_TIME = "start time";
    private static final String STRING_END_TIME = "end time";
    private static final String LOCATION = "location";
    private static final boolean PUBLISHED = true;

    @Mock
    private Date date;
    @Mock
    private Time startTime;
    @Mock
    private Time endTime;
    @Mock
    private Event event;

    private EventForm eventForm;

    @Before
    public void before() {
        eventForm = new EventForm();
    }

    @Test
    public void constructorWithEventParameter() {
        Mockito.when(event.getId()).thenReturn(ID);
        Mockito.when(event.getName()).thenReturn(NAME);
        Mockito.when(event.getDescription()).thenReturn(DESCRIPTION);
        Mockito.when(event.getDate()).thenReturn(date);
        Mockito.when(date.toString()).thenReturn(STRING_DATE);
        Mockito.when(event.getStartTime()).thenReturn(startTime);
        Mockito.when(startTime.toString()).thenReturn(STRING_START_TIME);
        Mockito.when(event.getEndTime()).thenReturn(endTime);
        Mockito.when(endTime.toString()).thenReturn(STRING_END_TIME);
        Mockito.when(event.getLocation()).thenReturn(LOCATION);
        Mockito.when(event.isPublished()).thenReturn(PUBLISHED);

        eventForm = new EventForm(event);
    }

    @Test
    public void setAndGetId() {
        eventForm.setId(ID);

        Assert.assertEquals(ID, eventForm.getId());
    }

    @Test
    public void setAndGetName() {
        eventForm.setName(NAME);

        Assert.assertEquals(NAME, eventForm.getName());
    }

    @Test
    public void setAndGetDescription() {
        eventForm.setDescription(DESCRIPTION);

        Assert.assertEquals(DESCRIPTION, eventForm.getDescription());
    }

    @Test
    public void setAndGetDate() {
        eventForm.setDate(STRING_DATE);

        Assert.assertEquals(STRING_DATE, eventForm.getDate());
    }

    @Test
    public void setAndGetStartTime() {
        eventForm.setStartTime(STRING_START_TIME);

        Assert.assertEquals(STRING_START_TIME, eventForm.getStartTime());
    }

    @Test
    public void setAndGetEndTime() {
        eventForm.setEndTime(STRING_END_TIME);

        Assert.assertEquals(STRING_END_TIME, eventForm.getEndTime());
    }

    @Test
    public void setAndGetLocation() {
        eventForm.setLocation(LOCATION);

        Assert.assertEquals(LOCATION, eventForm.getLocation());
    }

    @Test
    public void setAndGetPublished() {
        eventForm.setPublished(PUBLISHED);

        Assert.assertEquals(PUBLISHED, eventForm.isPublished());
    }
}