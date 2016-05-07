package org.dcsc.admin.users;

import org.dcsc.core.user.DcscUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserFormBinder {
    public void bindToUser(DcscUser user, Map<String, Object> userForm) throws Exception {
        for (Map.Entry<String, Object> entry : userForm.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            if (isProfileDescription(key)) {
                user.getUserProfile().setDescription((String) value);
            } else if (isFormPassword(key) && passwordMatches((String) value, (String) userForm.get("user_form_confirm_password"))) {
                user.setPassword(new BCryptPasswordEncoder().encode((String) value));
            } else if (isFormEmail(key)) {
                user.getUserProfile().setEmail((String) value);
            }
        }
    }

    private boolean isProfileDescription(String key) {
        return "user_form_description".equals(key);
    }

    private boolean isFormPassword(String key) {
        return "user_form_password".equals(key);
    }

    private boolean passwordMatches(String password, String confirmPassword) {
        return password.matches(confirmPassword);
    }

    private boolean isFormEmail(String key) {
        return "user_form_email".equals(key);
    }
}
