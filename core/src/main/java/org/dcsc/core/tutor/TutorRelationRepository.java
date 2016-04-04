package org.dcsc.core.tutor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TutorRelationRepository extends JpaRepository<TutorRelation, TutorRelationId> {
    @Query("SELECT relation FROM TutorRelation relation WHERE relation.tutorId=:tutorId AND relation.termId=:termId")
    Set<TutorRelation> findByTutorIdAndAcademicTermId(@Param("tutorId") long tutorId, @Param("termId") long academicTermId);
}
