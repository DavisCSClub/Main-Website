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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor; 
import org.springframework.messaging.support.NativeMessageHeaderAccessor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageType;


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


		MessageHeaderAccessor accessor = NativeMessageHeaderAccessor.getAccessor(sessionDisconnectEvent.getMessage(), SimpMessageHeaderAccessor.class);
		accessor.getMessageHeaders();
		Object header = accessor.getHeader("simpConnectMessage");

		MessageHeaders headers = accessor.getMessageHeaders();
    	SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");
    	String simpSessionId = (String) headers.get("simpSessionId");



		TutoringSession thisSession = axisQueue.popSession(simpSessionId);
		System.out.println("AAAAfdsfsdfsadgfdsfdsdsfdsAAAAAAAAAAAAAAAAAAA\n\n\n\n\n\n\n\n\nDDDDDDDDDDDDDDDDDDDDD\n\n\n\n\n\n\nDDDDDDDDDDDDDDDDDDD");
        if (thisSession != null) {
            thisSession.setEndDateTime(LocalDateTime.now());
            tutoringSessionService.save(thisSession);
        	DebugLib.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA"    );
        }

		axisQueue.removePersonAndMappingByID(simpSessionId);
		DebugLib.println(axisQueue.queueStatus());
		
	}
}