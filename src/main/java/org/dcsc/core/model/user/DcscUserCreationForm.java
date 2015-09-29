package org.dcsc.core.model.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by tktong on 8/5/2015.
 */
public class DcscUserCreationForm implements DcscUserForm {
    public static final String THYMELEAF_FRAGMENT = "admin/user-create-form::user-create-form";

    private String name;
    private String username;
    private String email;
    private String password;
    private String confirmPassword;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public DcscUser build() {
        DcscUser user = new DcscUser();

        user.setName(name);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRole(Role.ROLE_USER);
        user.setEnabled(true);
        user.setLocked(false);

        return user;
    }

    @Override
    public DcscUser build(DcscUser dcscUser) {
        dcscUser.setName(name);
        dcscUser.setUsername(username);
        dcscUser.setEmail(email);
        dcscUser.setPassword(new BCryptPasswordEncoder().encode(password));
        dcscUser.setRole(Role.ROLE_USER);

        return dcscUser;
    }
}
