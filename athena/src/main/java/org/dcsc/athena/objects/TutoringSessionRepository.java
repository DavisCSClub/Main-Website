package org.dcsc.athena.objects;

import org.dcsc.athena.objects.TutoringSession;
import org.dcsc.core.user.DcscUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.time.LocalDateTime;

@Repository
public interface TutoringSessionRepository extends JpaRepository<TutoringSession, Long> {
    TutoringSession findTutoringSessionById(Long id);

    @Query("SELECT t FROM TutoringSession t WHERE t.endDateTime is NULL AND t.tutorId = :id")
    List<TutoringSession> findOpenTutoringSessionByTutorId(@Param("id") Long id);

    // @Query("SELECT t FROM TutoringSession t WHERE t.endDateTime is NULL AND t.tutorId = :id")
    // List<TutoringSession> findAllHoursByUserInInterval(@Param("id") Long id, @Param("startDateTime") LocalDateTime lower, @Param("endDateTime") LocalDateTime upper);

}