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
import org.dcsc.athena.objects.Status;
import org.dcsc.athena.objects.StatusType;
import org.dcsc.athena.objects.TuteeForTutor;
import org.dcsc.athena.objects.TutoringSession;

import org.dcsc.athena.services.TutoringSessionService;
import org.dcsc.core.user.profile.UserProfileService;


import org.dcsc.core.tutor.Tutor;
import org.dcsc.core.tutor.TutorService;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.profile.UserProfile;


import org.dcsc.core.user.details.DcscUserDetails;
import org.springframework.security.core.Authentication;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.SimpMessageType;




import org.dcsc.athena.objects.DebugLib;
import org.dcsc.athena.objects.Person;
import java.util.*;
import java.util.concurrent.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import java.util.Optional;

@Controller
public class AxisController {

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@Autowired
	private AxisQueue axisQueue;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private DcscUserService dcscUserService;

    @Autowired
    private TutoringSessionService tutoringSessionService;

    // @Autowired
    // private UserProfileService userProfileService;

    @MessageMapping("/tuteeRegistration")
    public void registerTutor(SimpMessageHeaderAccessor headerAccessor, TuteeRegistration message) throws Exception {
    	
        // MessageHeaderAccessor accessor = NativeMessageHeaderAccessor.getAccessor(sessionConnectedEvent.getMessage(), SimpMessageHeaderAccessor.class);
        // accessor.getMessageHeaders();
        // Object header = accessor.getHeader("simpConnectMessage");


        MessageHeaders headers = headerAccessor.getMessageHeaders();
        SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");
        String simpSessionId = (String) headers.get("simpSessionId");


    	Tutee t = new Tutee(message.getEmail(), message.getEmail(), message.getTutoredSubject(), simpSessionId, message.getLocation());
    	axisQueue.addPersonMapping(t, simpSessionId);
    	messagingTemplate.convertAndSend("/topic/" + simpSessionId, axisQueue.add(t).statusData());
        DebugLib.println(axisQueue.queueStatus());
    }

    @MessageMapping("/requestTutee")
    public void requestTutee(SimpMessageHeaderAccessor headerAccessor, DummyRequest message) throws Exception {

        MessageHeaders headers = headerAccessor.getMessageHeaders();
        SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");
        String simpSessionId = (String) headers.get("simpSessionId");

    	Person p = axisQueue.getPersonFromID(simpSessionId);
    	if (p != null) {
    		TutorExtension t = (TutorExtension) p;




    		messagingTemplate.convertAndSend("/topic/" + simpSessionId, axisQueue.add(t).statusData());
    	}

    	DebugLib.println(axisQueue.queueStatus());
        // TuteeForTutor
    }


    @MessageMapping("/requestSetupTutor")
    @SendToUser("/queue/setup")
    public SetupResponse simpleSetupTutor(Authentication authentication, SimpMessageHeaderAccessor headerAccessor, DummyRequest message) throws Exception {
    	TreeSet<String> subs = new TreeSet<String>();


                MessageHeaders headers = headerAccessor.getMessageHeaders();
                SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");
                String simpSessionId = (String) headers.get("simpSessionId");

    	if (message.getType().equals("auth")) {
            Tutor tutor = tutorService.getTutor(authentication);

            if (tutor != null) {

                if (tutoringSessionService.findOpenTutoringSessionByTutorId(tutor.getId()).size() != 0 ) {
                        return new SetupResponse(axisQueue.getTutorList(), axisQueue.getQueueData(), simpSessionId, 203);
                }

                subs = tutor.getCurrentTermCourseStrings();                

                // TutorExtension t = new TutorExtension(tutor.getDcscUser().getUserProfile().getName(), tutor.getDcscUser().getUserProfile().getEmail(), tutor.getCurrentTermCourseStrings(), headerAccessor.getSessionId(), tutor);
                if (subs.size() == 0)
                    return new SetupResponse(axisQueue.getTutorList(), axisQueue.getQueueData(), simpSessionId, 101);

                DcscUser d = (dcscUserService.getUserById(tutor.getDcscUserId())).get();
                TutorExtension t = new TutorExtension(d.getUserProfile().getName(), d.getUserProfile().getEmail(), tutor.getCurrentTermCourseStrings(), simpSessionId, tutor);

                


                LocalDateTime now = LocalDateTime.now();
                TutoringSession thisSession = new TutoringSession(now, t.getTutor(), message.getLocation());
                // System.out.println(thisSession);
                tutoringSessionService.save(thisSession);
                // System.out.println(thisSession);

                TutoringSession t1 = tutoringSessionService.findTutoringSessionById(thisSession.getId());

                // System.out.println(t1);
                // tutoringSessionService.save(t1);

                axisQueue.setSession(simpSessionId, t1);


                // DebugLib.println(tutor.getCurrentTermCourseStrings().toString());
                // TutorExtension t = new TutorExtension("Alex Fu", "aafu@ucdavis.edu", subs, headerAccessor.getSessionId(), tutor);
                axisQueue.addPersonMapping(t, simpSessionId);
               
                DebugLib.println(axisQueue.queueStatus());    
            }
    		
    	}
    	
        return new SetupResponse(axisQueue.getTutorList(), axisQueue.getQueueData(), simpSessionId, 0);
    }

    @MessageMapping("/requestSetupTutee")
    @SendToUser("/queue/setup")
    public SetupResponse simpleSetupTutee(SimpMessageHeaderAccessor headerAccessor, DummyRequest message) throws Exception {    
                   MessageHeaders headers = headerAccessor.getMessageHeaders();
                SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");
                String simpSessionId = (String) headers.get("simpSessionId");    
        return new SetupResponse(axisQueue.getTutorList(), axisQueue.getQueueData(), simpSessionId);
    }

    @MessageMapping("/disconnectPairing")
    public void disconnect(SimpMessageHeaderAccessor headerAccessor, DummyRequest message) throws Exception {

        // TutoringSession thisSession = axisQueue.popSession(headerAccessor.getSessionId());

        

        // if (thisSession != null) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAA\n\n\n\n\n\n\n\n\nDDDDDDDDDDDDDDDDDDDDD\n\n\n\n\n\n\nDDDDDDDDDDDDDDDDDDD");
        //     thisSession.setEndDateTime(LocalDateTime.now());
        //     tutoringSessionService.save(thisSession);    
        // }
                MessageHeaders headers = headerAccessor.getMessageHeaders();
        SimpMessageType type = (SimpMessageType) headers.get("simpMessageType");
                String simpSessionId = (String) headers.get("simpSessionId");    

    	axisQueue.removePersonAndMappingByID(simpSessionId);
		DebugLib.println(axisQueue.queueStatus());

        // TuteeForTutor
    }

}