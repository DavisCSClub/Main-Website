package org.dcsc.event;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tktong on 7/22/2015.
 */
@Component
public class EventFormValidator implements Validator {
    private static final String REGEX_DATE_FORMAT = "([0-9]{4})-([0-9]{2})-([0-9]{2})";
    private static final String REGEX_TIME_FORMAT_WITHOUT_SECONDS = "([0-9]{2}):([0-9]{2})";
    private static final String REGEX_TIME_FORMAT = "([0-9]{2}):([0-9]{2}):([0-9]{2})";

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(EventForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventForm eventForm = (EventForm) target;

        validateEventName(errors, eventForm);

        // Only perform the remaining validations if the event is to be published.
        if(eventForm.isPublished()) {
            validateDate(errors, eventForm);
            validateTimes(errors, eventForm);
            validateLocation(errors, eventForm);
        }
    }

    private void validateEventName(Errors errors, EventForm eventForm) {
        String name = eventForm.getName();

        if( (name == null) || (name.length() <= 0) ) {
            errors.reject("event.no_name", "Event does not have a name.");
        }
    }

    private void validateDate(Errors errors, EventForm eventForm) {
        String date = eventForm.getDate();

        if( (date == null) || (date.length() <= 0) ) {
            errors.reject("event.no_date", "Event does not have a defined date.");
        }
        else if(!date.matches(REGEX_DATE_FORMAT)) {
            errors.reject("event.invalid_date", "Event date has invalid string format.");
        }
    }

    private void validateTimes(Errors errors, EventForm eventForm) {
        String startTime = eventForm.getStartTime();
        String endTime = eventForm.getEndTime();

        // Null check and empty string check
        if( (startTime == null) || (endTime == null) || (startTime.length() <= 0) || (endTime.length() <= 0) ) {
            errors.reject("event.no_time", "Event does not have a defined start or end time.");
        }

        startTime = standardizeTimeFormat(startTime);
        endTime = standardizeTimeFormat(endTime);

        // Check if it matches our standard format
        if( !(startTime.matches(REGEX_TIME_FORMAT) && endTime.matches(REGEX_TIME_FORMAT)) ) {
            errors.reject("event.invalid_time", "Event start or end time has invalid string format.");
        }
        else { // Check if the end time is after the start time
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

            try {
                Date start = dateFormat.parse(startTime);
                Date end = dateFormat.parse(endTime);

                if(start.after(end)) {
                    errors.reject("event.invalid_start_time", "Event start time cannot be after event end time.");
                }
            } catch(Exception e) {
                System.out.println("here");
                errors.reject("event.invalid_time", "Date parsing error.");
            }
        }
    }

    private void validateLocation(Errors errors, EventForm eventForm) {
        String location = eventForm.getLocation();

        if( (location == null) || (location.length() <= 0) ) {
            errors.reject("event.no_location", "Event does not have a defined location.");
        }
    }

    private String standardizeTimeFormat(String time) {
        return (time.matches(REGEX_TIME_FORMAT_WITHOUT_SECONDS) ? (time + ":00") : time);
    }
}
