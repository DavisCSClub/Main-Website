package org.dcsc.core.user.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile findUserProfileById(int id) {
        return userProfileRepository.findUserProfileById((long) id);
    }
}
