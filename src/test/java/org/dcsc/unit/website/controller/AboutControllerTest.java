package org.dcsc.unit.website.controller;

import org.dcsc.website.controller.AboutController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;

/**
 * Created by tktong on 7/8/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class AboutControllerTest {
    private AboutController aboutController = new AboutController();

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(aboutController).build();

    @Test
    public void about() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/about");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.view().name("main/about"));
    }
}