package com.example.security.service;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

    private final OAuth2AuthorizedClientService authorizedClientService;

    public OAuthService(OAuth2AuthorizedClientService authorizedClientService) {
        this.authorizedClientService = authorizedClientService;
    }

    public String getGitHubToken(@AuthenticationPrincipal(expression = "name") String username) {
        OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
                "github", username);

        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        return accessToken != null ? accessToken.getTokenValue() : null;
    }
}
