package org.dcsc.unit.faq;

import org.dcsc.core.model.faq.FrequentlyAskedQuestion;
import org.dcsc.core.persistence.faq.FrequentlyAskedQuestionRepository;
import org.dcsc.core.service.faq.FrequentlyAskedQuestionService;
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
public class FrequentlyAskedQuestionServiceTest {
    @Mock private FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository;
    @Mock private List<FrequentlyAskedQuestion> expectedFrequentlyAskedQuestions;

    @InjectMocks
    private FrequentlyAskedQuestionService frequentlyAskedQuestionService;

    @Test
    public void getAllQuestionAnswers() throws Exception {
        Mockito.when(frequentlyAskedQuestionRepository.findAll(Mockito.any(Sort.class))).thenReturn(expectedFrequentlyAskedQuestions);

        List<FrequentlyAskedQuestion> actualFrequentlyAskedQuestions = frequentlyAskedQuestionService.getAllQuestionAnswers();

        Assert.assertEquals(expectedFrequentlyAskedQuestions, actualFrequentlyAskedQuestions);
    }
}