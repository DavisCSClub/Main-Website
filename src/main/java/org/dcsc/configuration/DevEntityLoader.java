package org.dcsc.configuration;

import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DevEntityLoader implements ApplicationListener<ContextRefreshedEvent> {
    private DcscUserService userService;

    public DevEntityLoader(DcscUserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        DcscUser user = new DcscUser();

        user.setUsername("webdev");
        user.setPassword(new BCryptPasswordEncoder().encode("password"));
        user.setEmail("text@website.org");
        user.setRoleId(1);
        user.setEnabled(true);
        user.setLocked(false);

        userService.save(user);
    }
}
