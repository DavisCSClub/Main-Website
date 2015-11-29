package org.dcsc.core.committee;

import org.dcsc.core.committee.Committee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by tktong on 7/9/2015.
 */
@Repository
public interface CommitteeRepository extends JpaRepository<Committee, Long> {
    Optional<Committee> findCommitteeByTag(String tag);
}
