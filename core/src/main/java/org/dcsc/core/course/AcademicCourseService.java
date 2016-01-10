package org.dcsc.core.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcademicCourseService {
    @Autowired
    private AcademicCourseRepository academicCourseRepository;

    public AcademicCourse get(String shortName) {
        return academicCourseRepository.findByCode(shortName).get();
    }

    public List<AcademicCourse> getAll() {
        return academicCourseRepository.findAll();
    }
}
