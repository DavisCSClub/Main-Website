package org.dcsc.core.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AcademicCourseRepository extends JpaRepository<AcademicCourse, Long> {
    Optional<AcademicCourse> findByCode(String code);
}
