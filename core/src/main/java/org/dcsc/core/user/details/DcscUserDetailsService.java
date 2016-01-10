package org.dcsc.core.user.details;

import org.dcsc.core.navigation.NavigationBar;
import org.dcsc.core.navigation.NavigationBarFactory;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.permission.RolePermissionService;
import org.dcsc.core.user.role.DcscRole;
import org.dcsc.core.user.role.DcscRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@Service
public class DcscUserDetailsService implements UserDetailsService {
    @Autowired
    private DcscUserService userService;
    @Autowired
    private DcscRoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;
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
        DcscRole role = roleService.getRole(roleId);

        Collection<GrantedAuthority> authorities = roleService.getRoleAuthorities(role);
        Map<String, Integer> permissionMap = rolePermissionService.getPermissionMap(roleId);
        NavigationBar navbar = navigationBarFactory.getNavigationBar(dcscUser, permissionMap);

        return new DcscUserDetails(dcscUser, authorities, role, permissionMap, navbar);
    }
}
