package org.dcsc.unit.faq;

import org.dcsc.faq.FrequentlyAskedQuestion;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by tktong on 7/27/2015.
 */
public class FrequentlyAskedQuestionTest {
    private static final long ID = 0;
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";

    private FrequentlyAskedQuestion frequentlyAskedQuestion;

    @Test
    public void setAndGetId() {
        frequentlyAskedQuestion = new FrequentlyAskedQuestion();
        frequentlyAskedQuestion.setId(ID);

        Assert.assertEquals(ID, frequentlyAskedQuestion.getId());
    }

    @Test
    public void setAndGetQuestion() {
        frequentlyAskedQuestion = new FrequentlyAskedQuestion();
        frequentlyAskedQuestion.setQuestion(QUESTION);

        Assert.assertEquals(QUESTION, frequentlyAskedQuestion.getQuestion());
    }

    @Test
    public void setAndGetAnswer() {
        frequentlyAskedQuestion = new FrequentlyAskedQuestion();
        frequentlyAskedQuestion.setAnswer(ANSWER);

        Assert.assertEquals(ANSWER, frequentlyAskedQuestion.getAnswer());
    }
}