package org.dcsc.admin.controllers;

import org.dcsc.admin.constants.ViewNames;
import org.dcsc.admin.profile.ProfileForm;
import org.dcsc.core.activity.Activity;
import org.dcsc.core.activity.ActivityService;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.details.DcscUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AdminProfileController {
    @Autowired
    private ActivityService activityService;

    @RequestMapping(value = "/admin/profile", method = RequestMethod.GET)
    public String profile(Authentication authentication, Model model) {
        DcscUser user = ((DcscUserDetails) authentication.getPrincipal()).getUser();

        List<Activity> list = activityService.getAllActivities(user.getId());

        model.addAttribute("activities", list);

        return ViewNames.ADMIN_PROFILE;
    }

    @RequestMapping(value = "/admin/profile/edit", method = RequestMethod.GET)
    public String profileEdit(Model model) {
        model.addAttribute("profileForm", new ProfileForm());

        return ViewNames.ADMIN_PROFILE_EDIT;
    }
}
