package org.dcsc.configuration;

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
        permissionMask.put("READ", 1);
        permissionMask.put("CREATE", 2);
        permissionMask.put("UPDATE", 4);
        permissionMask.put("DELETE", 8);
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

        permission = StringUtils.toUpperCase(permission, Locale.ENGLISH);
        category = StringUtils.toUpperCase(category, Locale.ENGLISH);

        DcscUserDetails userDetails = (DcscUserDetails) authentication.getPrincipal();

        int userAccessLevel = userDetails.getPermissionLevel(category);
        int requestedAccessLevel = Optional.ofNullable(permissionMask.get(permission)).orElseGet(() -> 0);

        return ((userAccessLevel & requestedAccessLevel) > 0);
    }

    public List<String> getBitPermissionsAsString(int permissions) {
        List<String> stringPermissions = new ArrayList<>();

        Set<Map.Entry<String, Integer>> permissionMaskEntrySet = permissionMask.entrySet();

        for (Map.Entry<String, Integer> entry : permissionMaskEntrySet) {
            String ability = entry.getKey();
            int permissionBit = entry.getValue();

            if ((permissions & permissionBit) > 0) {
                stringPermissions.add(ability);
            }
        }

        return stringPermissions;
    }
}
