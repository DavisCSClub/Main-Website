package org.dcsc.admin.controllers;

import org.dcsc.admin.dto.RestTransactionResult;
import org.dcsc.admin.profile.ProfileCreateForm;
import org.dcsc.admin.profile.ProfileForm;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.details.DcscUserDetails;
import org.dcsc.core.user.profile.UserProfile;
import org.dcsc.core.user.role.DcscRole;
import org.dcsc.core.user.role.DcscRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AdminProfileRestController {
    @Autowired
    private DcscUserService dcscUserService;
    @Autowired
    private DcscRoleRepository roleRepository;

    @RequestMapping(value = "/admin/r/profile/", method = RequestMethod.PUT)
    public RestTransactionResult submitProfileEdit(@RequestBody ProfileForm profileForm, Authentication authentication) {
        boolean success = false;
        String message = null;
        String password = profileForm.getPassword();
        String confirmPassword = profileForm.getConfirmPassword();

        if (StringUtils.hasLength(password) && password.equals(confirmPassword)) {
            DcscUserDetails dcscUserDetails = (DcscUserDetails) authentication.getPrincipal();
            DcscUser dcscUser = dcscUserDetails.getUser();

            dcscUser.setPassword(new BCryptPasswordEncoder().encode(password));

            dcscUserService.save(dcscUser);

            success = true;
            message = "Password successfully changed.";
        } else {
            message = "Failed to update password. Passwords did not match.";
        }

        return new RestTransactionResult(success, message);
    }

    @RequestMapping(value = "/admin/r/user", method = RequestMethod.POST)
    @PreAuthorize("hasPermission('user','create')")
    public RestTransactionResult createNewUser(@RequestBody ProfileCreateForm profileCreateForm) {
        RestTransactionResult transactionResult = null;

        if (profileCreateForm.getPassword().equals(profileCreateForm.getConfirmPassword())) {
            Optional<DcscUser> dcscUser = dcscUserService.getUserByUsername(profileCreateForm.getUsername());

            if (dcscUser.isPresent()) {
                transactionResult = new RestTransactionResult(false, "Existing username in database.");
            } else {
                Optional<DcscRole> role = roleRepository.findByName(profileCreateForm.getRole());

                if (role.isPresent()) {
                    DcscUser user = new DcscUser();
                    UserProfile userProfile = new UserProfile();

                    userProfile.setName(profileCreateForm.getName());
                    userProfile.setTitle(profileCreateForm.getTitle());

                    user.setUsername(profileCreateForm.getUsername());
                    user.setPassword(new BCryptPasswordEncoder().encode(profileCreateForm.getPassword()));
                    user.setEnabled(profileCreateForm.isActive());
                    user.setLocked(!profileCreateForm.isUnlocked());
                    user.setUserProfile(userProfile);
                    user.setRoleId(role.get().getId());

                    dcscUserService.save(user);

                    transactionResult = new RestTransactionResult(true, "Account successfully created.");
                } else {
                    transactionResult = new RestTransactionResult(false, String.format("Could not create account with role - %s", profileCreateForm.getRole()));
                }
            }
        }

        return transactionResult;
    }
}
