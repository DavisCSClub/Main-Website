package org.dcsc.admin.navigation;

import org.dcsc.admin.navigation.NavigationController;
import org.dcsc.admin.navigation.NavigationLink;
import org.dcsc.admin.navigation.NavigationProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NavigationControllerTest {
    @Mock
    private NavigationProvider navigationProvider;

    @InjectMocks
    private NavigationController navigationController;

    @Mock
    private Authentication authentication;
    @Mock
    private List<NavigationLink> navigationLinks;

    @Test
    public void getLinks() {
        when(navigationProvider.getNavigation(authentication)).thenReturn(navigationLinks);
        assertEquals(navigationLinks, navigationController.getNavigation(authentication));
    }
}