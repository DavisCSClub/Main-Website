package org.dcsc.admin.controllers;

import org.dcsc.admin.view.model.user.UserFormBinder;
import org.dcsc.admin.view.model.user.UserResourceAssembler;
import org.dcsc.admin.view.model.user.UserViewModel;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.details.DcscUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RequestMapping("/admin/r/users")
@RestController("adminUsersController")
public class UsersController {
    @Autowired
    private DcscUserService userService;
    @Autowired
    private UserFormBinder formBinder;
    @Autowired
    private UserResourceAssembler resourceAssembler;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResources<UserViewModel> getUsers(Pageable pageable, PagedResourcesAssembler assembler) {
        return assembler.toResource(userService.getUsers(pageable), resourceAssembler);
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getMe(Authentication authentication, HttpServletResponse response) throws IOException {
        DcscUser user = ((DcscUserDetails) authentication.getPrincipal()).getUser();
        response.sendRedirect(linkTo(UsersController.class).slash(user.getId()).toUri().toString());
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<UserViewModel> getUser(@PathVariable("userId") int userId) {
        DcscUser user = userService.getUserById(userId).get();
        return resourceAssembler.toResource(user);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{userId}", method = RequestMethod.PATCH)
    public void updateUser(@PathVariable("userId") int userId, @RequestBody Map<String, Object> userForm) throws Exception {
        DcscUser user = userService.getUserById(userId).get();
        formBinder.bindToUser(user, userForm);
        userService.save(user);
    }
}
