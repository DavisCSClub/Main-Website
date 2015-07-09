package org.dcsc.faq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tktong on 7/8/2015.
 */
@Service
public class QuestionAnswerService {
    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Transactional(readOnly = true)
    public List<QuestionAnswer> getAllQuestionAnswers() {
        return questionAnswerRepository.findAll(new Sort(Sort.Direction.ASC, "id"));
    }
}
