package org.dcsc.athena.objects;

import org.dcsc.athena.objects.TutoringSession;
import org.dcsc.core.user.DcscUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutoringSessionRepository extends JpaRepository<TutoringSession, Long> {
    TutoringSession findTutoringSessionById(Long id);
}