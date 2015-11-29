package org.dcsc.core.activity;

import org.dcsc.core.activity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tktong on 7/19/15.
 */
@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllActivityByUserId(long id);
}
