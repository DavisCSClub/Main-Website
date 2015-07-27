package org.dcsc.admin;

import org.dcsc.activity.Activity;
import org.dcsc.activity.ActivityService;
import org.dcsc.security.user.DcscUser;
import org.dcsc.security.userdetails.DcscUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by tktong on 7/18/15.
 */
@Controller
public class ProfileController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/admin/profile")
    public String profile(Authentication authentication, Model model) {
        DcscUser user =  ((DcscUserDetails) authentication.getPrincipal()).getUser();

        List<Activity> list = activityService.getAllActivities(user.getId());

        model.addAttribute("activities", list);

        return "admin/profile";
    }
}
