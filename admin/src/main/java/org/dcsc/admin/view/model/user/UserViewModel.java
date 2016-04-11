package org.dcsc.admin.view.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.dcsc.admin.view.model.ViewModel;
import org.dcsc.core.user.profile.UserProfile;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserViewModel extends ViewModel {
    public UserViewModel(UserProfile userProfile) {
        put("details", userProfile);
    }
}
