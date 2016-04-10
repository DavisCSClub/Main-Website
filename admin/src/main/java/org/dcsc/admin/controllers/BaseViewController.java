package org.dcsc.admin.controllers;


import org.dcsc.admin.constants.ViewNames;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BaseViewController {
    @RequestMapping(value = {"/admin/ng", "/admin/ng/"}, method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String getBaseView() {
        return ViewNames.ADMIN_ALTAIR_BASE;
    }
}
