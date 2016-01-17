package org.dcsc.admin.controllers.tutoring;

import org.dcsc.core.course.AcademicCourseService;
import org.dcsc.core.time.AcademicTerm;
import org.dcsc.core.time.AcademicTermService;
import org.dcsc.core.tutor.Tutor;
import org.dcsc.core.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminTutoringEditController {
    @Autowired
    private TutorService tutorService;
    @Autowired
    private AcademicTermService academicTermService;
    @Autowired
    private AcademicCourseService academicCourseService;

    @RequestMapping(value = "/admin/tutoring/tutor/edit", method = RequestMethod.GET)
    @PreAuthorize("hasGroup('Tutoring Committee')")
    public String getTutorEditPage(Authentication authentication, Model model) {
        try {
            AcademicTerm academicTerm = academicTermService.getCurrentTerm();
            Tutor tutor = tutorService.getTutor(authentication);

            model.addAttribute("currentTerm", academicTerm.toString());
            model.addAttribute("allCourses", academicCourseService.getAll());
            model.addAttribute("tutoredCourses", tutor.getCurrentTermCourses());
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/admin";
        }

        return "admin/tutoring-edit";
    }
}