package org.dcsc.admin.view.model.user;

import org.dcsc.admin.controllers.UsersController;
import org.dcsc.admin.view.model.permission.PermissionViewModel;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.group.UserGroup;
import org.dcsc.core.user.permission.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<DcscUser, Resource> {
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private PermissionTranslator permissionTranslator;

    public UserResourceAssembler() {
        this(UsersController.class, Resource.class);
    }

    public UserResourceAssembler(Class<?> controllerClass, Class<Resource> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public List<Resource> toResources(Iterable<? extends DcscUser> users) {
        List<Resource> resources = new ArrayList<>();
        users.forEach(user -> resources.add(toResource(user)));
        return resources;
    }

    @Override
    public Resource toResource(DcscUser user) {
        UserViewModel viewModel = new UserViewModel(user.getUserProfile());

        List<Map<String, Object>> groups = new ArrayList<>();
        for (UserGroup userGroup : user.getUserGroups()) {
            Map<String, Object> group = new HashMap<>();
            group.put("name", userGroup.getGroup().getName());
            group.put("isAdmin", userGroup.isAdmin());
            groups.add(group);
        }

        viewModel.put("groups", groups);

        Map<String, Integer> permissionMap = rolePermissionService.getPermissionMap(user.getRoleId());
        List<PermissionViewModel> permissions = new ArrayList<>();
        for (Map.Entry<String, Integer> permission : permissionMap.entrySet()) {
            List<String> permissionStrings = permissionTranslator.getPermissionStrings(permission.getValue());
            PermissionViewModel permissionViewModel = new PermissionViewModel();
            permissionViewModel.put("name", permission.getKey());
            permissionViewModel.put("permission", permissionStrings.stream().collect(Collectors.joining(", ")));
            permissions.add(permissionViewModel);
        }
        viewModel.put("permissions", permissions);

        return new Resource(viewModel, getRestEndpoint(user));
    }

    private Link getRestEndpoint(DcscUser user) {
        return linkTo(UsersController.class).slash(user.getId()).withSelfRel();
    }
}
