package org.dcsc.core.authentication.membership;

import org.dcsc.core.authentication.group.Group;
import org.dcsc.core.authentication.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Membership> getByGroupAndYear(int groupId, int year) {
        return repository.getByGroupAndYear(groupId, year);
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
