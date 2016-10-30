package org.dcsc.core.authentication.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users", schema = "authentication")
public class User {
    private int id;
    private String openIdIdentifier;
    private String name;
    private String email;
    private boolean isEnabled;
    private boolean isLocked;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "oidc_id", unique = true)
    public String getOpenIdIdentifier() {
        return openIdIdentifier;
    }

    public void setOpenIdIdentifier(String openIdIdentifier) {
        this.openIdIdentifier = openIdIdentifier;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "is_enabled")
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Column(name = "is_locked")
    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }
}
