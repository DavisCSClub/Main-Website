package org.dcsc.compound.presentation.controller;

import org.dcsc.core.service.faq.FrequentlyAskedQuestionService;
import org.dcsc.core.model.faq.QuestionAnswerForm;
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
    private FrequentlyAskedQuestionService frequentlyAskedQuestionService;

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

        frequentlyAskedQuestionService.save(questionAnswerForm);

        return "redirect:/admin/faq";
    }
}
