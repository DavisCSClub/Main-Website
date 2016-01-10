package org.dcsc.core.user;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class DcscUserSessionHolder {
    private DcscUser dcscUser;

    public boolean hasSession() {
        return (dcscUser != null);
    }

    public void setUser(DcscUser dcscUser) {
        this.dcscUser = dcscUser;
    }

    public DcscUser getUser() {
        return dcscUser;
    }
}
