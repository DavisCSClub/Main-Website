package org.dcsc.admin.controllers;

import org.dcsc.admin.view.model.courses.AcademicCourseResourceAssembler;
import org.dcsc.core.course.AcademicCourse;
import org.dcsc.core.course.AcademicCourseService;
import org.dcsc.core.tutor.Tutor;
import org.dcsc.core.tutor.TutorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AcademicCoursesControllerTest {
    private static final int ID = 0;

    @Mock
    private AcademicCourseService academicCourseService;
    @Mock
    private TutorService tutorService;
    @Mock
    private AcademicCourseResourceAssembler resourceAssembler;
    @Mock
    private Authentication authentication;
    @Mock
    private Tutor tutor;

    @InjectMocks
    private AcademicCoursesController controller;

    @Mock
    private List<AcademicCourse> courses;
    @Mock
    private AcademicCourse course;
    @Mock
    private List<Resource> resources;
    @Mock
    private Resource resource;
    @Mock
    private Collection<Long> courseIds;

    @Test
    public void getCourses() throws Exception {
        when(academicCourseService.getAll()).thenReturn(courses);
        when(resourceAssembler.toResources(courses)).thenReturn(resources);

        assertEquals(resources, controller.getCourses());
    }

    @Test
    public void getCourseById() {
        when(academicCourseService.get(ID)).thenReturn(course);
        when(resourceAssembler.toResource(course)).thenReturn(resource);

        assertEquals(resource, controller.getCourse(ID));
    }

    @Test
    public void getTutoredCourses() {
        when(tutorService.getTutor(authentication)).thenReturn(tutor);
        when(tutor.getCurrentTermCourses()).thenReturn(courses);
        when(resourceAssembler.toResources(courses)).thenReturn(resources);

        assertEquals(resources, controller.getTutoredCourses(authentication));
    }

    @Test
    public void updatedTutoredCourses() throws Exception {
        when(tutorService.getTutor(authentication)).thenReturn(tutor);
        when(academicCourseService.get(courseIds)).thenReturn(courses);

        controller.updateTutoredCourses(authentication, courseIds);

        verify(tutorService).updateCoursesTutored(tutor, courses);
    }
}