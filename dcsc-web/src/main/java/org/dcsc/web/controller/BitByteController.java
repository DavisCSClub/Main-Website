package org.dcsc.web.controller;

import org.dcsc.web.constants.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BitByteController {
    @RequestMapping(value = "/bit-byte")
    public String bitByte() {
        return ViewNames.BIT_BYTE;
    }
}
