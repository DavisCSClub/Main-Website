package org.dcsc.faq;

import org.dcsc.faq.form.QuestionAnswerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by tktong on 7/8/2015.
 */
@Service
public class FrequentlyAskedQuestionService {
    @Autowired
    private FrequentlyAskedQuestionRepository frequentlyAskedQuestionRepository;

    public FrequentlyAskedQuestion save(QuestionAnswerForm questionAnswerForm) {
        return save(questionAnswerForm.build());
    }

    private FrequentlyAskedQuestion save(FrequentlyAskedQuestion frequentlyAskedQuestion) {
        return frequentlyAskedQuestionRepository.save(frequentlyAskedQuestion);
    }

    @Transactional(readOnly = true)
    public Optional<FrequentlyAskedQuestion> getQuestionAnswerById(long id) {
        return frequentlyAskedQuestionRepository.findQuestionAnswerById(id);
    }

    @Transactional(readOnly = true)
    public List<FrequentlyAskedQuestion> getAllQuestionAnswers() {
        return frequentlyAskedQuestionRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
