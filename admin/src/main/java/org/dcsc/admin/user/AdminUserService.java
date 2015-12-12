package org.dcsc.admin.user;

import org.dcsc.admin.profile.AccountForm;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.profile.UserProfile;
import org.dcsc.core.user.role.DcscRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminUserService {
    @Autowired
    private DcscUserService userService;
    @Autowired
    private DcscRoleService dcscRoleService;

    public AccountForm getAccountForm(long id) {
        AccountForm form = new AccountForm();

        Optional<DcscUser> userWrapper = userService.getUserById(id);

        if (userWrapper.isPresent()) {
            DcscUser user = userWrapper.get();
            UserProfile userProfile = user.getUserProfile();

            form.setUsername(user.getUsername());
            form.setIsActive(user.isEnabled());
            form.setIsUnlocked(!user.isLocked());

            form.setName(userProfile.getName());
            form.setRole(dcscRoleService.getRole(user.getRoleId()).getName());
            form.setTitle(userProfile.getTitle());
        }

        return form;
    }
}
