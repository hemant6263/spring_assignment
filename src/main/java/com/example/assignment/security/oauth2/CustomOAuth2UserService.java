package com.example.assignment.security.oauth2;

import com.example.assignment.entity.User;
import com.example.assignment.exception.OAuth2AuthenticationProcessingException;
import com.example.assignment.model.AuthProvider;
import com.example.assignment.repository.UserRepository;
import com.example.assignment.security.UserPrincipal;
import com.example.assignment.security.oauth2.user.OAuth2UserInfo;
import com.example.assignment.security.oauth2.user.OAuth2UserInfoFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
        									.getOAuth2UserInfo(oAuth2UserRequest
        															.getClientRegistration()
        															.getRegistrationId()
        															, oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<User> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        User user = null;
        if(userOptional.isPresent()) {
            user = userOptional.get();
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("plesase use appropriate provider details");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
            return UserPrincipal.create(user, oAuth2User.getAttributes());
        }else {
        	throw new BadCredentialsException("User Does not belog to system contact admin");
        }
      
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        if(StringUtils.isEmpty(existingUser.getRoles())) {
        	existingUser.setRoles("ROLE_USER");
        }
        return userRepository.save(existingUser);
    }

}
