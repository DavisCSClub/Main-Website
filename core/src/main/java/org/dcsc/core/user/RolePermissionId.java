package org.dcsc.core.user;

import java.io.Serializable;

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
