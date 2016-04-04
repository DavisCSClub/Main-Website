package org.dcsc.admin.controllers.tutoring;

import org.dcsc.admin.constants.AttributeNames;
import org.dcsc.core.tutor.TutorService;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

// @Controller
public class AdminTutoringDirectoryController {
    @Autowired
    private TutorService tutorService;
    @Autowired
    private DcscUserService dcscUserService;

    @RequestMapping(value = "/admin/tutoring/directory", method = RequestMethod.GET)
    @PreAuthorize("hasGroup('Tutoring Committee')")
    public String tutoringDirectory(Model model) {
        List<DcscUser> allTutors = tutorService.getAllTutors().stream()
                .map(tutor -> tutor.getDcscUser()).collect(Collectors.toList());

        model.addAttribute(AttributeNames.TUTORS, allTutors);
        model.addAttribute(AttributeNames.USERS, dcscUserService.getAllUsers());

        return "admin/tutoring-directory";
    }
}
