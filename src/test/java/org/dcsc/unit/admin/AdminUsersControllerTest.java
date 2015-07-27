package org.dcsc.unit.admin;


import org.dcsc.admin.AdminUsersController;
import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.DcscUserForm;
import org.dcsc.security.user.DcscUserFormValidator;
import org.dcsc.security.user.DcscUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.TypeMismatchException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AdminUsersController.class)
public class AdminUsersControllerTest {
    private static final long ID = 0;
    private static final String EXPECTED_USER_LIST_VIEW = "admin/users";
    private static final String EXPECTED_USER_FORM_VIEW = "admin/user";

    @Mock
    private DcscUserService dcscUserService;
    @Mock
    private DcscUserFormValidator dcscUserFormValidator;

    @InjectMocks
    private AdminUsersController adminUsersController;

    @Mock
    private WebDataBinder webDataBinder;
    @Mock
    private Model model;
    @Mock
    private List<DcscUser> expectedUserList;
    @Mock
    private DcscUserForm dcscUserForm;
    @Mock
    private BindingResult bindingResult;
    @Mock
    private RedirectAttributes redirectAttributes;
    @Mock
    private DcscUser dcscUser;
    @Mock
    private List<ObjectError> expectedErrorList;
    @Mock
    private Optional<DcscUser> optional;
    @Mock
    private TypeMismatchException exception;

    @Test
    public void initBinder() {
        adminUsersController.initBinder(webDataBinder);

        Mockito.verify(webDataBinder).addValidators(dcscUserFormValidator);
    }

    @Test
    public void users() {
        Mockito.when(dcscUserService.getAllUsers()).thenReturn(expectedUserList);

        String actualView = adminUsersController.users(model);

        Mockito.verify(model).addAttribute("users", expectedUserList);

        Assert.assertEquals(EXPECTED_USER_LIST_VIEW, actualView);
    }

    @Test
    public void createUserGet() throws Exception {
        PowerMockito.whenNew(DcscUserForm.class).withNoArguments().thenReturn(dcscUserForm);

        String actualView = adminUsersController.createUser(model);

        Mockito.verify(model).addAttribute("form", dcscUserForm);

        Assert.assertEquals(EXPECTED_USER_FORM_VIEW, actualView);
    }

    @Test
    public void createUserPostRequestWithNoErrors() {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(dcscUserService.saveDcscUser(dcscUserForm)).thenReturn(dcscUser);

        String actualRedirect = adminUsersController.createUser(dcscUserForm, bindingResult, redirectAttributes);

        Assert.assertEquals("redirect:/admin/super/users", actualRedirect);
    }

    @Test
    public void createUserPostRequestWithErrors() {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getAllErrors()).thenReturn(expectedErrorList);

        String actualRedirect = adminUsersController.createUser(dcscUserForm, bindingResult, redirectAttributes);

        Mockito.verify(redirectAttributes).addFlashAttribute("errors", expectedErrorList);

        Assert.assertEquals("redirect:/admin/super/users/create?error", actualRedirect);
    }

    @Test
    public void editUserGetRequestWithValidId() throws Exception {
        Mockito.when(dcscUserService.getUserById(ID)).thenReturn(optional);
        Mockito.when(optional.isPresent()).thenReturn(true);
        Mockito.when(optional.get()).thenReturn(dcscUser);

        PowerMockito.whenNew(DcscUserForm.class).withArguments(dcscUser).thenReturn(dcscUserForm);

        String view = adminUsersController.editUser(ID, model);

        Mockito.verify(model).addAttribute("form", dcscUserForm);

        Assert.assertEquals(EXPECTED_USER_FORM_VIEW, view);
    }

    @Test
    public void editUserGetRequestWithInvalidId() {
        Mockito.when(dcscUserService.getUserById(ID)).thenReturn(optional);
        Mockito.when(optional.isPresent()).thenReturn(false);

        String redirect = adminUsersController.editUser(ID, model);

        Assert.assertEquals("redirect:/admin/super/users", redirect);
    }

    @Test
    public void editUserPostRequestWithNoErrors() {
        Mockito.when(bindingResult.hasErrors()).thenReturn(false);
        Mockito.when(dcscUserService.saveDcscUser(ID, dcscUserForm)).thenReturn(dcscUser);

        String redirect = adminUsersController.editUser(ID, dcscUserForm, bindingResult, redirectAttributes);

        Assert.assertEquals("redirect:/admin/super/users", redirect);
    }

    @Test
    public void editUserPostRequestWithErrors() {
        Mockito.when(bindingResult.hasErrors()).thenReturn(true);
        Mockito.when(bindingResult.getAllErrors()).thenReturn(expectedErrorList);

        String redirect = adminUsersController.editUser(ID, dcscUserForm, bindingResult, redirectAttributes);

        Mockito.verify(redirectAttributes).addFlashAttribute("errors", expectedErrorList);

        Assert.assertEquals("redirect:/admin/super/users/create?error", redirect);
    }

    @Test
    public void handleTypeMismatchException() {
        String redirect = adminUsersController.handleTypeMismatchException(exception);

        Assert.assertEquals("redirect:/admin/super/users", redirect);
    }
}