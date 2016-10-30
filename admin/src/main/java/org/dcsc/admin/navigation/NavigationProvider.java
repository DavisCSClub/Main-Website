package org.dcsc.admin.navigation;

import com.google.common.collect.ImmutableList;
import org.dcsc.core.authentication.authorities.Authorities;
import org.dcsc.core.authentication.user.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class NavigationProvider {
    private static final NavigationLink DASHBOARD = new NavigationLink("Dashboard", "restricted.dashboard", "&#xE871;");

    private static final NavigationLink USER_APPLICATIONS = new NavigationLink("Applications",
                                                                               "restricted.userApplications",
                                                                               "&#xE7FB;");
    private static final NavigationLink USERS = new NavigationLink("Users", "&#xE7FB;",
                                                                   ImmutableList.of(USER_APPLICATIONS));

    public static final NavigationLink TUTORING_EDIT = new NavigationLink("Edit Courses", "restricted.tutoringCourses",
                                                                          "&#xE7FB;");
    public static final NavigationLink TUTORING_CALENDAR = new NavigationLink("Office Hour",
                                                                              "restricted.tutoringCalendar",
                                                                              "&#xE7FB;");
    public static final NavigationLink TUTORING_MAIN = new NavigationLink("Find Tutee", "findTutee", "&#xE7FB;");
    public static final NavigationLink TUTORING = new NavigationLink("Tutoring", "&#xE54B;",
                                                                     ImmutableList.of(TUTORING_MAIN, TUTORING_CALENDAR,
                                                                                      TUTORING_EDIT));

    private static final Map<String, NavigationLink> NAVIGATIONS = new HashMap<>();

    @PostConstruct
    private void initialize() {
        NAVIGATIONS.put(Authorities.USERS, USERS);
        //NAVIGATIONS.put(Authorities.TUTORING, TUTORING);
    }

    public Collection<NavigationLink> getNavigation(UserDetails userDetails) {
        Collection<GrantedAuthority> authorities = userDetails.getAuthorities();

        Collection<NavigationLink> navigationLinks = new ArrayList<>();
        addDefaultLinks(navigationLinks);
        addAuthorityBasedLinks(navigationLinks, authorities);

        return navigationLinks;
    }

    private void addDefaultLinks(Collection<NavigationLink> navigationLinks) {
        navigationLinks.add(DASHBOARD);
    }

    private void addAuthorityBasedLinks(Collection<NavigationLink> navigationLinks,
                                        Collection<GrantedAuthority> authorities) {
        for (GrantedAuthority authority : authorities) {
            NavigationLink navigationLink = NAVIGATIONS.get(authority.getAuthority());

            if (navigationLink != null) {
                navigationLinks.add(navigationLink);
            }
        }
    }
}
