package org.dcsc.admin;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.DcscUserService;
import org.dcsc.security.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * Created by tktong on 8/4/2015.
 */
@Controller
@RequestMapping(value = "/admin/super/users")
public class SuperAdminUserController {
    @Autowired
    private DcscUserService dcscUserService;

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String users(Model model) {
        List<DcscUser> users = dcscUserService.getAllUsers();

        model.addAttribute("users", users);

        return "admin/super/user-list";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(AccessDeniedException e, HttpServletRequest request) {
        return "redirect:/admin";
    }
}
