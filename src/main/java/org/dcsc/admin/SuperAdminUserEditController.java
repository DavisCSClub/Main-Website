package org.dcsc.admin;

import javassist.NotFoundException;
import org.dcsc.security.user.DcscUser;
import org.dcsc.security.user.DcscUserService;
import org.dcsc.security.user.form.superedit.DcscSuperUserEditForm;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

/**
 * Created by tktong on 7/26/2015.
 */
@Controller
@RequestMapping(value = "/admin/super/users/user/edit/{userId}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class SuperAdminUserEditController {
    @Autowired
    private DcscUserService dcscUserService;

    @RequestMapping(method = RequestMethod.GET)
    public String editUser(@PathVariable("userId") long userId, Model model) {
        Optional<DcscUser> user = dcscUserService.getUserById(userId);

        if(!user.isPresent()) {
            return "redirect:/admin/super/users";
        }

        DcscUser dcscUser = user.get();

        DcscSuperUserEditForm form = new DcscSuperUserEditForm();

        form.setId(userId);
        form.setEnabled(dcscUser.isEnabled());
        form.setLocked(dcscUser.isLocked());
        form.setRole(dcscUser.getRole());

        model.addAttribute("user", dcscUser);

        model.addAttribute("form", form);

        return "admin/super/user-edit-form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String editUser(@PathVariable("userId") long userId, @ModelAttribute DcscSuperUserEditForm dcscSuperUserEditForm,
                           BindingResult result, RedirectAttributes redirectAttributes) {
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/admin/super/users";
        }

        try {
            dcscUserService.save(dcscSuperUserEditForm, userId);
        } catch(NotFoundException e) {
            return "redirect:/admin/super/users";
        }

        return "redirect:/admin/super/users";
    }


    @ExceptionHandler(TypeMismatchException.class)
    public String handleTypeMismatchException(TypeMismatchException e) {
        return "redirect:/admin/super/users";
    }
}
