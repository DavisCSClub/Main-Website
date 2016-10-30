package org.dcsc.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableOAuth2Client
public class OAuth2Configuration {
    private static final List<String> OAUTH2_SCOPES = Arrays.asList("openid", "profile", "email");

    @Value("${google.oauth2.clientId:clientId}")
    private String clientId;
    @Value("${google.oauth2.clientSecret:clientSecret}")
    private String clientSecret;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private OAuth2ClientContext oAuth2ClientContext;

    @Bean
    public OAuth2ProtectedResourceDetails googleOAuth2Details() {
        AuthorizationCodeResourceDetails googleOAuth2Details = new AuthorizationCodeResourceDetails();
        googleOAuth2Details.setAuthenticationScheme(AuthenticationScheme.form);
        googleOAuth2Details.setClientAuthenticationScheme(AuthenticationScheme.form);
        googleOAuth2Details.setClientId(clientId);
        googleOAuth2Details.setClientSecret(clientSecret);
        googleOAuth2Details.setUserAuthorizationUri("https://accounts.google.com/o/oauth2/auth");
        googleOAuth2Details.setAccessTokenUri("https://www.googleapis.com/oauth2/v3/token");
        googleOAuth2Details.setScope(OAUTH2_SCOPES);
        return googleOAuth2Details;
    }

    @Bean
    @SessionScope
    public OAuth2RestTemplate googleOAuth2RestTemplate() {
        return new OAuth2RestTemplate(googleOAuth2Details(), oAuth2ClientContext);
    }

    @Bean
    public OAuth2ClientContextFilter oAuth2ClientContextFilter() {
        return new OAuth2ClientContextFilter();
    }
}
