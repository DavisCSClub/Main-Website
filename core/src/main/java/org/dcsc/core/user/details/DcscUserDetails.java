package org.dcsc.core.user.details;

import org.dcsc.core.user.DcscUser;
import org.springframework.security.core.userdetails.User;

public class DcscUserDetails extends User {
    private final DcscUser user;

    public DcscUserDetails(DcscUser user) {
        super(user.getUsername(), user.getPassword(), user.getRole().getAuthorities());

        this.user = user;
    }

    public DcscUser getUser() {
        return user;
    }

}
