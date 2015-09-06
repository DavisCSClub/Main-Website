package org.dcsc.model.user;

import java.util.NoSuchElementException;

/**
 * Created by tktong on 8/5/2015.
 */
public class DcscSuperUserEditForm implements DcscUserForm {
    private long id;
    private Role role;
    private boolean enabled;
    private boolean locked;

    public long getId() throws NoSuchElementException {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @Override
    public DcscUser build() {
        DcscUser user = new DcscUser();

        user.setId(id);
        user.setRole(role);
        user.setEnabled(enabled);
        user.setLocked(locked);

        return user;
    }

    @Override
    public DcscUser build(DcscUser dcscUser) {
        dcscUser.setRole(role);
        dcscUser.setEnabled(enabled);
        dcscUser.setLocked(locked);

        return dcscUser;
    }
}
