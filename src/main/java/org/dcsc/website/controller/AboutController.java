package org.dcsc.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tktong on 7/8/2015.
 */
@Controller
public class AboutController {
    @RequestMapping(value = "/about")
    public String about() {
        return "main/about";
    }
}
