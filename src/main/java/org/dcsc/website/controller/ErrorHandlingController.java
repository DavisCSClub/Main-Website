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

    @RequestMapping(value = "/error/404")
    public String error404(Model model) {
        model.addAttribute("error", "Not Found");
        model.addAttribute(TEMPLATE_VAR_ERROR_CODE, HttpStatus.NOT_FOUND.value());
        model.addAttribute(TEMPLATE_VAR_PRIMARY_MESSAGE, "OOOPPS.! THE PAGE YOU WERE LOOKING FOR, COULDN'T BE FOUND.");

        return "main/error";
    }

    @RequestMapping(value = "/error/405")
    public String error405(Model model) {
        model.addAttribute("error", "Method Not Allowed");
        model.addAttribute(TEMPLATE_VAR_ERROR_CODE, HttpStatus.METHOD_NOT_ALLOWED.value());
        model.addAttribute(TEMPLATE_VAR_PRIMARY_MESSAGE, "Your request method is not supported.");

        return "main/error";
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
