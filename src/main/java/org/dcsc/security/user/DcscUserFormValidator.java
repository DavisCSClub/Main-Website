package org.dcsc.security.user;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by tktong on 7/26/2015.
 */
@Component
public class DcscUserFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DcscUserForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DcscUserForm dcscUserForm = (DcscUserForm) target;

        validatePassword(dcscUserForm, errors);
    }

    private void validatePassword(DcscUserForm dcscUserForm, Errors errors) {
        String password = dcscUserForm.getPassword();
        String confirmPassword = dcscUserForm.getConfirmPassword();

        if(!password.equals(confirmPassword)) {
            errors.reject("password.no_match", "Password and confirmation do not match.");
        }
    }
}
