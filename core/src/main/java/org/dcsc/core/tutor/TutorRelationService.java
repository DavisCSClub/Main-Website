package org.dcsc.core.tutor;

import org.dcsc.core.course.AcademicCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class TutorRelationService {
    @Autowired
    private TutorRelationRepository tutorRelationRepository;
    @Autowired
    private AcademicCourseRepository academicCourseRepository;

    @Transactional(readOnly = true)
    public Collection<TutorRelation> get(long tutorId, long termId) {
        Collection<TutorRelation> tutorRelations = tutorRelationRepository.findByTutorIdAndAcademicTermId(tutorId, termId);

        for (TutorRelation relation : tutorRelations) {
            long courseId = relation.getCourseId();
            relation.setAcademicCourse(academicCourseRepository.findOne(courseId));
        }

        return tutorRelations;
    }
}
