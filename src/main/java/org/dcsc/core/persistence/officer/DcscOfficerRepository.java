package org.dcsc.core.persistence.officer;

import org.dcsc.core.model.officer.DcscOfficer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tktong on 8/3/2015.
 */
@Repository
public interface DcscOfficerRepository extends JpaRepository<DcscOfficer, Long> {
    List<DcscOfficer> findDcscOfficersByYear(int year);

    @Query("SELECT DISTINCT (year) FROM DcscOfficer ORDER BY year ASC")
    List<Integer> findDistinctYears();
}
