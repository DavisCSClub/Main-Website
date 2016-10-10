package org.dcsc.web.exception;


import org.dcsc.web.constants.ModelAttributeNames;
import org.dcsc.web.constants.ViewNames;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);
    private static final String DEFAULT_ERROR_MESSAGE = "If debugging is the process of removing software bugs, then programming must be the process of putting them in. <br /> - Edsger Dijkstra <br /><br /> Something happened and we're looking into it.";

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception e) {
        LOGGER.error("", e);

        ModelAndView modelView = new ModelAndView(ViewNames.ERROR);
        modelView.addObject(ModelAttributeNames.ERROR_CODE, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        modelView.addObject(ModelAttributeNames.PRIMARY_MESSAGE, DEFAULT_ERROR_MESSAGE);
        return modelView;
    }
}
