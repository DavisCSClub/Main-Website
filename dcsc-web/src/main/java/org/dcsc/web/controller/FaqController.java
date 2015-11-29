package org.dcsc.web.controller;

import org.dcsc.web.constants.ModelAttributeNames;
import org.dcsc.web.constants.ViewNames;
import org.dcsc.core.faq.FrequentlyAskedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tktong on 7/8/2015.
 */
@Controller
public class FaqController {
    @Autowired
    private FrequentlyAskedQuestionService frequentlyAskedQuestionService;

    @RequestMapping(value = "/faq")
    public String faq(Model model) {
        model.addAttribute(ModelAttributeNames.FAQ, frequentlyAskedQuestionService.getAllQuestionAnswers());

        return ViewNames.FAQ;
    }
}
