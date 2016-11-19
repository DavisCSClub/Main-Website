package org.dcsc.core.authentication.membership;

import org.dcsc.core.authentication.group.Group;
import org.dcsc.core.authentication.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class MembershipService {
    @Autowired
    private MembershipRepository repository;

    @Transactional(readOnly = true)
    public List<Membership> getByUser(User user) {
        return repository.getByUser(user);
    }

    @Transactional(readOnly = true)
    public List<Membership> getByGroupAndAcademicYear(int groupId, int startYear) {
        ZonedDateTime startDate = ZonedDateTime.of(startYear, 6, 12, 0, 0, 0, 0, ZoneId.of("America/Los_Angeles"));
        ZonedDateTime endDate = startDate.plusYears(1);

        return repository.getByGroupBetweenDates(groupId, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<Integer> getMembershipYears(int groupId) {
        return repository.getMembershipYears(groupId);
    }

    @Transactional
    public Membership add(User user, Group group) {
        ZonedDateTime start = ZonedDateTime.now();
        ZonedDateTime end = start.plusYears(1);

        Membership membership = new Membership();
        membership.setUser(user);
        membership.setGroup(group);
        membership.setStartDate(start);
        membership.setEndDate(end);
        membership.setLeader(false);
        membership.setTitle("Member");

        return repository.save(membership);
    }
}
