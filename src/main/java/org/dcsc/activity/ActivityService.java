package org.dcsc.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tktong on 7/19/15.
 */
@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities(long id) {
        return activityRepository.findAllActivityByUserId(id);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }
}
