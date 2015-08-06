package org.dcsc.admin;

import org.dcsc.security.user.DcscUserService;
import org.dcsc.security.user.form.create.DcscUserCreationForm;
import org.dcsc.security.user.form.create.DcscUserCreationFormValidator;
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
public class SuperAdminUserCreationController {
    @Autowired
    private DcscUserService dcscUserService;
    @Autowired
    private DcscUserCreationFormValidator createUserFormValidator;

    @InitBinder("dcscUserCreationForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(createUserFormValidator);
    }

    @RequestMapping(value = "/admin/super/users/create", method = RequestMethod.GET)
    public String createUser(Model model) {
        model.addAttribute("form", new DcscUserCreationForm());

        model.addAttribute("content_fragment", DcscUserCreationForm.THYMELEAF_FRAGMENT);

        return "admin/super/user-create-form";
    }


    @RequestMapping(value = "/admin/super/users/create", method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute DcscUserCreationForm dcscUserCreationForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/admin/super/users";
        }

        dcscUserService.save(dcscUserCreationForm);


        return "redirect:/admin/super/users";
    }
}
