package org.dcsc.admin.view.model.user;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PermissionTranslator {
    public List<String> getPermissionStrings(int permission) {
        List<String> permissionStrings = new ArrayList<>();

        if (permission <= 0 || permission > 16) {
            permissionStrings.add("NONE");
        } else {
            if (hasRead(permission)) {
                permissionStrings.add("READ");
            }
            if (hasCreate(permission)) {
                permissionStrings.add("CREATE");
            }
            if (hasUpdate(permission)) {
                permissionStrings.add("UPDATE");
            }
            if (hasDelete(permission)) {
                permissionStrings.add("DELETE");
            }
        }

        return permissionStrings;
    }

    private boolean hasRead(int permission) {
        return ((permission & 1) == 1);
    }

    private boolean hasCreate(int permission) {
        return ((permission & 2) == 1);
    }

    private boolean hasUpdate(int permission) {
        return ((permission & 4) == 1);
    }

    private boolean hasDelete(int permission) {
        return ((permission & 8) == 1);
    }
}
