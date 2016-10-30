package org.dcsc.config.security;


import org.dcsc.core.authentication.user.User;
import org.dcsc.core.authentication.user.UserDetails;
import org.dcsc.core.authentication.user.UserDetailsFactory;
import org.dcsc.core.authentication.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * For developer environments only
 */
class DevAuthenticationProvider implements AuthenticationProvider {
    private static final String DEV_USERNAME = "dcsc";
    private static final String DEV_EMAIL = "officers@daviscsclub.org";
    private static final String DEV_PASSWORD = "password";

    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsFactory userDetailsFactory;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication result;
        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        String password = authentication.getCredentials().toString();

        if (DEV_USERNAME.equals(username) && DEV_PASSWORD.equals(password)) {
            User user = userService.getByEmail(DEV_EMAIL);
            UserDetails details = userDetailsFactory.create(user);

            result = new UsernamePasswordAuthenticationToken(details, password, details.getAuthorities());
        } else {
            throw new BadCredentialsException("Username or password does not match");
        }

        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
