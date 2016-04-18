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

public class TutoringIndexController {

    @RequestMapping("/tutoring/athena")
    public String getTestPage(Model model, HttpServletRequest req) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName(); //get logged in username

        model.addAttribute("loggedIn", !name.equals("anonymousUser"));
        model.addAttribute("hostname", req.getRemoteHost());
        model.addAttribute("username", name);
        model.addAttribute("requestsCompleted", 42);

        return "athena/index";
    }
}
