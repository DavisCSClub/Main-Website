package org.dcsc.admin.view.model.courses;

import org.dcsc.admin.controllers.AcademicCoursesController;
import org.dcsc.core.course.AcademicCourse;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class AcademicCourseResourceAssembler extends ResourceAssemblerSupport<AcademicCourse, Resource> {
    public AcademicCourseResourceAssembler() {
        super(AcademicCoursesController.class, Resource.class);
    }

    @Override
    public List<Resource> toResources(Iterable<? extends AcademicCourse> courses) {
        List<Resource> resources = new ArrayList<>();
        courses.forEach(course -> resources.add(toResource(course)));
        return resources;
    }

    @Override
    public Resource toResource(AcademicCourse course) {
        return new Resource(course, getRestEndpoint(course));
    }

    private Link getRestEndpoint(AcademicCourse course) {
        return linkTo(AcademicCoursesController.class).slash(course.getId()).withSelfRel();
    }
}
