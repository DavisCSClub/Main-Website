package org.dcsc.admin.faq;

import org.dcsc.faq.QuestionAnswerService;
import org.dcsc.faq.form.QuestionAnswerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tktong on 8/8/2015.
 */
@Controller
@RequestMapping(value = "/admin/faq")
public class AdminFaqCreationController {
    @Autowired
    private QuestionAnswerService questionAnswerService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createFaq(Model model) {
        model.addAttribute("form", new QuestionAnswerForm());

        return "admin/create-faq";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createFaq(@ModelAttribute QuestionAnswerForm questionAnswerForm) {
        System.out.println(questionAnswerForm.getId());
        System.out.println(questionAnswerForm.getQuestion());
        System.out.println(questionAnswerForm.getAnswer());

        questionAnswerService.save(questionAnswerForm);

        return "redirect:/admin/faq";
    }
}
