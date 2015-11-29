package org.dcsc.admin.controllers;

import org.dcsc.core.faq.FrequentlyAskedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by tktong on 8/6/2015.
 */
@Controller
@RequestMapping(value = "/admin/faq")
public class AdminFaqController {
    @Autowired
    private FrequentlyAskedQuestionService frequentlyAskedQuestionService;

    @RequestMapping(method = RequestMethod.GET)
    public String faqList(Model model) {
        model.addAttribute("QUESTION_ANSWER", frequentlyAskedQuestionService.getAllQuestionAnswers());

        return "admin/faq";
    }
}
