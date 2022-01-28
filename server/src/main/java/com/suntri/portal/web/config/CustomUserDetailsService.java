package com.suntri.portal.web.config;

import com.suntri.portal.entity.User;
import com.suntri.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optUser = this.userRepository.findByUsername(username);
        if(optUser.isPresent()){
            User user = optUser.get();
            UserDetails userDetails = org.springframework.security.core.userdetails.User
                                            .withUsername(user.getUsername())
                                            .password(user.getPassword())
                                            .roles("USER")
                                            .build();
            return userDetails;
        }
        return null;
    }

}
