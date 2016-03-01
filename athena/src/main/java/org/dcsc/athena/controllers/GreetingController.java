package org.dcsc.athena.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import javax.servlet.http.HttpSession;
import org.dcsc.athena.objects.Greeting;
import org.dcsc.athena.objects.HelloMessage;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Controller
public class GreetingController {


    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(SimpMessageHeaderAccessor headerAccessor, HelloMessage message) throws Exception {
        System.out.println("AAAAAAAAAAAAAZZZZAAAAAAAAAA\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n " + headerAccessor.getSessionId() + " \n\n\n\n\n\n\n\n\n\n\n\nAAAAAAAAAAAAAAA");
        return new Greeting("Hello, " + message.getName() + "!");

    }


// @MessageMapping("/room/greeting/{room}")
//   public Greeting greet(@DestinationVariable String room, HelloMessage message) throws Exception {
//     return new Greeting("Hello, " + message.getName() + "!");
//   }
}