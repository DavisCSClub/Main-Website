package org.dcsc.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tktong on 7/28/2015.
 */
@Controller
public class OfficerController {
    @RequestMapping(value = "/officers")
    public String officers() {
        return "main/officers";
    }
}
