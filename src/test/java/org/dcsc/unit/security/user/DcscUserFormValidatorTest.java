package org.dcsc.unit.security.user;

import org.dcsc.security.user.DcscUserForm;
import org.dcsc.security.user.DcscUserFormValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class DcscUserFormValidatorTest {
    private static final String PASSWORD = "password";
    private static final String CONFIRM_PASSWORD = "password";
    private static final String NON_MATCHING_PASSWORD = "PaSsWoRd";

    @Mock
    private DcscUserForm target;
    @Mock
    private Errors errors;

    private DcscUserFormValidator dcscUserFormValidator = new DcscUserFormValidator();

    @Test
    public void supportsTrue() {
        Assert.assertTrue(dcscUserFormValidator.supports(DcscUserForm.class));
    }

    @Test
    public void supportsFalse() {
        Assert.assertFalse(dcscUserFormValidator.supports(Object.class));
    }

    @Test
    public void successfulValidate() {
        Mockito.when(target.getPassword()).thenReturn(PASSWORD);
        Mockito.when(target.getConfirmPassword()).thenReturn(CONFIRM_PASSWORD);

        dcscUserFormValidator.validate(target, errors);

        Mockito.verify(errors, Mockito.never()).reject(Mockito.anyString(), Mockito.anyString());
    }

    @Test
    public void unsuccessfulValidate() {
        Mockito.when(target.getPassword()).thenReturn(PASSWORD);
        Mockito.when(target.getConfirmPassword()).thenReturn(NON_MATCHING_PASSWORD);

        dcscUserFormValidator.validate(target, errors);

        Mockito.verify(errors).reject(Mockito.anyString(), Mockito.anyString());
    }
}