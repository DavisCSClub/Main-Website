package org.dcsc.unit.website.configuration;

import org.dcsc.website.configuration.ServletCustomizer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;

/**
 * Created by tktong on 7/27/2015.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ServletCustomizer.class)
public class ServletCustomizerTest {
    @Mock
    private ConfigurableEmbeddedServletContainer container;
    @Mock
    private ErrorPage error404Page;
    @Mock
    private ErrorPage error405Page;

    private ServletCustomizer servletCustomizer = new ServletCustomizer();

    @Test
    public void customize() throws Exception {
        PowerMockito.whenNew(ErrorPage.class)
                .withArguments(HttpStatus.NOT_FOUND, "/error/404")
                .thenReturn(error404Page);
        PowerMockito.whenNew(ErrorPage.class)
                .withArguments(HttpStatus.METHOD_NOT_ALLOWED, "/error/405")
                .thenReturn(error405Page);

        servletCustomizer.customize(container);

        Mockito.verify(container).addErrorPages(error404Page);
        Mockito.verify(container).addErrorPages(error405Page);
    }
}