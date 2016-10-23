package org.dcsc.config.social;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.client.RestTemplate;

@Configuration
public class FacebookConfig {
    private static final String FACEBOOK_OAUTH_URL = "https://graph.facebook.com/oauth/access_token?client_id={appId}&client_secret={appSecret}&grant_type=client_credentials";
    private static final String NO_NAMESPACE = "";

    @Bean
    @ConditionalOnProperty(name = {"spring.social.facebook.app-id", "spring.social.facebook.app-secret"})
    public Facebook productionFacebookRestClient(@Value("${spring.social.facebook.app-id}") String appId,
                                                 @Value("${spring.social.facebook.app-secret}") String appSecret) {
        String accessToken = getAccessToken(appId, appSecret);
        return new FacebookTemplate(accessToken, NO_NAMESPACE, appId);
    }


    private String getAccessToken(String appId, String appSecret) {
        String response = new RestTemplate().getForObject(FACEBOOK_OAUTH_URL, String.class, appId, appSecret);
        int delimiter = response.indexOf("=") + 1;

        return response.substring(delimiter);
    }
}