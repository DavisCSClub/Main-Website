package org.dcsc.unit.admin;

import org.dcsc.admin.DashboardController;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.Test;

/**
 * Created by tktong on 7/16/15.
 */
public class DashboardControllerTest {
    private DashboardController dashboardController = new DashboardController();

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(dashboardController).build();

    @Test
    public void dashboard() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/admin");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.view().name("admin/dashboard"));
    }
}
