package org.dcsc.web.controller;

import org.dcsc.web.constants.ModelAttributeNames;
import org.dcsc.web.constants.ViewNames;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
public class ErrorHandlingController implements ErrorController {
    private static final String ERROR_PATH = "/error";
    private static final String ERROR_MESSAGE_401 = "OOOPPS! You tried to access something you don't have permissions to.";
    private static final String ERROR_MESSAGE_500 = "If debugging is the process of removing software bugs, then "
            + "programming must be the process of putting them in. <br />- Edsger Dijkstra <br /><br />Something "
            + "happened and we're looking into it.";

    @RequestMapping(value = "/error")
    public String error(Model model) {
        model.addAttribute("error", "Internal Server Error");
        model.addAttribute(ModelAttributeNames.ERROR_CODE, HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute(ModelAttributeNames.PRIMARY_MESSAGE, ERROR_MESSAGE_500);


        return ViewNames.ERROR;
    }

    @RequestMapping(value = "/error/401")
    public String error401(HttpServletRequest request, Model model) {
        String errorMessage = Optional.ofNullable((String) request.getAttribute(WebUtils.ERROR_MESSAGE_ATTRIBUTE))
                .orElse(ERROR_MESSAGE_401);

        model.addAttribute("error", "Unauthorized");
        model.addAttribute(ModelAttributeNames.ERROR_CODE, HttpServletResponse.SC_UNAUTHORIZED);
        model.addAttribute(ModelAttributeNames.PRIMARY_MESSAGE, errorMessage);

        return ViewNames.ERROR;
    }

    @RequestMapping(value = "/error/403")
    public String error403(Model model) {
        model.addAttribute("error", "Forbidden");
        model.addAttribute(ModelAttributeNames.ERROR_CODE, HttpStatus.FORBIDDEN.value());
        model.addAttribute(ModelAttributeNames.PRIMARY_MESSAGE,
                           "OOOPPS.! You tried to access something you don't have permissions to.");

        return ViewNames.ERROR;
    }

    @RequestMapping(value = "/error/404")
    public String error404(Model model) {
        model.addAttribute("error", "Not Found");
        model.addAttribute(ModelAttributeNames.ERROR_CODE, HttpStatus.NOT_FOUND.value());
        model.addAttribute(ModelAttributeNames.PRIMARY_MESSAGE,
                           "OOOPPS.! THE PAGE YOU WERE LOOKING FOR, COULDN'T BE FOUND.");

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
