package org.dcsc.activity.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.dcsc.activity.Action;
import org.dcsc.activity.ActivityService;
import org.dcsc.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by tktong on 8/6/2015.
 */
@Aspect
@Component
public class EventServiceAspect {
    @Autowired
    private ActivityService activityService;

    @AfterReturning(
            pointcut = "execution(* org.dcsc.event.EventService.saveEvent(long,org.dcsc.event.form.EventForm))",
            returning = "result"
    )
    public void saveEventWithId(JoinPoint joinPoint, Object result) {
        Event event = (Event) result;

        logActivity(String.format("Event #%d (%s) updated.", event.getId(), event.getName()), Action.UPDATE);
    }

    @AfterReturning(
            pointcut = "execution(* org.dcsc.event.EventService.saveEvent(org.dcsc.event.form.EventForm))",
            returning = "result"
    )
    public void createEvent(JoinPoint joinPoint, Object result) {
        Event event = (Event) result;

        logActivity(String.format("Event #%d (%s) created.", event.getId(), event.getName()), Action.CREATE);
    }

    private void logActivity(String description, Action action) {
        activityService.save("Event", description, action);
    }
}
