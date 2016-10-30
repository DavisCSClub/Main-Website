package org.dcsc.config.security;


import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;
import org.dcsc.core.authentication.user.User;
import org.dcsc.core.authentication.user.UserDetails;
import org.dcsc.core.authentication.user.UserDetailsFactory;
import org.dcsc.core.authentication.user.UserService;
import org.dcsc.core.authentication.user.NoLinkedAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
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
    private UserDetailsFactory userDetailsFactory;
    @Autowired
    private UserService userService;

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
        User user = userService.getByEmail(userInfo.getEmail());

        if (user == null) {
            throw new NoLinkedAccountException("Could not find a DCSC account associated with email");
        } else if (user.isLocked()) {
            throw new LockedException("Account locked");
        } else if (!user.isEnabled()) {
            throw new DisabledException("Account disabled");
        } else if (isVerifiedAccountNotLinked(user)) {
            user.setName(userInfo.getName());
            user.setOpenIdIdentifier(userInfo.getId());
            user.setPictureUrl(userInfo.getPicture());
            userService.update(user);
        } else if (isUserPictureUpdated(user, userInfo)) {
            user.setPictureUrl(userInfo.getPicture());
            userService.update(user);
        }

        UserDetails userDetails = userDetailsFactory.create(user);
        return createAuthentication(request, userDetails, userInfo, userDetails.getAuthorities());
    }

    private boolean isVerifiedAccountNotLinked(User user) {
        return StringUtils.isEmpty(user.getOpenIdIdentifier());
    }

    private boolean isUserPictureUpdated(User user, UserInfo userInfo) {
        String savedPicture = user.getPictureUrl();
        String candidatePicture = userInfo.getPicture();

        return !StringUtils.equals(savedPicture, candidatePicture);
    }

    private Authentication createAuthentication(HttpServletRequest request, Object userDetails, Object credentials,
                                                Collection<GrantedAuthority> authorities) {
        Authentication userAuth = new UsernamePasswordAuthenticationToken(userDetails, credentials, authorities);

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
        return new OAuth2Authentication(oauthRequest, userAuth);
    }

    private String constructRedirectUri(HttpServletRequest request) {
        String scheme = request.getScheme();
        String host = request.getServerName();
        int port = request.getServerPort();

        return String.format("%s://%s:%d/login-oidc", scheme, host, port);
    }
}
