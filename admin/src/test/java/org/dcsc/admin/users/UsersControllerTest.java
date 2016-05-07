package org.dcsc.admin.users;

import org.dcsc.admin.users.UsersController;
import org.dcsc.admin.users.UserFormBinder;
import org.dcsc.admin.users.UserResourceAssembler;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.details.DcscUserDetails;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsersControllerTest {
    private static final int ID = 1;

    @Mock
    private DcscUserService userService;
    @Mock
    private UserFormBinder formBinder;
    @Mock
    private UserResourceAssembler resourceAssembler;

    @InjectMocks
    private UsersController controller;

    @Mock
    private PagedResourcesAssembler pagedResourcesAssembler;
    @Mock
    private Pageable pageable;
    @Mock
    private Page<DcscUser> pagedUsers;
    @Mock
    private PagedResources<DcscUser> pagedResources;
    @Mock
    private Authentication authentication;
    @Mock
    private HttpServletResponse response;
    @Mock
    private DcscUserDetails dcscUserDetails;
    @Mock
    private DcscUser dcscUser;
    @Mock
    private Resource resource;
    @Mock
    private Map<String, Object> payload;

    @Test
    public void getUsers() {
        when(userService.getUsers(pageable)).thenReturn(pagedUsers);
        when(pagedResourcesAssembler.toResource(pagedUsers, resourceAssembler)).thenReturn(pagedResources);

        assertEquals(pagedResources, controller.getUsers(pageable, pagedResourcesAssembler));
    }

    @Test
    public void getUser() throws Exception {
        when(userService.getUserById(ID)).thenReturn(Optional.ofNullable(dcscUser));
        when(resourceAssembler.toResource(dcscUser)).thenReturn(resource);

        assertEquals(resource, controller.getUser(ID));
    }

    @Test
    public void updateUser() throws Exception {
        when(userService.getUserById(ID)).thenReturn(Optional.ofNullable(dcscUser));

        controller.updateUser(ID, payload);

        verify(formBinder).bindToUser(dcscUser, payload);
        verify(userService).save(dcscUser);
    }
}