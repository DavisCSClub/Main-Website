package org.dcsc.configuration;

import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.Permission;
import org.dcsc.core.user.RolePermission;
import org.dcsc.core.user.details.DcscUserDetails;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;

@Component
public class DcscPermissionEvaluator implements PermissionEvaluator {
    private static final Map<String, Integer> permissionMask = new HashMap<>();

    @PostConstruct
    private void initialize() {
        permissionMask.put("read", 1);
        permissionMask.put("create", 2);
        permissionMask.put("update", 4);
        permissionMask.put("delete", 8);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }

    public boolean hasPermission(Authentication authentication, String category, String permission) {
        Assert.notNull(authentication);
        Assert.notNull(category);
        Assert.notNull(permission);

        permission = StringUtils.toLowerCase(permission, Locale.ENGLISH);

        DcscUserDetails userDetails = (DcscUserDetails) authentication.getPrincipal();
        DcscUser user = userDetails.getUser();

        Collection<RolePermission> permissions = user.getRole().getRolePermissions();
        int accessCode = Optional.ofNullable(permissionMask.get(permission)).orElseGet(() -> 0);

        for (RolePermission rolePermission : permissions) {
            Permission p = rolePermission.getPermission();
            int access = rolePermission.getAccessLevel();

            String className = p.getClassName();
            if (className.equals(category) && ((access & accessCode) > 0)) {
                return true;
            }
        }

        return false;
    }
}
