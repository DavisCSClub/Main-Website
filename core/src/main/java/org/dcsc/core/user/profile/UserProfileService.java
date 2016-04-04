package org.dcsc.core.user.profile;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.dcsc.core.user.profile.UserProfile;
import org.dcsc.core.user.profile.UserProfileRepository;

@Service
public class UserProfileService {

	@Autowired
    private UserProfileRepository userProfileRepository;

    UserProfile findUserProfileById(Long id) {
    	return userProfileRepository.findUserProfileById(id);
    }
}
