package org.dcsc.core.user.permission;

import org.dcsc.core.user.role.DcscRole;

import java.io.Serializable;

@Deprecated
public class RolePermissionId implements Serializable {
    private DcscRole role;
    private Permission permission;

    public DcscRole getRole() {
        return role;
    }

    public void setRole(DcscRole role) {
        this.role = role;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
}
