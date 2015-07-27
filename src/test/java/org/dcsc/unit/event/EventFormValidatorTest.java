package org.dcsc.unit.event;

import org.dcsc.event.EventForm;
import org.dcsc.event.EventFormValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class EventFormValidatorTest {
    private static final String NAME = "name";
    private static final String DATE = "0000-00-00";

    @Mock
    private EventForm eventForm;
    @Mock
    private Errors errors;

    private EventFormValidator eventFormValidator;

    @Before
    public void before() {
        eventFormValidator = new EventFormValidator();
    }

    @Test
    public void supportsTrue(){
        Assert.assertTrue(eventFormValidator.supports(EventForm.class));
    }

    @Test
    public void supportFalse() {
        Assert.assertFalse(eventFormValidator.supports(Object.class));
    }

    @Test
    public void validateEventNameSuccess() {
        Mockito.when(eventForm.getName()).thenReturn(NAME);
        Mockito.when(eventForm.isPublished()).thenReturn(false);

        eventFormValidator.validate(eventForm, errors);
    }

    @Test
    public void validateEventNameWithNullName() {
        Mockito.when(eventForm.getName()).thenReturn(null);
        Mockito.when(eventForm.isPublished()).thenReturn(false);

        eventFormValidator.validate(eventForm, errors);

        Mockito.verify(errors, Mockito.times(1)).reject(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void validateEventNameWithEmptyName() {
        Mockito.when(eventForm.getName()).thenReturn("");
        Mockito.when(eventForm.isPublished()).thenReturn(false);

        eventFormValidator.validate(eventForm, errors);

        Mockito.verify(errors, Mockito.times(1)).reject(Mockito.anyString(), Mockito.anyString());
    }
}