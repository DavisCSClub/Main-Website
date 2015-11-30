package org.dcsc.web.controller;

import org.dcsc.web.here.HereForm;
import org.dcsc.web.here.HereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class HereRestController {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    @Autowired
    private HereService hereService;

    @RequestMapping(value = "/r/here", method = RequestMethod.POST)
    public boolean checkin(@RequestBody HereForm hereForm) {
        boolean success = validEmail(hereForm.getEmail());

        if (success) {
            hereService.checkIn(hereForm);
        }

        return success;
    }

    private boolean validEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
