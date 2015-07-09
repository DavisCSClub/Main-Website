package org.dcsc.unit.faq;

import org.dcsc.faq.QuestionAnswer;
import org.dcsc.faq.QuestionAnswerRepository;
import org.dcsc.faq.QuestionAnswerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by tktong on 7/8/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionAnswerServiceTest {
    @Mock
    private QuestionAnswerRepository questionAnswerRepository;
    @Mock
    private List<QuestionAnswer> expectedQuestionAnswers;

    @InjectMocks
    private QuestionAnswerService questionAnswerService;

    @Test
    public void getAllQuestionAnswers() throws Exception {
        Mockito.when(questionAnswerRepository.findAll(Mockito.any(Sort.class))).thenReturn(expectedQuestionAnswers);

        List<QuestionAnswer> actualQuestionAnswers = questionAnswerService.getAllQuestionAnswers();

        Assert.assertEquals(expectedQuestionAnswers, actualQuestionAnswers);
    }
}