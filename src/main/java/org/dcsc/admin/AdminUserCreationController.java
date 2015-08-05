package org.dcsc.admin;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.DcscUserForm;
import org.dcsc.security.user.DcscUserFormValidator;
import org.dcsc.security.user.DcscUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by tktong on 8/4/2015.
 */
@Controller
@RequestMapping(value = "/admin/super/users/create", method = RequestMethod.GET)
public class AdminUserCreationController {
    @Autowired
    private DcscUserService dcscUserService;
    @Autowired
    private DcscUserFormValidator dcscUserFormValidator;

    @InitBinder("dcscUserForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(dcscUserFormValidator);
    }

    @RequestMapping(value = "/admin/super/users/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        DcscUserForm dcscUserForm = new DcscUserForm();

        model.addAttribute("form", dcscUserForm);

        return "admin/user";
    }


    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute DcscUserForm dcscUserForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/admin/super/users/create?error";
        }

        dcscUserService.saveDcscUser(dcscUserForm);

        return "redirect:/admin/super/users";
    }
}
