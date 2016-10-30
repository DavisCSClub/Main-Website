package org.dcsc.core.authentication.user;


import org.dcsc.core.authentication.authorities.Authority;
import org.dcsc.core.authentication.group.Group;
import org.dcsc.core.authentication.membership.Membership;
import org.dcsc.core.authentication.membership.MembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserDetailsFactory {
    @Autowired
    private MembershipService membershipService;

    public UserDetails create(User user) {
        Collection<GrantedAuthority> grantedAuthorities = getAuthorities(user);
        int id = user.getId();
        String name = user.getName();
        String email = user.getEmail();

        return new UserDetails(grantedAuthorities, id, name, email);
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        List<Membership> memberships = membershipService.getByUser(user);
        for (Membership membership : memberships) {
            Group group = membership.getGroup();
            List<Authority> groupAuthorities = group.getAuthorities();

            for (Authority groupAuthority : groupAuthorities) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(groupAuthority.getScope());
                authorities.add(authority);
            }
        }

        return authorities;
    }
}
