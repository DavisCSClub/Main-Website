package org.dcsc.core.tutor;

import org.dcsc.core.course.AcademicCourse;
import org.dcsc.core.time.AcademicTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRelationRepository extends JpaRepository<TutorRelation, TutorRelationId> {
    void deleteByTutorAndAcademicTerm(Tutor tutor, AcademicTerm academicTerm);

    void deleteByTutorAndAcademicCourseAndAcademicTerm(Tutor tutor, AcademicCourse academicCourse, AcademicTerm academicTerm);
}
