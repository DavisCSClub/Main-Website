package org.dcsc.unit.admin;

import org.dcsc.admin.LoginController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.Test;

/**
 * Created by tktong on 7/16/15.
 */
public class LoginControllerTest {
    private LoginController loginController = new LoginController();

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();

    @Test
    public void login() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/login");

        mockMvc.perform(request)
            .andExpect(MockMvcResultMatchers.view().name("admin/login"));
    }

}
