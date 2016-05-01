package org.dcsc.core.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AcademicCourseService {
    @Autowired
    private AcademicCourseRepository academicCourseRepository;

    public AcademicCourse get(String shortName) {
        return academicCourseRepository.findByCode(shortName).get();
    }

    public AcademicCourse get(int id) {
        return academicCourseRepository.findOne((long) id);
    }

    public Collection<AcademicCourse> get(Collection<Long> ids) {
        return academicCourseRepository.findAll(ids);
    }

    public List<AcademicCourse> getAll() {
        return academicCourseRepository.findAll();
    }
}
