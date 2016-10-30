package org.dcsc.admin.users;

import org.dcsc.admin.view.ViewNames;
import org.dcsc.core.authentication.group.Group;
import org.dcsc.core.authentication.group.GroupService;
import org.dcsc.core.authentication.membership.MembershipService;
import org.dcsc.core.authentication.user.User;
import org.dcsc.core.authentication.user.UserService;
import org.dcsc.core.authentication.user.application.UserApplication;
import org.dcsc.core.authentication.user.application.UserApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@Controller
public class UserApplicationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserApplicationController.class);
    private static final String DUPLICATE_EMAIL_ERROR_MESSAGE = "There is already an account or application with this email. If this is an error, please contact the administrators at officers@daviscsclub.org";
    private static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error has occurred.";
    private static final String SUCCESS_MESSAGE = "Application successfully submitted.";

    @Autowired
    private UserApplicationService userApplicationService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private MembershipService membershipService;

    @RequestMapping(value = "/apply", method = RequestMethod.GET)
    public View redirectToLoginPage() {
        return new RedirectView("/login");
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    public ModelAndView applyForUserAccount(@RequestParam("application_name") String name,
                                            @RequestParam("application_email") String email) {
        UserApplicationResponse response = new UserApplicationResponse();

        if (!StringUtils.hasText(name) || !StringUtils.hasText(email)) {
            response.setHeader("ERROR");
            response.setMessage("Name and email required.");
        } else if (isEmailUsedAlready(email)) {
            response.setHeader("ERROR");
            response.setMessage(DUPLICATE_EMAIL_ERROR_MESSAGE);
        } else {
            try {
                userApplicationService.createApplication(name, email);
                response.setHeader("SUCCESS");
                response.setMessage(SUCCESS_MESSAGE);
            } catch (Exception e) {
                LOGGER.error("", e);
                response.setHeader("ERROR");
                response.setMessage(UNEXPECTED_ERROR_MESSAGE);
            }
        }

        return new ModelAndView(ViewNames.APPLICATION_CONCLUSION, "data", response);
    }

    @ResponseBody
    @RequestMapping(value = "/admin/r/users/applications", method = RequestMethod.GET)
    public Collection<UserApplication> getUserApplications() {
        return userApplicationService.getAll();
    }

    @RequestMapping(value = "/admin/r/users/applications/{id}", method = RequestMethod.POST)
    public void approve(@PathVariable("id") int id, HttpServletResponse response) {
        boolean isSuccessful = true;
        UserApplication userApplication = userApplicationService.get(id);

        try {
            Group defaultGroup = groupService.getDefault();
            User user = userService.create(userApplication);
            membershipService.add(user, defaultGroup);

            userApplicationService.remove(userApplication);
        } catch (Exception e) {
            isSuccessful = false;
        }


        int responseCode = isSuccessful ? HttpServletResponse.SC_CREATED : HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        response.setStatus(responseCode);
    }

    @RequestMapping(value = "/admin/r/users/applications/{id}", method = RequestMethod.DELETE)
    public void deny(@PathVariable("id") int id, HttpServletResponse response) {
        boolean isSuccessful = true;
        UserApplication userApplication = userApplicationService.get(id);

        try {
            userApplicationService.remove(userApplication);
        } catch (Exception e) {
            isSuccessful = false;
        }

        int responseCode = isSuccessful ? HttpServletResponse.SC_NO_CONTENT : HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        response.setStatus(responseCode);
    }

    private boolean isEmailUsedAlready(String email) {
        return userApplicationService.hasApplication(email) || userService.hasUser(email);
    }
}
