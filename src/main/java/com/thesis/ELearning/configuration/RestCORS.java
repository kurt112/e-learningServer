package com.thesis.ELearning.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RestCORS extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration registry) {

        registry.getCorsRegistry()
                .addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowCredentials(false)
                .allowedMethods("GET","POST", "PUT", "DELETE");
    }


}
