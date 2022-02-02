package com.suntri.portal.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoaderBootstrap implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired(required = false)
    private DataLoader dataLoader;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if(this.dataLoader != null) {
            this.dataLoader.loadData();
        }
    }
}
