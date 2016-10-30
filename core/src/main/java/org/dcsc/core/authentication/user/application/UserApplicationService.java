package org.dcsc.core.authentication.user.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserApplicationService {
    @Autowired
    private UserApplicationRepository repository;

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USERS')")
    public UserApplication get(int id) {
        return repository.findOne(id);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('USERS')")
    public List<UserApplication> getAll() {
        List<UserApplication> userApplications = new ArrayList<>();
        repository.findAll().forEach(userApplications::add);

        return userApplications;
    }

    @Transactional
    public void createApplication(String name, String email) {
        UserApplication application = new UserApplication();
        application.setName(name);
        application.setEmail(email);
        repository.save(application);
    }

    @Transactional
    public void remove(int id) {
        repository.delete(id);
    }

    @Transactional
    public void remove(UserApplication userApplication) {
        repository.delete(userApplication);
    }

    @Transactional(readOnly = true)
    public boolean hasApplication(String email) {
        return repository.getUserApplicationByEmail(email).isPresent();
    }
}
