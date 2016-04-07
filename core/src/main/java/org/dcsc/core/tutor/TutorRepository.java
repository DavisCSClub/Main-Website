package org.dcsc.core.tutor;

import org.dcsc.core.user.DcscUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Tutor findByDcscId(Long id);
}
