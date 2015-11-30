package org.dcsc.core.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findEventById(Long id);

    List<Event> findEventsByDate(Date date);

    @Query("SELECT e FROM Event e WHERE :startDate <= e.date AND e.date <= :endDate")
    Set<Event> findEventsByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
