package com.example.assignment.security.oauth2.user;

import com.example.assignment.exception.OAuth2AuthenticationProcessingException;
import com.example.assignment.model.AuthProvider;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("No Support for Oauth Provider");
        }
    }
}
