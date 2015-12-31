package org.dcsc.athena.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TutoringAboutController {
    @RequestMapping("/tutoring/about")
    public String getAboutPage() {
        return "athena/about";
    }
}
