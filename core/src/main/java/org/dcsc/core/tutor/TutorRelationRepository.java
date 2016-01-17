package org.dcsc.core.tutor;

import org.dcsc.core.course.AcademicCourse;
import org.dcsc.core.time.AcademicTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TutorRelationRepository extends JpaRepository<TutorRelation, TutorRelationId> {
    Set<TutorRelation> findByTutorAndAcademicTerm(Tutor tutor, AcademicTerm academicTerm);

    void deleteByTutorAndAcademicTerm(Tutor tutor, AcademicTerm academicTerm);

    void deleteByTutorAndAcademicCourseAndAcademicTerm(Tutor tutor, AcademicCourse academicCourse, AcademicTerm academicTerm);
}
