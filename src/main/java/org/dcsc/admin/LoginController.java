package org.dcsc.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tktong on 7/15/15.
 */
@Controller
public class LoginController {
    @RequestMapping(value = "/login")
    public String login() {
        return "admin/login";
    }
}
