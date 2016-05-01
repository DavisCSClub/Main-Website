package org.dcsc.listeners;

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
public class StompSessionConnectedEventListener implements ApplicationListener<SessionConnectedEvent> {

	private static final Logger logger = LoggerFactory.getLogger(StompSessionConnectedEventListener.class);

	@Override
	public void onApplicationEvent(SessionConnectedEvent sessionConnectedEvent) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectedEvent.getMessage());

		MessageHeaderAccessor accessor = NativeMessageHeaderAccessor.getAccessor(sessionConnectedEvent.getMessage(), SimpMessageHeaderAccessor.class);
		accessor.getMessageHeaders();
		Object header = accessor.getHeader("simpConnectMessage");

		MessageHeaders headers = accessor.getMessageHeaders();
    	SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");
    	String simpSessionId = (String) headers.get("simpSessionId");


		GenericMessage<?> generic = (GenericMessage<?>) accessor.getHeader("simpConnectMessage");
		System.out.println(generic.getHeaders().get("nativeHeaders"));



		logger.info(headerAccessor.toString());
	}
}