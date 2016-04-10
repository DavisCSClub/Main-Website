package org.dcsc.core.user.profile;

import org.dcsc.core.user.profile.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findUserProfileById(Long id);
}