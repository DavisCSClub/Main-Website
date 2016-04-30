package org.dcsc.admin.view.model.user;

import org.dcsc.admin.view.model.ViewModel;
import org.dcsc.core.user.profile.UserProfile;

public class UserViewModel extends ViewModel {
    public UserViewModel(UserProfile userProfile) {
        super.addAttribute("details", userProfile);
    }
}
