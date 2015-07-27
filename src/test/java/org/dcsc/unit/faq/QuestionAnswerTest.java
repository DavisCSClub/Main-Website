package org.dcsc.unit.faq;

import org.dcsc.faq.QuestionAnswer;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tktong on 7/27/2015.
 */
public class QuestionAnswerTest {
    private static final long ID = 0;
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";

    private QuestionAnswer questionAnswer;

    @Test
    public void setAndGetId() {
        questionAnswer = new QuestionAnswer();
        questionAnswer.setId(ID);

        Assert.assertEquals(ID, questionAnswer.getId());
    }

    @Test
    public void setAndGetQuestion() {
        questionAnswer = new QuestionAnswer();
        questionAnswer.setQuestion(QUESTION);

        Assert.assertEquals(QUESTION, questionAnswer.getQuestion());
    }

    @Test
    public void setAndGetAnswer() {
        questionAnswer = new QuestionAnswer();
        questionAnswer.setAnswer(ANSWER);

        Assert.assertEquals(ANSWER, questionAnswer.getAnswer());
    }
}