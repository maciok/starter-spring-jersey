package pl.pietrzam.starter;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/")
class JerseyConfiguration extends ResourceConfig {


    @PostConstruct
    void setUp() {
        register(StarterController.class);
    }
}
