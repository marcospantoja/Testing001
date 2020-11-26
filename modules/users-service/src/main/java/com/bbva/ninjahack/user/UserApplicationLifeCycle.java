package com.bbva.ninjahack.user;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
class UserApplicationLifeCycle {

    private static final Logger LOGGER = Logger.getLogger(UserApplicationLifeCycle.class);

    void onStart(@Observes StartupEvent ev) {
    	LOGGER.infof("The users service is starting with profile `%s`", ProfileManager.getActiveProfile());
    }                                                                                                                                                   
                                                                                                                                                                       
    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("Users service is stopping...");
    }
}
