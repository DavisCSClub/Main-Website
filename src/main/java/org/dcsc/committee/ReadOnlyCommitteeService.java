package org.dcsc.committee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by tktong on 7/9/2015.
 */
@Service
public class ReadOnlyCommitteeService {
    @Autowired
    private CommitteeRepository committeeRepository;

    @Transactional(readOnly = true)
    public Optional<Committee> getCommitteeByTag(String tag) {
        return committeeRepository.findCommitteeByTag(tag);
    }
}
