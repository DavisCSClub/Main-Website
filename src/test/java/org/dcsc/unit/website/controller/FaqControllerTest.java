package org.dcsc.unit.website.controller;

import org.dcsc.core.model.faq.FrequentlyAskedQuestion;
import org.dcsc.core.service.faq.FrequentlyAskedQuestionService;
import org.dcsc.web.presentation.controller.FaqController;
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
    private FrequentlyAskedQuestionService frequentlyAskedQuestionService;
    @Mock
    private List<FrequentlyAskedQuestion> expectedFrequentlyAskedQuestions;
    @Mock
    private Model model;

    @InjectMocks
    private FaqController faqController;

    @Test
    public void faq() {
        Mockito.when(frequentlyAskedQuestionService.getAllQuestionAnswers()).thenReturn(expectedFrequentlyAskedQuestions);

        String actualView = faqController.faq(model);

        Mockito.verify(model).addAttribute("questionAnswers", expectedFrequentlyAskedQuestions);

        Assert.assertEquals("main/faq", actualView);
    }
}