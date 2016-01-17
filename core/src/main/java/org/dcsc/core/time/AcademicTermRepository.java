package org.dcsc.core.time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicTermRepository extends JpaRepository<AcademicTerm, Long> {
    AcademicTerm findByCode(String code);

    List<AcademicTerm> findByYear(int year);

    @Query("SELECT a FROM AcademicTerm a WHERE a.startDate <= :date AND :date <= a.endDate")
    Optional<AcademicTerm> findByDate(@Param("date") LocalDate date);
}
