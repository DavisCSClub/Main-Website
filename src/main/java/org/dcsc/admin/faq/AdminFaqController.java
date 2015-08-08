package org.dcsc.admin.faq;

import org.dcsc.faq.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tktong on 8/6/2015.
 */
@Controller
@RequestMapping(value = "/admin/faq")
public class AdminFaqController {
    @Autowired
    private QuestionAnswerService questionAnswerService;

    @RequestMapping(method = RequestMethod.GET)
    public String faqList(Model model) {
        model.addAttribute("QUESTION_ANSWER", questionAnswerService.getAllQuestionAnswers());

        return "admin/faq";
    }
}
