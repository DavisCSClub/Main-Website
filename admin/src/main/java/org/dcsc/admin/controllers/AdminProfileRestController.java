package org.dcsc.admin.controllers;

import org.dcsc.admin.dto.RestTransactionResult;
import org.dcsc.admin.profile.ProfileForm;
import org.dcsc.core.user.DcscUser;
import org.dcsc.core.user.DcscUserService;
import org.dcsc.core.user.details.DcscUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
public class AdminProfileRestController {
    @Autowired
    private DcscUserService dcscUserService;

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
}
