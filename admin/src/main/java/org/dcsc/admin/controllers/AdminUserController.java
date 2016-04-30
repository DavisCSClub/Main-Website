package org.dcsc.admin.controllers;

import org.dcsc.admin.constants.AttributeNames;
import org.dcsc.admin.constants.ViewNames;
import org.dcsc.admin.profile.ProfileCreateForm;
import org.dcsc.admin.user.AdminUserService;
import org.dcsc.core.user.role.DcscRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Deprecated
@Controller
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private DcscRoleService roleService;

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('user','create')")
    public String userCreatePage(Model model) {
        model.addAttribute(AttributeNames.FORM, new ProfileCreateForm());
        model.addAttribute(AttributeNames.ROLES, roleService.getAllRoles());

        return ViewNames.ADMIN_USER_CREATE;
    }

    @RequestMapping(value = "/admin/user/{user_id}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('user','update')")
    public String userEditPage(@PathVariable("user_id") long id, Model model) {
        model.addAttribute("userId", id);
        model.addAttribute(AttributeNames.FORM, adminUserService.getAccountForm(id));
        model.addAttribute(AttributeNames.ROLES, roleService.getAllRoles());

        return "admin/user-edit";
    }
}
