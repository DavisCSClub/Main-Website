package org.dcsc.admin.controllers;

import org.dcsc.core.faq.FrequentlyAskedQuestion;
import org.dcsc.core.faq.FrequentlyAskedQuestionService;
import org.dcsc.core.faq.QuestionAnswerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

/**
 * Created by tktong on 8/8/2015.
 */
@Controller
@RequestMapping(value = "/admin/faq")
public class AdminFaqEditController {
    @Autowired
    private FrequentlyAskedQuestionService frequentlyAskedQuestionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String editFaq(@PathVariable("id") long id, Model model) {
        Optional<FrequentlyAskedQuestion> questionAnswer = frequentlyAskedQuestionService.getQuestionAnswerById(id);

        if (!questionAnswer.isPresent()) {
            return "redirect:/admin/faq";
        }

        FrequentlyAskedQuestion qa = questionAnswer.get();

        QuestionAnswerForm form = new QuestionAnswerForm();

        form.setId(id);
        form.setQuestion(qa.getQuestion());
        form.setAnswer(qa.getAnswer());

        model.addAttribute("form", form);

        return "admin/edit-faq";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String editFaq(@ModelAttribute QuestionAnswerForm questionAnswerForm) {
        frequentlyAskedQuestionService.save(questionAnswerForm);

        return "redirect:/admin/faq";
    }
}
