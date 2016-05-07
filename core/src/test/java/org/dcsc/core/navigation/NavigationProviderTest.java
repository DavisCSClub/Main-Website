package org.dcsc.core.navigation;

import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.details.DcscUserDetails;
import org.dcsc.core.user.group.UserGroup;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NavigationProviderTest {
    @InjectMocks
    private NavigationProvider navigationProvider;

    @Mock
    private Authentication authentication;
    @Mock
    private DcscUserDetails userDetails;
    @Mock
    private Map<String, Integer> permissions;
    @Mock
    private DcscUser user;
    @Mock
    private List<UserGroup> groups;
    @Mock
    private Stream groupStream;

    @Before
    public void before() {
        navigationProvider.initialize();
    }

    @Test
    public void userOnlyNavigation() {
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getPermissions()).thenReturn(permissions);
        when(userDetails.getUser()).thenReturn(user);
        when(user.getUserGroups()).thenReturn(groups);
        when(groups.stream()).thenReturn(groupStream);
        when(groupStream.map(any())).thenReturn(groupStream);
        when(groupStream.anyMatch(any())).thenReturn(false);

        Collection<NavigationLink> links = navigationProvider.getNavigation(authentication);

        assertEquals(2, links.size());
        assertTrue(links.contains(NavigationProvider.DASHBOARD));
        assertTrue(links.contains(NavigationProvider.DIRECTORY));
    }

    @Test
    public void officerNavigation() {
        Map<String, Integer> officerPermissions = new HashMap<>();
        officerPermissions.put("CAROUSEL", 15);
        officerPermissions.put("EVENT", 15);

        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getPermissions()).thenReturn(officerPermissions);
        when(userDetails.getUser()).thenReturn(user);
        when(user.getUserGroups()).thenReturn(groups);
        when(groups.stream()).thenReturn(groupStream);
        when(groupStream.map(any())).thenReturn(groupStream);
        when(groupStream.anyMatch(any())).thenReturn(false);

        Collection<NavigationLink> links = navigationProvider.getNavigation(authentication);

        assertEquals(4, links.size());
        assertTrue(links.contains(NavigationProvider.DASHBOARD));
        assertTrue(links.contains(NavigationProvider.DIRECTORY));
        assertTrue(links.contains(NavigationProvider.CAROUSEL));
        assertTrue(links.contains(NavigationProvider.EVENT));
    }

    @Test
    public void tutorNavigation() {
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getPermissions()).thenReturn(permissions);
        when(userDetails.getUser()).thenReturn(user);
        when(user.getUserGroups()).thenReturn(groups);
        when(groups.stream()).thenReturn(groupStream);
        when(groupStream.map(any())).thenReturn(groupStream);
        when(groupStream.anyMatch(any())).thenReturn(true);

        Collection<NavigationLink> links = navigationProvider.getNavigation(authentication);

        assertEquals(3, links.size());
        assertTrue(links.contains(NavigationProvider.DASHBOARD));
        assertTrue(links.contains(NavigationProvider.DIRECTORY));
        assertTrue(links.contains(NavigationProvider.TUTORING));
    }

    @Test
    public void tutoringOfficerNavigation() {
        Map<String, Integer> officerPermissions = new HashMap<>();
        officerPermissions.put("CAROUSEL", 15);
        officerPermissions.put("EVENT", 15);

        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getPermissions()).thenReturn(officerPermissions);
        when(userDetails.getUser()).thenReturn(user);
        when(user.getUserGroups()).thenReturn(groups);
        when(groups.stream()).thenReturn(groupStream);
        when(groupStream.map(any())).thenReturn(groupStream);
        when(groupStream.anyMatch(any())).thenReturn(true);

        Collection<NavigationLink> links = navigationProvider.getNavigation(authentication);

        assertEquals(5, links.size());
        assertTrue(links.contains(NavigationProvider.DASHBOARD));
        assertTrue(links.contains(NavigationProvider.DIRECTORY));
        assertTrue(links.contains(NavigationProvider.CAROUSEL));
        assertTrue(links.contains(NavigationProvider.EVENT));
        assertTrue(links.contains(NavigationProvider.TUTORING));
    }
}