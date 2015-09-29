package org.dcsc.logical.user;

import org.dcsc.core.model.user.DcscUserCreationForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by tktong on 7/26/2015.
 */
@Component
public class DcscUserCreationFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(DcscUserCreationForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DcscUserCreationForm dcscUserCreationForm = (DcscUserCreationForm) target;

        validatePassword(dcscUserCreationForm, errors);
    }

    private void validatePassword(DcscUserCreationForm dcscUserCreationForm, Errors errors) {
        String password = dcscUserCreationForm.getPassword();
        String confirmPassword = dcscUserCreationForm.getConfirmPassword();

        if(!password.equals(confirmPassword)) {
            errors.reject("password.no_match", "Password and confirmation do not match.");
        }
    }
}
