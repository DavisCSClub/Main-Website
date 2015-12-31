package org.dcsc.athena.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TutoringHomeController {
    @RequestMapping("/tutoring")
    public String getAthenaPage() {
        return "athena/index";
    }
}
