package org.dcsc.core.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "dcsc_roles", schema = "dcsc_accounts")
public class DcscRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private Set<RolePermission> rolePermissions;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>(rolePermissions.size());

        authorities.add(new SimpleGrantedAuthority(name));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + name));

        return authorities;
    }

    public Collection<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }

}
