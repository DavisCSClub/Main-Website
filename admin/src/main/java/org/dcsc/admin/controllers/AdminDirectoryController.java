package org.dcsc.admin.controllers;

import org.dcsc.admin.constants.ViewNames;
import org.dcsc.core.user.DcscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminDirectoryController {
    @Autowired
    private DcscUserService userService;

    @RequestMapping("/admin/directory")
    public String directoryPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        
        return ViewNames.ADMIN_DIRECTORY;
    }
}
