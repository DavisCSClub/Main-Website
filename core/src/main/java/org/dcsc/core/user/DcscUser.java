package org.dcsc.core.user;

import org.dcsc.core.user.group.Group;
import org.dcsc.core.user.group.UserGroup;
import org.dcsc.core.user.profile.UserProfile;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "dcsc_users", schema = "dcsc_accounts")
public class DcscUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;

    @Column(name = "is_locked", nullable = false)
    private boolean locked;

    @Column(name = "role_id", nullable = false)
    private long roleId;

    @Column(name = "oidc_id")
    private String oidcId;

    @JoinColumn(name = "profile_id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserProfile userProfile;

    @OneToMany(mappedBy = "dcscUser", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserGroup> userGroups;

    public long getRoleId() {
        return roleId;
    }

    public void setRole(Role role) {
        // nop
    }

    public void setName(String name) {
        // nop
    }

    public void setEmail(String email) {
        //nop
    }

    public String getOidcId() {
        return oidcId;
    }

    public void setOidcId(String oidcId) {
        this.oidcId = oidcId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public List<UserGroup> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public boolean inGroup(String groupName) {
        for (UserGroup group : userGroups) {
            if (groupName.equals(group.getGroup().getName())) {
                return true;
            }
        }

        return false;
    }

    public UserGroup getUserGroup(String groupName) {
        UserGroup userGroup = null;

        for (UserGroup group : userGroups) {
            if (groupName.equals(group.getGroup().getName())) {
                userGroup = group;
                break;
            }
        }

        return userGroup;
    }

    public void addGroup(Group group) {
        addGroup(group, false);
    }

    public void addGroup(Group group, boolean isAdmin) {
        for (UserGroup userGroup : userGroups) {
            if (userGroup.getGroup().getName().equals(group.getName())) {
                return;
            }
        }

        userGroups.add(new UserGroup(this, group, isAdmin));
    }

    public void removeGroup(String groupName) {
        userGroups.removeIf(userGroup -> userGroup.getGroup().getName().equals(groupName));
    }
}
