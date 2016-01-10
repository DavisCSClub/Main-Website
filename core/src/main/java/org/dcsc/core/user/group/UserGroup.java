package org.dcsc.core.user.group;

import org.dcsc.core.user.DcscUser;

import javax.persistence.*;

@Entity
@IdClass(UserGroupId.class)
@Table(name = "dcsc_user_groups", schema = "dcsc_accounts")
public class UserGroup {
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private DcscUser dcscUser;

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "is_admin")
    private boolean isAdmin;

    public UserGroup() {
        
    }

    public UserGroup(DcscUser dcscUser, Group group, boolean isAdmin) {
        this.dcscUser = dcscUser;
        this.group = group;
        this.isAdmin = isAdmin;
    }

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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
