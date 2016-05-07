package org.dcsc.admin.users;

import org.dcsc.core.user.DcscUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class UserFormBinder {
    public void bindToUser(DcscUser user, Map<String, Object> userForm) throws Exception {
        Optional.ofNullable((String) userForm.get("user_form_description"))
                .ifPresent(description -> user.getUserProfile().setDescription(description));

        Optional.ofNullable((String) userForm.get("user_form_password"))
                .filter(password -> !password.isEmpty())
                .filter(password -> password.equals(userForm.get("user_form_confirm_password")))
                .ifPresent(password -> user.setPassword(new BCryptPasswordEncoder().encode(password)));

        Optional.ofNullable((String) userForm.get("user_form_email"))
                .ifPresent(email -> user.getUserProfile().setEmail(email));
    }
}
