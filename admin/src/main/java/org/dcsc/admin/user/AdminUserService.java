package org.dcsc.admin.user;

import org.dcsc.admin.profile.AccountForm;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.profile.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    @Autowired
    private DcscUserService userService;

    public AccountForm getAccountForm(long id) {
        DcscUser dcscUser = userService.getUserById(id).get();
        UserProfile userProfile = dcscUser.getUserProfile();
        AccountForm form = new AccountForm();

        form.setUsername(dcscUser.getUsername());
        form.setIsActive(dcscUser.isEnabled());
        form.setIsUnlocked(!dcscUser.isLocked());

        form.setName(userProfile.getName());
        form.setTitle(userProfile.getTitle());


        return form;
    }
}
