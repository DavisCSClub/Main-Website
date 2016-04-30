package org.dcsc.admin.controllers;

import org.dcsc.admin.view.model.courses.AcademicCourseResourceAssembler;
import org.dcsc.core.course.AcademicCourse;
import org.dcsc.core.course.AcademicCourseService;
import org.dcsc.core.tutor.Tutor;
import org.dcsc.core.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RequestMapping("/admin/r/courses")
@RestController("adminCoursesController")
public class AcademicCoursesController {
    @Autowired
    private AcademicCourseService academicCourseService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private AcademicCourseResourceAssembler resourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Resource> getCourses() {
        return resourceAssembler.toResources(academicCourseService.getAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<AcademicCourse> getCourse(@PathVariable("id") int id) {
        return resourceAssembler.toResource(academicCourseService.get(id));
    }

    @RequestMapping(value = "/tutor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Resource> getTutoredCourses(Authentication authentication) {
        Tutor tutor = tutorService.getTutor(authentication);
        return resourceAssembler.toResources(tutor.getCurrentTermCourses());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/tutor", method = RequestMethod.PUT)
    public void updateTutoredCourses(Authentication authentication, @RequestBody Collection<Long> courseIds) throws Exception {
        Tutor tutor = tutorService.getTutor(authentication);
        Collection<AcademicCourse> courses = academicCourseService.get(courseIds);
        tutorService.updateCoursesTutored(tutor, (List) courses);
    }
}
