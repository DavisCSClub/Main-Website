package org.dcsc.admin.controllers.tutoring;

import org.dcsc.admin.dto.RestTransactionResult;
import org.dcsc.admin.dto.TutorCourseUpdateForm;
import org.dcsc.core.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminTutoringEditRestController {
    @Autowired
    private TutorService tutorService;

    @CrossOrigin(origins = {"https://daviscsclub.org", "http://localhost:8080"}, methods = {RequestMethod.PUT})
    @RequestMapping(value = "/admin/r/tutoring/tutor/edit", method = RequestMethod.PUT)
    public RestTransactionResult updateTutoredCourses(Authentication authentication, @RequestBody TutorCourseUpdateForm tutorCourseUpdateForm) {
        RestTransactionResult result;

        List<String> courses = tutorCourseUpdateForm.getCourses();
        if (courses != null) {
            try {
                tutorService.updateCoursesTutored(authentication, courses);
                result = RestTransactionResult.success("Tutored courses successfully updated.");
            } catch (Exception e) {
                e.printStackTrace();
                result = RestTransactionResult.fail("Failed to update tutored courses.");
            }
        } else {
            result = RestTransactionResult.fail("Null courses in update request.");
        }

        return result;
    }
}
