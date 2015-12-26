package org.dcsc.core.user.group;

import org.dcsc.core.user.DcscUser;

import java.io.Serializable;

public class UserGroupId implements Serializable {
    private DcscUser dcscUser;
    private Group group;

    public DcscUser getDcscUser() {
        return dcscUser;
    }

    public void setDcscUser(DcscUser dcscUser) {
        this.dcscUser = dcscUser;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
