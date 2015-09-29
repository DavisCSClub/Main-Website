package org.dcsc.web.presentation.controller;

import org.dcsc.web.presentation.constants.ModelAttributeNames;
import org.dcsc.web.presentation.constants.ViewNames;
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

    @RequestMapping(value = "/error")
    public String error(Model model) {
        model.addAttribute("error", "Internal Server Error");
        model.addAttribute(ModelAttributeNames.ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute(ModelAttributeNames.PRIMARY_MESSAGE,
                "If debugging is the process of removing software bugs," +
                        "then programming must be the process of putting them in. <br />" +
                        "- Edsger Dijkstra <br /><br />" +
                        "Something happened and we're looking into it.");

        return ViewNames.ERROR;
    }

    @RequestMapping(value = "/error/401")
    public String error401(Model model) {
        model.addAttribute("error", "Unauthorized");
        model.addAttribute(ModelAttributeNames.ERROR_CODE, HttpStatus.UNAUTHORIZED.value());
        model.addAttribute(ModelAttributeNames.PRIMARY_MESSAGE, "OOOPPS.! You tried to access something you don't have permissions to.");

        return ViewNames.ERROR;
    }

    @RequestMapping(value = "/error/403")
    public String error403(Model model) {
        model.addAttribute("error", "Forbidden");
        model.addAttribute(ModelAttributeNames.ERROR_CODE, HttpStatus.FORBIDDEN.value());
        model.addAttribute(ModelAttributeNames.PRIMARY_MESSAGE, "OOOPPS.! You tried to access something you don't have permissions to.");

        return ViewNames.ERROR;
    }

    @RequestMapping(value = "/error/404")
    public String error404(Model model) {
        model.addAttribute("error", "Not Found");
        model.addAttribute(ModelAttributeNames.ERROR_CODE, HttpStatus.NOT_FOUND.value());
        model.addAttribute(ModelAttributeNames.PRIMARY_MESSAGE, "OOOPPS.! THE PAGE YOU WERE LOOKING FOR, COULDN'T BE FOUND.");

        return ViewNames.ERROR;
    }

    @RequestMapping(value = "/error/405")
    public String error405(Model model) {
        model.addAttribute("error", "Method Not Allowed");
        model.addAttribute(ModelAttributeNames.ERROR_CODE, HttpStatus.METHOD_NOT_ALLOWED.value());
        model.addAttribute(ModelAttributeNames.PRIMARY_MESSAGE, "Your request method is not supported.");

        return ViewNames.ERROR;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
