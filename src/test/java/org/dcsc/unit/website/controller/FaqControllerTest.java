package org.dcsc.unit.website.controller;

import org.dcsc.faq.QuestionAnswer;
import org.dcsc.faq.QuestionAnswerService;
import org.dcsc.website.controller.FaqController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.List;
/**
 * Created by tktong on 7/8/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class FaqControllerTest {
    @Mock
    private QuestionAnswerService questionAnswerService;
    @Mock
    private List<QuestionAnswer> expectedQuestionAnswers;
    @Mock
    private Model model;

    @InjectMocks
    private FaqController faqController;

    @Test
    public void faq() {
        Mockito.when(questionAnswerService.getAllQuestionAnswers()).thenReturn(expectedQuestionAnswers);

        String actualView = faqController.faq(model);

        Mockito.verify(model).addAttribute("questionAnswers", expectedQuestionAnswers);

        Assert.assertEquals("main/faq", actualView);
    }
}