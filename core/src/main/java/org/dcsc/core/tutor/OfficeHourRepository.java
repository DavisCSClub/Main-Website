package org.dcsc.core.tutor;

import org.dcsc.core.time.AcademicTerm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfficeHourRepository extends JpaRepository<OfficeHour, Long> {
    List<OfficeHour> findByTutorAndAcademicTerm(Tutor tutor, AcademicTerm academicTerm);
}
