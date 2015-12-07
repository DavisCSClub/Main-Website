package org.dcsc.core.user.details;

import org.dcsc.core.navigation.NavigationBar;
import org.dcsc.core.navigation.NavigationBarFactory;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.permission.Permission;
import org.dcsc.core.user.permission.RolePermission;
import org.dcsc.core.user.permission.RolePermissionRepository;
import org.dcsc.core.user.role.DcscRole;
import org.dcsc.core.user.role.DcscRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DcscUserDetailsService implements UserDetailsService {
    @Autowired
    private DcscUserService userService;
    @Autowired
    private DcscRoleRepository dcscRoleRepository;
    @Autowired
    private RolePermissionRepository rolePermissionRepository;
    @Autowired
    private NavigationBarFactory navigationBarFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, LockedException, DisabledException {
        Optional<DcscUser> user = userService.getUserByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(String.format("Username %s was not found.", username));
        }

        DcscUser dcscUser = user.get();

        if (dcscUser.isLocked()) {
            throw new LockedException(String.format("Account %s is currently locked.", username));
        } else if (!dcscUser.isEnabled()) {
            throw new DisabledException(String.format("Account %s is disabled. If this is a mistake, contact the administration.", username));
        }

        long roleId = dcscUser.getRoleId();
        Collection<GrantedAuthority> authorities = getGrantedAuthorities(roleId);
        Map<String, Integer> permissionMap = getPermissionMap(roleId);
        NavigationBar navbar = navigationBarFactory.getNavigationBar(permissionMap);

        return new DcscUserDetails(user.get(), authorities, permissionMap, navbar);
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(long roleId) {
        DcscRole role = dcscRoleRepository.findOne(roleId);

        Set<GrantedAuthority> authorities = new HashSet<>();

        authorities.add(new SimpleGrantedAuthority(role.getName()));
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));

        return authorities;
    }

    private Map<String, Integer> getPermissionMap(long roleId) {
        return getPermissionMap(rolePermissionRepository.findByRoleId(roleId));
    }

    private Map<String, Integer> getPermissionMap(Set<RolePermission> rolePermissions) {
        Map<String, Integer> permissionMap = new HashMap<>();

        for (RolePermission rolePermission : rolePermissions) {
            Permission permission = rolePermission.getPermission();
            int access = rolePermission.getAccessLevel();

            permissionMap.put(permission.getClassName(), access);
        }

        return permissionMap;
    }
}
