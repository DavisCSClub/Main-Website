package org.dcsc.admin.controllers;

import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/admin/super/users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SuperAdminUserController {
    @Autowired
    private DcscUserService dcscUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String users(Model model) {
        List<DcscUser> users = dcscUserService.getAllUsers();

        model.addAttribute("users", users);

        return "admin/super/user/user-list";
    }
}
