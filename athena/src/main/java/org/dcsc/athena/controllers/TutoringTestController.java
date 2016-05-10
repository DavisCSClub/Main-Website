package org.dcsc.athena.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TutoringTestController {
    @RequestMapping("/tutoring/pingPage")
    public String getTestPage() {
        return "athena/test";
    }
}
