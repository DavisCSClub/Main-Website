package org.dcsc.activity;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.userdetails.DcscUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
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

    public void save(String target, String description, Actions action) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DcscUserDetails dcscUserDetails = (DcscUserDetails) authentication.getPrincipal();
        DcscUser user = dcscUserDetails.getUser();

        Calendar calendar = Calendar.getInstance();
        Date date = new Date(calendar.getTime().getTime());
        Time time = new Time(calendar.getTime().getTime());

        Activity activity = new Activity();

        activity.setUser(user);
        activity.setTarget(target);
        activity.setDescription(description);
        activity.setAction(action);
        activity.setDate(date);
        activity.setTime(time);

        save(activity);
    }

    public void save(Activity activity) {
        activityRepository.save(activity);
    }
}
