package org.dcsc.config.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableAspectJAutoProxy
@EnableSpringDataWebSupport
public class ApplicationConfiguration {
}
