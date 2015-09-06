package org.dcsc.logical.event;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.dcsc.model.activity.Action;
import org.dcsc.service.activity.ActivityService;
import org.dcsc.model.event.Event;
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
            pointcut = "execution(* org.dcsc.model.event.EventService.saveEvent(long,org.dcsc.model.event.form.EventForm))",
            returning = "result"
    )
    public void saveEventWithId(JoinPoint joinPoint, Object result) {
        Event event = (Event) result;

        logActivity(String.format("Event #%d (%s) updated.", event.getId(), event.getName()), Action.UPDATE);
    }

    @AfterReturning(
            pointcut = "execution(* org.dcsc.model.event.EventService.saveEvent(org.dcsc.model.event.form.EventForm))",
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
