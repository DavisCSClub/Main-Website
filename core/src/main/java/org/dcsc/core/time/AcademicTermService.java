package org.dcsc.core.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AcademicTermService {
    @Autowired
    private AcademicTermRepository academicTermRepository;

    @Cacheable
    public AcademicTerm getCurrentTerm() throws Exception {
        return academicTermRepository.findByDate(LocalDate.now())
                .orElseThrow(() -> new Exception("No academic term defined"));
    }

    private void save(AcademicTerm academicTerm) {
        academicTermRepository.save(academicTerm);
    }

}
