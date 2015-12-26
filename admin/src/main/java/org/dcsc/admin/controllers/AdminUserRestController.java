package org.dcsc.admin.controllers;

import org.dcsc.admin.dto.RestTransactionResult;
import org.dcsc.admin.profile.AccountUpdateForm;
import org.dcsc.admin.profile.ProfileCreateForm;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.profile.UserProfile;
import org.dcsc.core.user.role.DcscRole;
import org.dcsc.core.user.role.DcscRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AdminUserRestController {
    @Autowired
    private DcscUserService dcscUserService;
    @Autowired
    private DcscRoleRepository roleRepository;

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

    @RequestMapping(value = "/admin/r/user/{user_id}", method = RequestMethod.PUT)
    @PreAuthorize("hasPermission('user','update')")
    public RestTransactionResult updateUser(@PathVariable("user_id") String userId, @RequestBody AccountUpdateForm accountUpdateForm) {
        RestTransactionResult transactionResult = null;

        try {
            int id = Integer.parseInt(userId);

            DcscUser dcscUser = dcscUserService.getUserById(id).orElseThrow(() -> new Exception("Invalid User Id"));
            DcscRole role = roleRepository.findByName(accountUpdateForm.getRole()).orElseThrow(() -> new Exception("Invalid Role Name"));

            dcscUser.getUserProfile().setTitle(accountUpdateForm.getTitle());
            dcscUser.setRoleId(role.getId());
            dcscUser.setLocked(!accountUpdateForm.isUnlocked());
            dcscUser.setEnabled(accountUpdateForm.isActive());

            dcscUserService.save(dcscUser);

            transactionResult = RestTransactionResult.success(String.format("User %d successfully updated.", id));
        } catch (Exception e) {
            transactionResult = RestTransactionResult.fail(String.format("Failed to update user %s.", userId));
        }
        return transactionResult;
    }
}
