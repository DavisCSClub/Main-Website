package org.dcsc.website.controller;

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
    private static final String EXPECTED_404_VIEW_NAME = "main/error";

    @Mock
    Model model;

    private ErrorHandlingController errorController = new ErrorHandlingController();

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(errorController).build();

    @Test
    public void error404StatusOk() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/error/404");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void error404() {
        Mockito.when(model.addAttribute(Mockito.any(), Mockito.any())).thenReturn(model);

        String view = errorController.error404(model);

        Mockito.verify(model).addAttribute("errorCode", HttpStatus.NOT_FOUND.value());
        Mockito.verify(model).addAttribute(Mockito.eq("primaryMessage"), Mockito.anyString());
        Mockito.verify(model).addAttribute(Mockito.eq("secondaryMessage"), Mockito.anyString());

        Assert.assertEquals(EXPECTED_404_VIEW_NAME, view);
    }

    @Test
    public void getErrorPath() {
        Assert.assertEquals(EXPECTED_ERROR_PATH, errorController.getErrorPath());
    }
}