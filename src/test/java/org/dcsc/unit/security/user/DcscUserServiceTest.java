package org.dcsc.unit.security.user;

import org.dcsc.security.user.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(DcscUserService.class)
public class DcscUserServiceTest {
    private static final long ID = 0;
    private static final String USERNAME = "username";
    private static final String NAME = "name";
    private static final String PASSWORD = "password";
    private static final String ENCRYPTED_PASSWORD = "encrypted password";
    private static final String EMAIL = "email";

    @Mock
    private DcscUserRepository dcscUserRepository;
    @Mock
    private DcscUserForm dcscUserForm;
    @Mock
    private DcscUser dcscUser;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private List<DcscUser> expectedUserList;
    @Mock
    private Optional<DcscUser> expectedOptional;

    @InjectMocks
    private DcscUserService dcscUserService;

    @Test
    public void saveDcscUserWithFormOnly() throws Exception {
        PowerMockito.whenNew(DcscUser.class).withNoArguments().thenReturn(dcscUser);
        PowerMockito.whenNew(BCryptPasswordEncoder.class).withNoArguments().thenReturn(passwordEncoder);

        Mockito.when(dcscUserForm.getUsername()).thenReturn(USERNAME);
        Mockito.when(dcscUserForm.getEmail()).thenReturn(EMAIL);
        Mockito.when(dcscUserForm.getPassword()).thenReturn(PASSWORD);
        Mockito.when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCRYPTED_PASSWORD);
        Mockito.when(dcscUserForm.getName()).thenReturn(NAME);
        Mockito.when(dcscUserRepository.save(dcscUser)).thenReturn(dcscUser);

        DcscUser actualUser = dcscUserService.saveDcscUser(dcscUserForm);

        Mockito.verify(dcscUser).setUsername(USERNAME);
        Mockito.verify(dcscUser).setName(NAME);
        Mockito.verify(dcscUser).setEmail(EMAIL);
        Mockito.verify(dcscUser).setPassword(ENCRYPTED_PASSWORD);
        Mockito.verify(dcscUser).setRole(Role.ROLE_USER);

        Assert.assertEquals(dcscUser, actualUser);
    }

    @Test
    public void saveDcscUserWithIdAndForm() throws Exception {
        Mockito.when(dcscUserRepository.findUserById(ID)).thenReturn(expectedOptional);
        Mockito.when(expectedOptional.isPresent()).thenReturn(true);
        Mockito.when(expectedOptional.get()).thenReturn(dcscUser);

        PowerMockito.whenNew(BCryptPasswordEncoder.class).withNoArguments().thenReturn(passwordEncoder);

        Mockito.when(dcscUserForm.getUsername()).thenReturn(USERNAME);
        Mockito.when(dcscUserForm.getEmail()).thenReturn(EMAIL);
        Mockito.when(dcscUserForm.getPassword()).thenReturn(PASSWORD);
        Mockito.when(passwordEncoder.encode(PASSWORD)).thenReturn(ENCRYPTED_PASSWORD);
        Mockito.when(dcscUserForm.getName()).thenReturn(NAME);
        Mockito.when(dcscUserRepository.save(dcscUser)).thenReturn(dcscUser);

        DcscUser actualUser = dcscUserService.saveDcscUser(ID, dcscUserForm);

        Mockito.verify(dcscUser).setUsername(USERNAME);
        Mockito.verify(dcscUser).setName(NAME);
        Mockito.verify(dcscUser).setEmail(EMAIL);
        Mockito.verify(dcscUser).setPassword(ENCRYPTED_PASSWORD);
        Mockito.verify(dcscUser).setRole(Role.ROLE_USER);

        Assert.assertEquals(dcscUser, actualUser);
    }

    @Test
    public void saveDcscUserWithInvalidId() {
        Mockito.when(dcscUserRepository.findUserById(ID)).thenReturn(expectedOptional);
        Mockito.when(expectedOptional.isPresent()).thenReturn(false);

        Assert.assertNull(dcscUserService.saveDcscUser(ID, dcscUserForm));
    }

    @Test
    public void getAllUsers() {
        Mockito.when(dcscUserRepository.findAll()).thenReturn(expectedUserList);

        Assert.assertEquals(expectedUserList, dcscUserService.getAllUsers());
    }

    @Test
    public void getUserById() {
        Mockito.when(dcscUserRepository.findUserById(ID)).thenReturn(expectedOptional);

        Assert.assertEquals(expectedOptional, dcscUserService.getUserById(ID));
    }

    @Test
    public void getUserByUsername() {
        Mockito.when(dcscUserRepository.findUserByUsername(USERNAME)).thenReturn(expectedOptional);

        Assert.assertEquals(expectedOptional, dcscUserService.getUserByUsername(USERNAME));
    }
}