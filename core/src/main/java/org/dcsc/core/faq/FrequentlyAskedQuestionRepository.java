package org.dcsc.core.faq;

import org.dcsc.core.faq.FrequentlyAskedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by tktong on 7/8/2015.
 */
@Repository
public interface FrequentlyAskedQuestionRepository extends JpaRepository<FrequentlyAskedQuestion, Long> {
    Optional<FrequentlyAskedQuestion> findQuestionAnswerById(long id);
}
