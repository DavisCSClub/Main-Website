package org.dcsc.config.security;


import com.google.common.collect.ImmutableSet;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.NoLinkedAccountException;
import org.dcsc.core.user.details.DcscUserDetails;
import org.dcsc.core.user.permission.RolePermissionService;
import org.dcsc.core.user.role.DcscRole;
import org.dcsc.core.user.role.DcscRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class OpenIdConnectAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private static final String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
    @Autowired
    private OAuth2RestTemplate restTemplate;
    @Autowired
    private DcscUserService userService;
    @Autowired
    private DcscRoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;

    OpenIdConnectAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);

        SimpleUrlAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/admin");

        setAuthenticationSuccessHandler(successHandler);
        setAuthenticationManager(authentication -> authentication);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        UserInfo userInfo = restTemplate.getForEntity(GOOGLE_USER_INFO_URL, UserInfo.class).getBody();

        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Collection<GrantedAuthority> authorities;

        if (authentication != null) {
            DcscUserDetails userDetails = (DcscUserDetails) authentication.getPrincipal();
            DcscUser user = userDetails.getUser();
            String userOidcId = user.getOidcId();
            String userInfoOidcId = userInfo.getId();

            if (userOidcId == null) {
                user.setOidcId(userInfoOidcId);
                try {
                    userService.save(user);
                } catch (DataIntegrityViolationException e) {
                    throw new BadCredentialsException(
                            "Authenticated Google account already linked to another DCSC account");
                }
            } else if (!userOidcId.equals(userInfoOidcId)) {
                throw new BadCredentialsException("Authenticated Google account does not match linked account");
            }
        } else {
            DcscUser user = userService.getUserByOidcId(userInfo.getId());

            if (user != null) {
                long roleId = user.getRoleId();
                DcscRole role = roleService.getRole(roleId);

                authorities = roleService.getRoleAuthorities(role);
                Map<String, Integer> permissions = rolePermissionService.getPermissionMap(roleId);
                DcscUserDetails userDetails = new DcscUserDetails(user, authorities, permissions);
                authentication = new UsernamePasswordAuthenticationToken(userDetails, userInfo, authorities);

                authentication = createAuthentication(request, authentication, authorities);
            } else {
                throw new NoLinkedAccountException("Could not find linked DCSC user account.");
            }
        }

        return authentication;
    }

    private OAuth2Authentication createAuthentication(HttpServletRequest request, Authentication authentication,
                                                      Collection<GrantedAuthority> authorities) {
        String clientId = restTemplate.getResource().getClientId();
        String redirectUri = constructRedirectUri(request);
        Set<String> scope = restTemplate.getResource().getScope().stream().collect(Collectors.toSet());
        Set<String> responseTypes = ImmutableSet.of("code");

        Map<String, String> requestParameters = new HashMap<>();
        requestParameters.put(OAuth2Utils.CLIENT_ID, clientId);
        requestParameters.put(OAuth2Utils.REDIRECT_URI, redirectUri);
        requestParameters.put(OAuth2Utils.SCOPE, OAuth2Utils.formatParameterList(scope));
        requestParameters.put(OAuth2Utils.RESPONSE_TYPE, OAuth2Utils.formatParameterList(responseTypes));

        OAuth2Request oauthRequest = new OAuth2Request(requestParameters, clientId, authorities, true, scope, null,
                                                       redirectUri, responseTypes, null);
        return new OAuth2Authentication(oauthRequest, authentication);
    }

    private String constructRedirectUri(HttpServletRequest request) {
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();

        return String.format("%s://%s:%d/login-oidc", scheme, host, port);
    }
}
