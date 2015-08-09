package org.dcsc.activity;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.details.DcscUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

/**
 * Created by tktong on 7/19/15.
 *
 * Service connecting to the activity log repository. Queries should never directly access {@link #activityRepository}.
 */
@Service
public class ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    /**
     * Returns all activities performed by a single user.
     *
     * @param id    The user id to look for.
     * @return      {@link List} of {@link Activity}
     */
    @Transactional(readOnly = true)
    public List<Activity> getAllActivities(long id) {
        return activityRepository.findAllActivityByUserId(id);
    }

    /**
     * Returns all activities performed by all users.
     *
     * @return  {@link List} of {@link Activity} performed by all users.
     */
    @Transactional(readOnly = true)
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    /**
     * Records a new activity with the current user and current time into the {@link #activityRepository}
     *
     * @param target        Target of the action
     * @param description   Description of the action performed
     * @param action        {@link Action} - (CRUD)
     */
    public void save(String target, String description, Action action) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DcscUserDetails dcscUserDetails = (DcscUserDetails) authentication.getPrincipal();
        DcscUser user = dcscUserDetails.getUser();

        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTime().getTime());
        Time time = new Time(calendar.getTime().getTime());

        save(new Activity(user, action, target, description, date, time));
    }

    /**
     * Saves an activity entity
     *
     * @param activity
     */
    public void save(Activity activity) {
        activityRepository.save(activity);
    }
}
