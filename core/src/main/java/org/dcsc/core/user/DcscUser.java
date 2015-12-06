package org.dcsc.core.user;

import javax.persistence.*;

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

    @OneToOne
    @JoinColumn(name = "role_id")
    private DcscRole role;

    public DcscRole getRole() {
        return role;
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
}
