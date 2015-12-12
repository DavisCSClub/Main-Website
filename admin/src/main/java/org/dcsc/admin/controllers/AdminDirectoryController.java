package org.dcsc.admin.controllers;

import org.dcsc.admin.constants.AttributeNames;
import org.dcsc.admin.constants.ViewNames;
import org.dcsc.admin.profile.ProfileCreateForm;
import org.dcsc.admin.user.AdminUserService;
import org.dcsc.core.activity.ActivityService;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.permission.RolePermissionService;
import org.dcsc.core.user.role.DcscRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminDirectoryController {
    @Autowired
    private ActivityService activityService;
    @Autowired
    private DcscUserService userService;
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private DcscRoleService roleService;

    @RequestMapping("/admin/directory")
    public String directoryPage(Model model) {
        model.addAttribute(AttributeNames.USERS, userService.getAllUsers());

        return ViewNames.ADMIN_DIRECTORY;
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.GET)
    @PreAuthorize("hasPermission('user','create')")
    public String userCreatePage(Model model) {
        model.addAttribute("form", new ProfileCreateForm());
        model.addAttribute("roles", roleService.getAllRoles());

        return ViewNames.ADMIN_USER_CREATE;
    }

    @RequestMapping(value = "/admin/user/{user_id}/edit", method = RequestMethod.GET)
    public String userEditPage(@PathVariable("user_id") long id, Model model) {
        model.addAttribute("form", adminUserService.getAccountForm(id));
        model.addAttribute("roles", roleService.getAllRoles());

        return "admin/user-edit";
    }
}
