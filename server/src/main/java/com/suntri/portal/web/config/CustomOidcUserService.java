package com.suntri.portal.web.config;

import com.suntri.portal.entity.Role;
import com.suntri.portal.entity.User;
import com.suntri.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
public class CustomOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

    private OidcUserService delegate = new OidcUserService();

    @Autowired
    private UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = delegate.loadUser(userRequest);
        OAuth2AccessToken accessToken = userRequest.getAccessToken();
        Optional<User> optUser = userRepository.findByEmail(oidcUser.getEmail());

        if(optUser.isPresent()){
            User user = optUser.get();
            Set<Role> roles = user.getRoles();
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            roles.forEach(role -> {
                mappedAuthorities.add(new SimpleGrantedAuthority(String.format("ROLE_%s", role.getName().toUpperCase())));
            });

            OidcIdToken.Builder builder = OidcIdToken.withTokenValue(userRequest.getIdToken().toString());
            OidcIdToken customizedToken = builder.subject(user.getUsername()).build();
            OidcUser modifiedOidcUser = new DefaultOidcUser(mappedAuthorities, customizedToken, oidcUser.getUserInfo());
            return modifiedOidcUser;
        } else {
            throw new OAuth2AuthenticationException(new OAuth2Error("Not registered yet."), "Access denied.");
        }
    }
}
