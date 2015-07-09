package org.dcsc.faq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tktong on 7/8/2015.
 */
@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
}
