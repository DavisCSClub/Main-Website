package org.dcsc.core.user.permission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class RolePermissionService {
    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public Map<String, Integer> getPermissionMap(long roleId) {
        Set<RolePermission> rolePermissionSet = rolePermissionRepository.findByRoleId(roleId);
        Map<String, Integer> permissionMap = new HashMap<>(rolePermissionSet.size());

        for (RolePermission rolePermission : rolePermissionSet) {
            Permission permission = rolePermission.getPermission();
            int accessLevel = rolePermission.getAccessLevel();
            
            permissionMap.put(permission.getClassName().toUpperCase(), accessLevel);
        }

        return permissionMap;
    }
}
