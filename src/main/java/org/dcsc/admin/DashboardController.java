package org.dcsc.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tktong on 7/15/15.
 */
@Controller
public class DashboardController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String dashboard() {
        return "admin/dashboard";
    }

}
