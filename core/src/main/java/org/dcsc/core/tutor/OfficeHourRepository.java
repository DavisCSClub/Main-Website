package org.dcsc.core.tutor;

import org.dcsc.core.time.AcademicTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OfficeHourRepository extends JpaRepository<OfficeHour, Long> {
    List<OfficeHour> findByTutorAndAcademicTerm(Tutor tutor, AcademicTerm academicTerm);

    @Query("SELECT oh FROM OfficeHour oh WHERE oh.childOfficeHour.id = :childId")
    Optional<OfficeHour> findParentOfficeHour(@Param("childId") long childId);
}
