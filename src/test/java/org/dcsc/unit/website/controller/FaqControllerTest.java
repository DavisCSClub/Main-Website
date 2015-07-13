package org.dcsc.unit.website.controller;

import org.dcsc.faq.QuestionAnswer;
import org.dcsc.faq.QuestionAnswerService;
import org.dcsc.website.controller.FaqController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by tktong on 7/8/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class FaqControllerTest {
    @Mock
    private QuestionAnswerService questionAnswerService;
    @Mock
    private List<QuestionAnswer> expectedQuestionAnswers;

    @InjectMocks
    private FaqController faqController = new FaqController();

    private MockMvc mockMvc = MockMvcBuilders.standaloneSetup(faqController).build();

    @Test
    public void faq() throws Exception {
        Mockito.when(questionAnswerService.getAllQuestionAnswers()).thenReturn(expectedQuestionAnswers);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/faq");

        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("questionAnswers", expectedQuestionAnswers))
                .andExpect(MockMvcResultMatchers.view().name("main/faq"));
    }
}