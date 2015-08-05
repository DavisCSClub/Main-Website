package org.dcsc.admin;

import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.DcscUserForm;
import org.dcsc.security.user.DcscUserFormValidator;
import org.dcsc.security.user.DcscUserService;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/26/2015.
 */
@Controller
@RequestMapping(value = "/admin/super/users/user/edit/{userId}")
public class AdminUserEditController {
    @Autowired
    private DcscUserService dcscUserService;
    @Autowired
    private DcscUserFormValidator dcscUserFormValidator;

    @InitBinder("dcscUserForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(dcscUserFormValidator);
    }

    @RequestMapping(value = "/admin/super/users")
    public String users(Model model) {
        List<DcscUser> users = dcscUserService.getAllUsers();

        model.addAttribute("users", users);

        return "admin/users";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String editUser(@PathVariable("userId") long userId, Model model) {
        Optional<DcscUser> user = dcscUserService.getUserById(userId);

        if(!user.isPresent()) {
            return "redirect:/admin/super/users";
        }

        DcscUserForm dcscUserForm = new DcscUserForm(user.get());
        model.addAttribute("form", dcscUserForm);

        return "admin/user";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editUser(@PathVariable("userId") long userId, @Valid @ModelAttribute DcscUserForm dcscUserForm,
                           BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/admin/super/users/create?error";
        }

        dcscUserService.saveDcscUser(userId, dcscUserForm);

        return "redirect:/admin/super/users";
    }


    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException e) {
        return "redirect:/admin/super/users";
    }
}
