package org.dcsc.core.user.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DcscRoleService {
    @Autowired
    private DcscRoleRepository roleRepository;

    @Transactional(readOnly = true)
    public DcscRole getRole(long id) {
        return roleRepository.findOne(id);
    }

    @Transactional(readOnly = true)
    public List<DcscRole> getAllRoles() {
        return roleRepository.findAll();
    }

    public Collection<GrantedAuthority> getRoleAuthorities(long id) {
        DcscRole role = getRole(id);

        return getRoleAuthorities(role);
    }

    public Collection<GrantedAuthority> getRoleAuthorities(DcscRole role) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(role.getName()));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

        return authorities;
    }
}
