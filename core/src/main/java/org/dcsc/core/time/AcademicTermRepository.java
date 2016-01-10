package org.dcsc.core.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcademicTermRepository extends JpaRepository<AcademicTerm, Long> {
    AcademicTerm findByCode(String code);

    List<AcademicTerm> findByYear(int year);
}
