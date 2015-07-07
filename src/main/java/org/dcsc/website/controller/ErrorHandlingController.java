package org.dcsc.website.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tktong on 7/7/2015.
 */
@Controller
public class ErrorHandlingController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    private static final String TEMPLATE_VAR_ERROR_CODE = "errorCode";
    private static final String TEMPLATE_VAR_PRIMARY_MESSAGE = "primaryMessage";
    private static final String TEMPLATE_VAR_SECONDARY_MESSAGE = "secondaryMessage";

    @RequestMapping(value = "/error/404")
    public String error404(Model model) {
        model.addAttribute(TEMPLATE_VAR_ERROR_CODE, HttpStatus.NOT_FOUND.value());
        model.addAttribute(TEMPLATE_VAR_PRIMARY_MESSAGE, "Looks like the page you're looking for was moved or never existed.");
        model.addAttribute(TEMPLATE_VAR_SECONDARY_MESSAGE, "Make sure you typed the correct URL or followed a valid link.");

        return "main/error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
