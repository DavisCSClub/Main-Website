package org.dcsc.admin.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ViewControllerTest {
    private ViewController controller = new ViewController();

    @Mock
    private HttpServletResponse response;

    @Test
    public void getLogin() {
        assertEquals(ViewNames.LOGIN, controller.getLogin());
    }

    @Test
    public void redirectedToApplicationShell() throws Exception {
        controller.redirectToApplicationShell(response);
        verify(response).sendRedirect("/admin/");
    }

    @Test
    public void getApplicationShell() {
        assertEquals(ViewNames.ALTAIR_APPLICATION_SHELL, controller.getApplicationShell());
    }

    @Test
    public void getSidebar() {
        assertEquals("admin/view/common/sidebar::altair", controller.getView("sidebar"));
    }

    @Test
    public void getHeader() {
        assertEquals("admin/view/common/header::altair", controller.getView("header"));
    }

    @Test
    public void getStandardView() {
        assertEquals("admin/view/someView::altair", controller.getView("someView"));
    }

    @Test
    public void getEditView() {
        assertEquals("admin/view/edit/someView::altair", controller.getEdit("someView"));
    }
}