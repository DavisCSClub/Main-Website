package org.dcsc.core.user.details;

import org.dcsc.core.user.DcscUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Map;

/**
 * Use UserDetails instead
 */
@Deprecated
public class DcscUserDetails extends User {
    private final DcscUser user;
    private Map<String, Integer> permissions;

    public DcscUserDetails(DcscUser user, Collection<GrantedAuthority> authorities, Map<String, Integer> permissions) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
        this.permissions = permissions;
    }

    public DcscUser getUser() {
        return user;
    }

    public Map<String, Integer> getPermissions() {
        return permissions;
    }

    public int getPermissionLevel(String category) {
        Integer access = permissions.get(category);

        return (access != null) ? access : 0;
    }
}
