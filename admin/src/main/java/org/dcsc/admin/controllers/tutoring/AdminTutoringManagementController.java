package org.dcsc.admin.controllers.tutoring;

import org.dcsc.admin.constants.AttributeNames;
import org.dcsc.core.tutor.TutorService;
import org.dcsc.core.user.DcscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminTutoringManagementController {
    @Autowired
    private TutorService tutorService;
    @Autowired
    private DcscUserService dcscUserService;

    @RequestMapping("/admin/tutoring/management")
    @PreAuthorize("hasGroup('Tutoring Committee',true)")
    public String tutorManagementPage(Model model) {
        model.addAttribute(AttributeNames.USERS, dcscUserService.getAllUsers());
        return "admin/tutoring-management";
    }
}
