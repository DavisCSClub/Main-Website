package org.dcsc.core.authentication.user;

import org.dcsc.core.authentication.user.application.UserApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserService {
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public User getById(int id) {
        return repository.findOne(id);
    }

    @Transactional(readOnly = true)
    public User getByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    @Transactional(readOnly = true)
    public User getByOpenIdIdentifier(String openIdIdentifier) {
        return repository.getUserByOpenIdIdentifier(openIdIdentifier);
    }

    @Transactional(readOnly = true)
    public boolean hasUser(String email) {
        return repository.getUserByEmail(email) != null;
    }

    @Transactional
    @PreAuthorize("hasAuthority('USERS')")
    public User create(UserApplication userApplication) {
        User user = new User();
        user.setName(userApplication.getName());
        user.setEmail(userApplication.getEmail());
        user.setLocked(false);
        user.setEnabled(true);

        return repository.save(user);
    }

    @Transactional
    public User update(User user) {
        return repository.save(user);
    }
}
