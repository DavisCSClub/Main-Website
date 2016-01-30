package org.dcsc.admin.controllers;

import org.dcsc.admin.constants.AttributeNames;
import org.dcsc.admin.constants.ViewNames;
import org.dcsc.admin.profile.ProfileForm;
import org.dcsc.core.activity.Activity;
import org.dcsc.core.activity.ActivityService;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.details.DcscUserDetails;
import org.dcsc.core.user.permission.RolePermissionService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminProfileController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private DcscUserService userService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String profile(Authentication authentication, Model model) {
        DcscUserDetails userDetails = ((DcscUserDetails) authentication.getPrincipal());
        DcscUser user = userDetails.getUser();

        model.addAttribute(AttributeNames.USER, user);
        model.addAttribute("permissions", userDetails.getPermissions());
        model.addAttribute("activities", activityService.getAllActivities(user.getId()));

        return ViewNames.ADMIN_PROFILE;
    }

    @RequestMapping(value = "/admin/user/{user_id}", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('user','read')")
    public String profileView(@PathVariable("user_id") long id, Model model) {
        Optional<DcscUser> dcscUser = userService.getUserById(id);

        if (!dcscUser.isPresent()) {
            return "redirect:/admin/user";
        }

        DcscUser user = dcscUser.get();

        List<Activity> list = activityService.getAllActivities(user.getId());

        model.addAttribute(AttributeNames.USER, user);
        model.addAttribute("permissions", rolePermissionService.getPermissionMap(user.getRoleId()));
        model.addAttribute("activities", list);

        return ViewNames.ADMIN_PROFILE;
    }

    @RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
    public String profileEdit(Model model) {
        model.addAttribute("profileForm", new ProfileForm());

        return ViewNames.ADMIN_PROFILE_EDIT;
    }

    @ExceptionHandler(TypeMismatchException.class)
    public String invalidIdPage() {
        return "redirect:/admin/directory";
    }
}
