package org.dcsc.admin.controllers;

import org.dcsc.core.navigation.NavigationBar;
import org.dcsc.core.navigation.NavigationBarFactory;
import org.dcsc.core.navigation.NavigationLink;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.details.DcscUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NavigationControllerTest {
    private static final String NON_ANGULAR_LINK = "someLink";
    private static final String ANGULAR_LINK = "/admin/";

    @Mock
    private NavigationBarFactory navigationBarFactory;

    @InjectMocks
    private NavigationController navigationController;

    @Mock
    private Authentication authentication;
    @Mock
    private DcscUserDetails userDetails;
    @Mock
    private DcscUser user;
    @Mock
    private Map<String, Integer> permissions;
    @Mock
    private NavigationBar navigationBar;
    @Mock
    private List<NavigationLink> navigationLinks;
    @Mock
    private List<NavigationLink> nestedLinks;
    @Mock
    private Iterator<NavigationLink> iterator;
    @Mock
    private Iterator<NavigationLink> nestedIterator;
    @Mock
    private NavigationLink navigationLink;
    @Mock
    private NavigationLink nestedLink;

    @Before
    public void before() {
        navigationController.initialize();
    }

    @Test
    public void nonAngularLink() {
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUser()).thenReturn(user);
        when(userDetails.getPermissions()).thenReturn(permissions);
        when(navigationBarFactory.getNavigationBar(user, permissions)).thenReturn(navigationBar);
        when(navigationBar.getNavigationLinks()).thenReturn(navigationLinks);
        when(navigationLinks.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(navigationLink);
        when(navigationLink.getLink()).thenReturn(NON_ANGULAR_LINK);

        navigationController.getNavigation(authentication);

        verify(navigationLink, never()).setLink(anyString());
    }

    @Test
    public void angularLink() {
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUser()).thenReturn(user);
        when(userDetails.getPermissions()).thenReturn(permissions);
        when(navigationBarFactory.getNavigationBar(user, permissions)).thenReturn(navigationBar);
        when(navigationBar.getNavigationLinks()).thenReturn(navigationLinks);
        when(navigationLinks.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(navigationLink);
        when(navigationLink.getLink()).thenReturn(ANGULAR_LINK);

        navigationController.getNavigation(authentication);

        verify(navigationLink).setLink("restricted.dashboard");
    }

    @Test
    public void nestedLink() {
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUser()).thenReturn(user);
        when(userDetails.getPermissions()).thenReturn(permissions);
        when(navigationBarFactory.getNavigationBar(user, permissions)).thenReturn(navigationBar);
        when(navigationBar.getNavigationLinks()).thenReturn(navigationLinks);
        when(navigationLinks.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(navigationLink);
        when(navigationLink.getSubmenu()).thenReturn(nestedLinks);
        when(nestedLinks.iterator()).thenReturn(nestedIterator);
        when(nestedIterator.hasNext()).thenReturn(true, false);
        when(nestedIterator.next()).thenReturn(nestedLink);
        when(nestedLink.getLink()).thenReturn(ANGULAR_LINK);

        navigationController.getNavigation(authentication);

        verify(nestedLink).setLink("restricted.dashboard");
    }
}