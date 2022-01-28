package com.suntri.portal.web.config;

import com.suntri.portal.entity.Role;
import com.suntri.portal.entity.User;
import com.suntri.portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Configuration
public class DataLoader implements ApplicationListener {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if(event instanceof ApplicationReadyEvent){
            ApplicationReadyEvent readyEvent = (ApplicationReadyEvent) event;
            Environment env = readyEvent.getApplicationContext().getEnvironment();
            if(Arrays.stream(env.getActiveProfiles()).anyMatch("local"::equalsIgnoreCase)){
                Role role = Role.builder().name("admin").build();
                User userAdmin = User.builder().username("admin").password("admin").role(role).build();
                this.userRepository.save(userAdmin);
            }
        }

    }
}
