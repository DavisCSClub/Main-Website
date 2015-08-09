package org.dcsc.configuration;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Created by tktong on 7/7/2015.
 */
@Component
public class ServletCustomizer implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        ErrorPage unathorized = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401");
        ErrorPage forbidden = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403");
        ErrorPage notFound = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
        ErrorPage methodNotAllowed = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405");

        container.addErrorPages(notFound);
        container.addErrorPages(methodNotAllowed);
    }
}
