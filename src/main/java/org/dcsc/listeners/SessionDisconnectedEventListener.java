package org.dcsc.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.dcsc.athena.objects.AxisQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.dcsc.athena.objects.DebugLib;
import org.dcsc.athena.services.TutoringSessionService;
import org.dcsc.athena.objects.TutoringSession;
import java.time.LocalDateTime;

@Component
public class SessionDisconnectedEventListener implements ApplicationListener<SessionDisconnectEvent> {

	private static final Logger logger = LoggerFactory.getLogger(SessionDisconnectedEventListener.class);

	@Autowired
	private AxisQueue axisQueue;

	@Autowired
    private TutoringSessionService tutoringSessionService;

	@Override
	public void onApplicationEvent(SessionDisconnectEvent sessionDisconnectEvent) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());

		TutoringSession thisSession = axisQueue.popSession(headerAccessor.getSessionId());

        if (thisSession != null) {
            thisSession.setEndDateTime(LocalDateTime.now());
            tutoringSessionService.save(thisSession);
        }

		axisQueue.removePersonAndMappingByID(headerAccessor.getSessionId());
		DebugLib.println(axisQueue.queueStatus());
		
	}
}