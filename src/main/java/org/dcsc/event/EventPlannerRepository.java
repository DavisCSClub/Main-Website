package org.dcsc.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tktong on 7/20/2015.
 */
@Repository
public interface EventPlannerRepository extends JpaRepository<EventPlanner, Long> {
}
