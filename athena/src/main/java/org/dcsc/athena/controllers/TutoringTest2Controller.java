package org.dcsc.athena.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;

@Controller

public class TutoringTest2Controller {

    @RequestMapping("/tutoring/test2")
    public String getTestPage(Model model, HttpServletRequest req) {

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      	String name = auth.getName(); //get logged in username
      	
      	if (name.equals("anonymousUser")) {
  			model.addAttribute("loggedIn", false);  
  			model.addAttribute("notLoggedIn", true);
  		 //    model.addAttribute("loggedIn", true);
  			// model.addAttribute("notLoggedIn", false);  

      	} else {
  			model.addAttribute("loggedIn", true);
  			model.addAttribute("notLoggedIn", false);  

      	}

      	model.addAttribute("hostname", req.getRemoteHost());
      	model.addAttribute("username", name);
      	model.addAttribute("requestsCompleted", 16);

        return "athena/test2";
    }
}