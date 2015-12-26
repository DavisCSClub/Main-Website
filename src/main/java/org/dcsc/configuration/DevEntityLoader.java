package org.dcsc.configuration;

import org.dcsc.core.user.DcscUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class DevEntityLoader implements ApplicationListener<ContextRefreshedEvent> {
    private DcscUserService userService;

    public DevEntityLoader(DcscUserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
    }
}
