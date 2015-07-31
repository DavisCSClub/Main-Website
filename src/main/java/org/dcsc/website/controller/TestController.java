package org.dcsc.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tktong on 7/27/2015.
 */
@Controller
public class TestController {
    @RequestMapping(value = "/test")
    public String test(Model model) {
        return "";
    }
}
