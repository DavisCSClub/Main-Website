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
public class QuestionAnswerService {
    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    public QuestionAnswer save(QuestionAnswerForm questionAnswerForm) {
        return save(questionAnswerForm.build());
    }

    private QuestionAnswer save(QuestionAnswer questionAnswer) {
        return questionAnswerRepository.save(questionAnswer);
    }

    @Transactional(readOnly = true)
    public Optional<QuestionAnswer> getQuestionAnswerById(long id) {
        return questionAnswerRepository.findQuestionAnswerById(id);
    }

    @Transactional(readOnly = true)
    public List<QuestionAnswer> getAllQuestionAnswers() {
        return questionAnswerRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
