package org.dcsc.config.servlet;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;


@Component
public class ServletCustomizer implements EmbeddedServletContainerCustomizer {
    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        ErrorPage unauthorized = new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401");
        ErrorPage forbidden = new ErrorPage(HttpStatus.FORBIDDEN, "/error/403");
        ErrorPage notFound = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
        ErrorPage methodNotAllowed = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405");

        container.addErrorPages(unauthorized, forbidden, notFound, methodNotAllowed);
    }
}
