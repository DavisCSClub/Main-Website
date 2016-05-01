package org.dcsc.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

// @Component

// public class PresenceChannelInterceptor extends ChannelInterceptorAdapter {

//     @Override
//     public Message<?> preSend(Message<?> message, MessageChannel channel) {
//         System.out.println("preSend");
//         return message;
//     }

//     @Override
//     public void postSend(Message<?> message, MessageChannel channel,
//             boolean sent) {
//         System.out.println("postSend");
//         System.out.println(channel.toString());
//     }

//     @Override
//     public void afterSendCompletion(Message<?> message, MessageChannel channel,
//             boolean sent, Exception ex) {
//         System.out.println("afterSendCompletion");
//     }

//     @Override
//     public boolean preReceive(MessageChannel channel) {
//         System.out.println("preReceive");
//         return true;
//     }

//     @Override
//     public Message<?> postReceive(Message<?> message, MessageChannel channel) {
//         System.out.println("postReceive");
//         return message;
//     }

//     @Override
//     public void afterReceiveCompletion(Message<?> message,
//             MessageChannel channel, Exception ex) {
//         System.out.println("afterReceiveCompletion");
//     }
// }




// import org.springframework.context.ApplicationListener;
// import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
// import org.springframework.stereotype.Component;
// import org.springframework.web.socket.messaging.SessionDisconnectEvent;
// import org.dcsc.athena.objects.AxisQueue;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.dcsc.athena.objects.DebugLib;
// import org.dcsc.athena.services.TutoringSessionService;
// import org.dcsc.athena.objects.TutoringSession;
// import java.time.LocalDateTime;

// @Bean
// public class PresenceChannelInterceptor extends ChannelInterceptorAdapter {
 
//     private static final Logger logger = LoggerFactory.getLogger(PresenceChannelInterceptor.class);
 
//     @Override
//     public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        
//         StompHeaderAccessor sha = StompHeaderAccessor.wrap(message);
//         System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

//         // ignore non-STOMP messages like heartbeat messages
//         if(sha.getCommand() == null) {
//             return;
//         }
 
//         String sessionId = sha.getSessionId();
 
//         switch(sha.getCommand()) {
//             case CONNECT:
//                 logger.debug("STOMP Connect [sessionId: " + sessionId + "]");
//                 System.out.println("1234AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//                 break;
//             case CONNECTED:
//                 logger.debug("STOMP Connected [sessionId: " + sessionId + "]");
//                 System.out.println("321AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//                 break;
//             case DISCONNECT:
//                 logger.debug("STOMP Disconnect [sessionId: " + sessionId + "]");
//                 System.out.println("A111AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//                 break;
//             default:
//                 break;
 
//         }
//     }
// }