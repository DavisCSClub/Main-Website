package org.dcsc.admin;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.DcscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by tktong on 8/4/2015.
 */
@Controller
@RequestMapping(value = "/admin/super/users")
public class AdminUserController {
    @Autowired
    private DcscUserService dcscUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String users(Model model) {
        List<DcscUser> users = dcscUserService.getAllUsers();

        model.addAttribute("users", users);

        return "admin/users";
    }
}
