package com.suntri.portal.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DataLoaderDev extends DataLoader {

    @Override
    public void loadProfileSpecificData() {
        // loading nothing currently
    }
}
