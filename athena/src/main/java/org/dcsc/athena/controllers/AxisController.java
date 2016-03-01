package org.dcsc.athena.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.dcsc.athena.objects.TuteeAuthorization;
import org.dcsc.athena.objects.TuteeRegistration;
import org.dcsc.athena.objects.GenericResponse;
import org.dcsc.athena.objects.DummyRequest;
import org.dcsc.athena.objects.SetupResponse;
import org.dcsc.athena.objects.AxisQueue;
import org.dcsc.athena.objects.TutorExtension;
import org.dcsc.athena.objects.Tutee;
import org.dcsc.athena.objects.TuteeForTutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.dcsc.athena.objects.DebugLib;
import org.dcsc.athena.objects.Person;
import java.util.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.util.TreeSet;


@Controller
public class AxisController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private AxisQueue axisQueue;

    @MessageMapping("/tuteeRegistration")
    public void registerTutor(SimpMessageHeaderAccessor headerAccessor, TuteeRegistration message) throws Exception {
    	//Get the name and pass it in instead of the email.
    	Tutee t = new Tutee(message.getEmail(), message.getEmail(), message.getTutoredSubject(), headerAccessor.getSessionId(), message.getLocation());
    	axisQueue.addPersonMapping(t, headerAccessor.getSessionId());
    	messagingTemplate.convertAndSend("/topic/" + headerAccessor.getSessionId(), axisQueue.add(t).statusData());
        DebugLib.println(axisQueue.queueStatus());
    }

    @MessageMapping("/requestTutee")
    public void requestTutee(SimpMessageHeaderAccessor headerAccessor, DummyRequest message) throws Exception {
    	Person p = axisQueue.getPersonFromID(headerAccessor.getSessionId());
    	if (p != null) {
    		TutorExtension t = (TutorExtension) p;
    		messagingTemplate.convertAndSend("/topic/" + headerAccessor.getSessionId(), axisQueue.add(t).statusData());
    	}

    	DebugLib.println(axisQueue.queueStatus());
        // TuteeForTutor
    }

    @MessageMapping("/requestSetup")
    @SendToUser("/queue/setup")
    public SetupResponse simpleSetup(SimpMessageHeaderAccessor headerAccessor, DummyRequest message) throws Exception {
    	TreeSet<String> subs = new TreeSet<String>();
    	if (message.getType().equals("auth")) {
    		subs.add("30");
    		TutorExtension t = new TutorExtension("Alex Fu", "aafu@ucdavis.edu", subs, headerAccessor.getSessionId());	
    		axisQueue.addPersonMapping(t, headerAccessor.getSessionId());
    		DebugLib.println(axisQueue.queueStatus());
    	}
    	
        return new SetupResponse(axisQueue.getTutorList(), axisQueue.getQueueData(), headerAccessor.getSessionId());
    }



    @MessageMapping("/disconnectPairing")
    public void disconnect(SimpMessageHeaderAccessor headerAccessor, DummyRequest message) throws Exception {

    	axisQueue.removePersonAndMappingByID(headerAccessor.getSessionId());
		DebugLib.println(axisQueue.queueStatus());

        // TuteeForTutor
    }


}