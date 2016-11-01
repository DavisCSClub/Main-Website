package org.dcsc.core.authentication.user;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserDetails {
    private Collection<GrantedAuthority> authorities;
    private int id;
    private String name;
    private String email;
    private String picture;

    UserDetails(Collection<GrantedAuthority> authorities, int id, String name, String email, String picture) {
        this.id = id;
        this.authorities = authorities;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPicture() {
        return picture;
    }
}
