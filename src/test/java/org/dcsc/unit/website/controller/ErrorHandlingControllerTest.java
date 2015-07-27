package org.dcsc.unit.website.controller;

import org.dcsc.website.controller.ErrorHandlingController;
import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

/**
 * Created by tktong on 7/7/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class ErrorHandlingControllerTest {
    private static final String TEMPLATE_VAR_ERROR_CODE = "errorCode";
    private static final String TEMPLATE_VAR_PRIMARY_MESSAGE = "primaryMessage";
    private static final String TEMPLATE_VAR_SECONDARY_MESSAGE = "secondaryMessage";
    private static final String EXPECTED_ERROR_PATH = "/error";
    private static final String EXPECTED_ERROR_VIEW_NAME = "main/error";

    @Mock Model model;

    private ErrorHandlingController errorController = new ErrorHandlingController();

    @Test
    public void error404() {
        String actualView = errorController.error404(model);

        Mockito.verify(model).addAttribute(TEMPLATE_VAR_ERROR_CODE, HttpStatus.NOT_FOUND.value());
        Mockito.verify(model).addAttribute(Mockito.eq(TEMPLATE_VAR_PRIMARY_MESSAGE), Mockito.anyString());
        Mockito.verify(model).addAttribute(Mockito.eq(TEMPLATE_VAR_SECONDARY_MESSAGE), Mockito.anyString());

        Assert.assertEquals(EXPECTED_ERROR_VIEW_NAME, actualView);
    }

    @Test
    public void error405() {
        String actualView = errorController.error405(model);

        Mockito.verify(model).addAttribute(TEMPLATE_VAR_ERROR_CODE, HttpStatus.METHOD_NOT_ALLOWED.value());
        Mockito.verify(model).addAttribute(Mockito.eq(TEMPLATE_VAR_PRIMARY_MESSAGE), Mockito.anyString());
        Mockito.verify(model).addAttribute(Mockito.eq(TEMPLATE_VAR_SECONDARY_MESSAGE), Mockito.anyString());

        Assert.assertEquals(EXPECTED_ERROR_VIEW_NAME, actualView);
    }

    @Test
    public void getErrorPath() {
        String actualErrorPath = errorController.getErrorPath();

        Assert.assertEquals(EXPECTED_ERROR_PATH, actualErrorPath);
    }
}